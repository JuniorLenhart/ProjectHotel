package hotel.repository;

import hotel.config.HibernateUtil;
import hotel.model.Quarto;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.jdbc.Work;

public class QuartoRepository {

    public static List<Quarto> readAll() {
        Query query = HibernateUtil.getSession().createQuery("FROM Quarto");
        return query.list();
    }

    public static List<Quarto> read(String pParam) {
        Query query = HibernateUtil.getSession().createQuery("FROM Quarto WHERE numQuarto LIKE :numQuarto");
        query.setParameter("numQuarto", "%" + pParam.toLowerCase() + "%");
        return query.list();
    }

    public static Quarto readId(int pCodigo) {
        Query query = HibernateUtil.getSession().createQuery("FROM Quarto WHERE codQuarto = " + pCodigo);
        return (Quarto) query.uniqueResult();
    }

    public static List<Quarto> selectQuartos(String pDtaEntrada, String pDtaSaida, int pQtdLugar) {
        List<Quarto> listQuarto = new ArrayList();

        HibernateUtil.getSession().doWork(new Work() {
            public void execute(Connection connection) throws SQLException {
                CallableStatement call = connection.prepareCall("{call select_quartos(?, ?, ?)}");
                call.setString(1, pDtaEntrada);
                call.setString(2, pDtaSaida);
                call.setInt(3, pQtdLugar);
                ResultSet rs = call.executeQuery();
                if (rs.next()) {
                    listQuarto.add(readId(rs.getInt("cod_quarto")));
                }
            }
        });

        return listQuarto;
    }
}
