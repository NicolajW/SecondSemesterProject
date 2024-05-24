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
	private static final String INSERT_ORDERLINE_Q = "insert into orderline values (?,?,?)";
	private PreparedStatement findAllPS;
	private PreparedStatement findByOrderNoPS;
	private PreparedStatement insertSaleOrder;
	private PreparedStatement insertOrderLine;
	private DBConnection dbconnection;

	public SaleOrderDB() throws DataAccessException {
		dbconnection = DBConnection.getInstance();
		Connection con = DBConnection.getInstance().getConnection();
		try {
			findAllPS = con.prepareStatement(FIND_ALL_Q);
			findByOrderNoPS = con.prepareStatement(FIND_BY_ID_Q);
			insertSaleOrder = con.prepareStatement(INSERT_Q, Statement.RETURN_GENERATED_KEYS);
			insertOrderLine = con.prepareStatement(INSERT_ORDERLINE_Q);

		} catch (SQLException e) {
			throw new DataAccessException("Could not prepare query", e);
		}

	}

	private List<SaleOrder> buildObjects(ResultSet rs) throws DataAccessException {
		List<SaleOrder> res = new ArrayList<>();
		SaleOrder o = buildObject(rs);
		while (o != null) {
			res.add(o);
			o = buildObject(rs);
		}
		return res;
	}

	private SaleOrder buildObject(ResultSet rs) throws DataAccessException {
		SaleOrder res = null;
		try {
			if (rs.next()) {
				res = new SaleOrder(rs.getInt("orderNo"), rs.getDouble("totalPrice"),
						new Person(null, null, rs.getString("email_FK"), null, 0, 0), rs.getInt("tableNo_FK"));
			}
		} catch (SQLException e) {
			throw new DataAccessException("Could not build object", e);
		}
		return res;
	}

	public void saveOrder(SaleOrder saleOrder) throws DataAccessException {
		try {
			dbconnection.startTransaction();

			insertSaleOrder.setDouble(1, saleOrder.getTotalPrice());
			insertSaleOrder.setString(2, saleOrder.getPerson().getEmail());
			insertSaleOrder.setInt(3, saleOrder.getTableNo());

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
			res = buildObject(rs);
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
