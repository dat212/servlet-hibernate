
import java.sql.Date;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import DAO.KhachHangDAO;
import model.KhachHang;
import util.HibernateUtil;

public class test {
	public static void main(String[] args) {
		KhachHangDAO khachHangDAO = new KhachHangDAO();
		KhachHang kh1 = 
		new KhachHang("kh1", "dat12", "123", "Luong Quoc Dat", "Nam", 
				"HN", "BG", "HN", new Date(1997, 02, 21), "0886216861", "dat@gmail.com", true );
		khachHangDAO.saveKhachHang(kh1);
		khachHangDAO.insert(kh1);
		
	}

}

