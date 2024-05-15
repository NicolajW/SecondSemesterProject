package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.OrderLine;
import model.SaleOrder;

public class OrderLineDB implements OrderLineDAO{
	private static final String FIND_ALL_Q = "Select id, quantity, orderNo_FK, saleProductID_FK from OrderLine";
	private static final String FIND_BY_ID_Q = 
			FIND_ALL_Q + "where id = ?";
	private static final String INSERT_Q = "INSERT INTO OrderLine (quantity, orderNo_FK, saleProductID_FK) Values (?, ?, ?)";
	private PreparedStatement findAllPSS;
	private PreparedStatement findByOrderNoPS;
	private PreparedStatement insert;
	private SaleOrder so;
	
	public OrderLineDB() throws DataAccessException {
		Connection con = DBConnection.getInstance().getConnection();
		try {
			findAllPSS = con.prepareStatement(FIND_ALL_Q);
			findByOrderNoPS = con.prepareStatement(FIND_ALL_Q);
			insert = con.prepareStatement(INSERT_Q, Statement.RETURN_GENERATED_KEYS);
		} catch (SQLException e) {
			throw new DataAccessException("Could not prepare query", e);
		}
	}
	
	private OrderLine buildObject(ResultSet rs) throws DataAccessException {
		OrderLine res = null;
		try {
		if(rs.next()) {
			res = new OrderLine(rs.getFloat("quantity"), null, so);
			}
		} catch (SQLException e) {
			throw new DataAccessException("Could not build object", e);
		}
		return res;
	}
	
	private List<OrderLine> buildObjects(ResultSet rs) throws DataAccessException {
		List<OrderLine> res = new ArrayList<>();
		OrderLine o = buildObject(rs);
		while (o!= null) {
			res.add(o);
			o = buildObject(rs);
		}
		return res;
		
	}
	@Override
	public List<OrderLine> findAll() throws DataAccessException {
		List<OrderLine> res = new ArrayList<>();
		return res;
	}

	@Override
	public List<OrderLine> findById(int orderNo) throws DataAccessException {
		List <OrderLine> res = null;
			try {
				findByOrderNoPS.setInt(1, orderNo);
				ResultSet rs = findByOrderNoPS.executeQuery();
				res = buildObjects(rs);
			} catch (SQLException e) {
				throw new DataAccessException("could not find by OrderNo", e);
			}
		return res;
	}
	public void saveOrderLine(OrderLine orderLine) throws DataAccessException {
		final int quantity = orderLine.getQuantity();
		final int saleOrderNo_FK = orderLine.getSaleOrder().getOrderNo();
		final int saleProductID_FK = orderLine.getSaleProduct().getSaleProductID();
		try {
			insert.setInt(1, quantity);
			insert.setInt(2, saleOrderNo_FK);
			insert.setInt(3, saleProductID_FK);	
			
			insert.executeUpdate();
		}	catch (SQLException e) {
			throw new DataAccessException("Could not save OrderLine", e);
		}
		
	}
}