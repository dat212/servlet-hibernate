
import java.sql.Date;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import DAO.KhachHangDAO;
import model.KhachHang;
import util.HibernateUtil;

public class test1 {
	public static void main(String[] args) {
		KhachHangDAO khachHangDAO = new KhachHangDAO();
		KhachHang kh1 = 
		new KhachHang("kh5","dat222", "123", "Luong Quoc Chinh", "Nam", 
				"HN", "HP", "HN", new Date(1997, 02, 21), "0886216861", "dat@gmail.com", true );
//		khachHangDAO.saveKhachHang(kh1);
//		khachHangDAO.insert(kh1);
//		khachHangDAO.kiemTraTenDangNhap("dat2");
//		List<KhachHang> list = khachHangDAO.selectAll();
//		list.forEach(s-> System.out.println(s.getHoVaTen()));
//		KhachHang kh =khachHangDAO.selectById("kh1");
//		System.out.println(kh.getHoVaTen());
//		khachHangDAO.update(kh1);
		khachHangDAO.selectByUserNameAndPassword(kh1);
		
	}

}

