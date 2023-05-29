package DAO;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import model.KhachHang;
import util.HibernateUtil;

public class KhachHangDAO implements DAOInterface<KhachHang> {

	@Override
	public List<KhachHang> selectAll() {
		try(Session session = HibernateUtil.getSessionFactory().openSession()) {
			return session.createQuery("from KhachHang", KhachHang.class).list();
		} 
	}

	@Override
	public KhachHang selectById(String id) {
		
		Transaction tr = null;
		KhachHang khachHang = null;
		
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			tr = session.beginTransaction();
			//get a KH object
			String hql = "FROM KhachHang kh WHERE kh.id = :maKhachHang ";
			Query query = session.createQuery(hql);
			query.setParameter("maKhachHang", id);
			List results = query.getResultList();
			
			if (results != null && !results.isEmpty()) {
				khachHang = (KhachHang) results.get(0);
				
			}
			tr.commit();
			
		} catch (Exception e) {
//			if (tr != null) {
//				tr.rollback();
//			}
			e.printStackTrace();
		}
		return khachHang;
	}
	
	public void saveKhachHang(KhachHang kh) {
		Transaction tr = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// start a transaction
			tr = session.beginTransaction();
			
			// operation 
			Object object = session.save(kh);
			
			// operation 2
			session.get(KhachHang.class, (Serializable) object);
			System.out.println(object);
			// commit transaction
			tr.commit();
			session.close();
		} catch (Exception e) {
//			if (tr != null) {
//				tr.rollback();
//			}
			e.printStackTrace();
		}
	}

	@Override
	public void insert(KhachHang t) {
		Transaction tr = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			tr = session.beginTransaction();
//			String hql = "INSERT INTO KhachHang "
//					+ "(maKhacHang, tenDangNhap, matKhau, hoVaTen, gioiTinh, diaChi, diaChiNhanHang, diaChiMuaHang, ngaySinh, soDienThoai, email, dangKyNhanBangTin )"
//					+"SELECT maKhacHang, tenDangNhap, matKhau, hoVaTen, gioiTinh, diaChi,"
//					+ "diaChiNhanHang, diaChiMuaHang, ngaySinh, soDienThoai, email, dangKyNhanBangTin FROM KhachHang";
//			Query query = session.createQuery(hql);
//			int result = query.executeUpdate();
//			System.out.println("row affects : "+result);
			session.save(t);
			//commit transaction
			tr.commit();
			System.out.println(t);

		} catch (Exception e) {
//			if (tr != null) {
//				tr.rollback();
//			}
			e.printStackTrace();
		}
	}

	@Override
	public void update(KhachHang t) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// start a transaction
			transaction = session.beginTransaction();

			// save the student object
			String hql = "UPDATE KhachHang set tenDangNhap = :tenDangNhap " + "WHERE id = :maKhachHang";
			Query query = session.createQuery(hql);
			query.setParameter("tenDangNhap", t.getTenDangNhap());
			query.setParameter("maKhachHang", t.getMaKhacHang());
			int result = query.executeUpdate();
			System.out.println("Rows affected: " + result);

			// commit transaction
			transaction.commit();
		} catch (Exception e) {
//			if (transaction != null) {
//				transaction.rollback();
//			}
			e.printStackTrace();
		}
	}

	@Override
	public void delete(String id) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// start a transaction
			transaction = session.beginTransaction();

			// Delete a KhachHang object
			KhachHang kh = session.get(KhachHang.class, id);
			if (kh != null) {
				String hql = "DELETE FROM KhachHang " + "WHERE id = :maKhachHang";
				Query query = session.createQuery(hql);
				query.setParameter("studentId", id);
				int result = query.executeUpdate();
				System.out.println("Rows affected: " + result);
			}

			// commit transaction
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}
	public boolean kiemTraTenDangNhap(String tenDangNhap) {
		boolean ketQua = false;
		Transaction tr = null;
		try(Session session = HibernateUtil.getSessionFactory().openSession()) {
			// Bước 1: tạo kết nối đến CSDL
			tr = session.beginTransaction();

			String hql = "FROM KhachHang kh WHERE kh.tenDangNhap = :tenDangNhap";
			Query query = session.createQuery(hql);
			query.setParameter("tenDangNhap", tenDangNhap);
			List result = query.getResultList();
			tr.commit();
			if (result.size() > 0) {
				ketQua = true;
				System.out.println("Ten dang nhap da ton tai");
			}

			

			// Bước 5:
			session.close();
			System.out.println(result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return ketQua;
	}
	
	public KhachHang selectByUserNameAndPassword(KhachHang t) {
		
		KhachHang ketQua = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			Transaction tr = session.beginTransaction();
			String tenDangNhap = t.getTenDangNhap();
			String matKhau = t.getMatKhau();
			
			String hql = "FROM KhachHang kh Where kh.tenDangNhap = :tenDangNhap and kh.matKhau = :matKhau";
			Query query = session.createQuery(hql);
			query.setParameter("tenDangNhap", tenDangNhap);
			query.setParameter("matKhau", matKhau);
			ketQua = (KhachHang)query.getSingleResult();
			tr.commit();
			//System.out.println(results);
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		System.out.println(ketQua);
		
		return ketQua;
	}
	
}
