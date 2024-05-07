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

public class SaleOrderDB implements SaleOrderDAO{
	private static final String FIND_ALL_Q = "select orderNo, totalPrice, email_FK, tableNo_FK from SaleOrder";
	private static final String FIND_BY_ID_Q = 
			FIND_ALL_Q + " where orderNo = ?";
	private static final String INSERT_Q = "INSERT INTO SaleOrder (orderNo, totalPrice, email_FK, tableNo_FK) VALUES (?, ?, ?, ?)";
	private PreparedStatement findAllPS;
	private PreparedStatement findByOrderNoPS;
	private PreparedStatement insert;
	private OrderLine ol;
	
	public SaleOrderDB() throws DataAccessException{
		Connection con = DBConnection.getInstance().getConnection();
		try {
			findAllPS = con.prepareStatement(FIND_ALL_Q);
			findByOrderNoPS = con.prepareStatement(FIND_BY_ID_Q);
			insert = con.prepareStatement(INSERT_Q, Statement.RETURN_GENERATED_KEYS);

		} catch (SQLException e) {
			throw new DataAccessException("Could not prepare query", e);
		}
		
	}
	
	
	private List<SaleOrder> buildObjects(ResultSet rs, boolean fullAssociation) throws DataAccessException{
		List<SaleOrder> res = new ArrayList<>();
		SaleOrder o = buildObject(rs, fullAssociation);
		while(o != null) {
			res.add(o);
			o = buildObject(rs, fullAssociation);
		}
		return res;
	}
	
	private SaleOrder buildObject(ResultSet rs, boolean fullAssociation) throws DataAccessException {
		SaleOrder res = null;
		try {
			if(rs.next()) {
				res = new SaleOrder(
						rs.getInt("orderNo"),
						rs.getDouble("totalPrice"), 
						new Person(null, null, rs.getString("email_FK"), null, 0, 0),
						rs.getInt("tableNo_FK")
						);
				if(fullAssociation) {
					//En db klasse til OrderLine, så vi kan fremsøge den?
					//List<Orderline> orderline = ol.findById(res.getOrderNo());
					//res.setOl(orderline);
				}
			}
		} catch (SQLException e) {
			throw new DataAccessException("Could not build object", e);
		}
		return res;
	}
	
	
	
	@Override
	public void saveOrder(SaleOrder order) throws DataAccessException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public SaleOrder findByOrderNo(int orderNo, boolean fullAssociation) throws DataAccessException {
		SaleOrder res = null;
		try {
			findByOrderNoPS.setInt(1, orderNo);
			ResultSet rs = findByOrderNoPS.executeQuery();
			res = buildObject(rs, fullAssociation);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new DataAccessException("Could not find by OrderNo", e);
		}
		return res;
	}
	
	
}
