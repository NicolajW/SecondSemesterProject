package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.OrderLine;
import model.Person;
import model.SaleOrder;
import model.Table;

public class SaleOrderDB implements SaleOrderDAO {
	private static final String FIND_ALL_Q = "select orderNo, totalPrice, email_FK, tableNo_FK from SaleOrder";
	private static final String FIND_BY_ID_Q = FIND_ALL_Q + " where orderNo = ?";
	private static final String INSERT_Q = "INSERT INTO SaleOrder (totalPrice, email_FK, tableNo_FK) VALUES (?, ?, ?)";
	private static final String insertOrderLineQ = "insert into orderline values (?,?,?)";
	private PreparedStatement findAllPS;
	private PreparedStatement findByOrderNoPS;
	private PreparedStatement insertSaleOrder;
	private PreparedStatement insertOrderLine;
	private OrderLineDB oldb = new OrderLineDB();
	private DBConnection dbconnection;

	public SaleOrderDB() throws DataAccessException {
		dbconnection = DBConnection.getInstance();
		Connection con = DBConnection.getInstance().getConnection();
		try {
			findAllPS = con.prepareStatement(FIND_ALL_Q);
			findByOrderNoPS = con.prepareStatement(FIND_BY_ID_Q);
			insertSaleOrder = con.prepareStatement(INSERT_Q, Statement.RETURN_GENERATED_KEYS);
			insertOrderLine = con.prepareStatement(insertOrderLineQ);

		} catch (SQLException e) {
			throw new DataAccessException("Could not prepare query", e);
		}

	}

	private List<SaleOrder> buildObjects(ResultSet rs, boolean fullAssociation) throws DataAccessException {
		List<SaleOrder> res = new ArrayList<>();
		SaleOrder o = buildObject(rs, fullAssociation);
		while (o != null) {
			res.add(o);
			o = buildObject(rs, fullAssociation);
		}
		return res;
	}

	private SaleOrder buildObject(ResultSet rs, boolean fullAssociation) throws DataAccessException {
		SaleOrder res = null;
		try {
			if (rs.next()) {
				res = new SaleOrder(
						rs.getInt("orderNo"), 
						rs.getDouble("totalPrice"),
						new Person(null, null, rs.getString("email_FK"), null, 0, 0), 
						rs.getInt("tableNo_FK"));
				if (fullAssociation) {
					// En db klasse til OrderLine, så vi kan fremsøge den?
					List<OrderLine> orderline = oldb.findById(res.getOrderNo());
					res.setOl(orderline);
				}
			}
		} catch (SQLException e) {
			throw new DataAccessException("Could not build object", e);
		}
		return res;
	}

//	@Override
//	public void saveOrder(SaleOrder order) throws DataAccessException {
//		 int orderNo = generateOrderNumber();
//		 order.setOrderNo(orderNo);
//	}

	public void saveOrder(SaleOrder saleOrder) throws DataAccessException {
		try {
			//Connection con = DBConnection.getInstance().getConnection();
			//con.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
			
			dbconnection.startTransaction();
			//insertSaleOrder.setInt(1, saleOrder.getOrderNo());
			insertSaleOrder.setDouble(1, saleOrder.getTotalPrice());
			insertSaleOrder.setString(2, saleOrder.getEmployee().getEmail());
			insertSaleOrder.setInt(3, saleOrder.getTableNo());
			//insertSaleOrder.executeQuery();

			int orderId = dbconnection.executeInsertWithIdentity(insertSaleOrder);
			for (OrderLine ol : saleOrder.getOl()) {
				insertOrderLine.setDouble(1, ol.getQuantity());
				insertOrderLine.setInt(2, orderId);
				insertOrderLine.setInt(3, ol.getSaleProduct().getSaleProductID());
				insertOrderLine.executeUpdate();
			}
			dbconnection.commitTransaction();
		} catch (Exception e) {
			dbconnection.rollbackTransaction();
			throw new DataAccessException("save order failed", e);
		}

	}

	@Override
	public SaleOrder findByOrderNo(int orderNo, boolean fullAssociation) throws DataAccessException {
		SaleOrder res = null;
		try {
			findByOrderNoPS.setInt(1, orderNo);
			ResultSet rs = findByOrderNoPS.executeQuery();
			res = buildObject(rs, fullAssociation);
		} catch (SQLException e) {
			throw new DataAccessException("Could not find by OrderNo", e);
		}
		return res;
	}

	public int generateOrderNumber() throws DataAccessException {
		int orderNumber = 0;
		try {
			Connection con = DBConnection.getInstance().getConnection();
			String query = "SELECT MAX(orderNo) AS maxOrderNo FROM SaleOrder";
			Statement statement = con.createStatement();
			ResultSet resultSet = statement.executeQuery(query);
			if (resultSet.next()) {
				orderNumber = resultSet.getInt("maxOrderNo");
			}
			orderNumber++;
		} catch (SQLException e) {
			throw new DataAccessException("Error generating order number", e);
		}
		return orderNumber;
	}

}
