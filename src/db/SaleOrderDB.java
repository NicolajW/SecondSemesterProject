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

	//Instantiates SaleOrderDB
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

	/**
	 * This method creates a <code>List</code> of object of the type <code>SaleOrder</code> using <code>ArrayList</code>
	 * the object <code>SaleOrder</code> o is created, using the buildObject method
	 * a <code>while</code> loop to add a SaleOrder o to the <code>List</code>
	 * @param rs
	 * @return res
	 * @throws DataAccessException
	 */
	private List<SaleOrder> buildObjects(ResultSet rs) throws DataAccessException {
		List<SaleOrder> res = new ArrayList<>();
		SaleOrder o = buildObject(rs);
		while (o != null) {
			res.add(o);
			o = buildObject(rs);
		}
		return res;
	}

	/**
	 * This method creates an object of the SaleOrder
	 * it needs <code>if</code> statement of the ResultSet rs to create a new SaleOrder
	 * the try catch throws an SQLException to catch, whether it can create the <code>SaleOrder</code> Object or not. 
	 * @param rs
	 * @return res
	 * @throws DataAccessException
	 */
	private SaleOrder buildObject(ResultSet rs) throws DataAccessException {
		SaleOrder res = null;
		try {
			if (rs.next()) {
				res = new SaleOrder(
						rs.getInt("orderNo"), 
						rs.getDouble("totalPrice"),
						new Person(null, null, rs.getString("email_FK"),null, 0, 0),
						new Table(false, rs.getInt("tableNo_FK"))
						);
			}
		} catch (SQLException e) {
			throw new DataAccessException("Could not build object", e);
		}
		return res;
	}

	/**
	 * This method saves the saleOrder where it starts a transaction to begin the saleOrder with the <code>DBConnection</code> class
	 * this inserts the price, email from employee, and gets the tableNo
	 * then it uses a <code>for</code> loop to set quantity, Id, and SaleProductID
	 * It then commits the transaction to the Database and the order is saved
	 * @param saleOrder
	 * @throws DataAccessException
	 */
	public void saveOrder(SaleOrder saleOrder) throws DataAccessException {
		try {
			dbconnection.startTransaction();

			insertSaleOrder.setDouble(1, saleOrder.getTotalPrice());
			insertSaleOrder.setString(2, saleOrder.getPerson().getEmail());
			insertSaleOrder.setInt(3, saleOrder.getTable().getTableNo());

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
	/**
	 * This method find saleOrder by Order Number. here we use a try catch, where we find the orderNo and execute a query by
	 * <code>findByOrderPS</code>. res uses the <code>buildObject</code> to build the object, before finding it?
	 * @param orderNo, fullAssociation
	 * @return res of the SaleOrder
	 * @throws DataAccessException
	 */
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



}
