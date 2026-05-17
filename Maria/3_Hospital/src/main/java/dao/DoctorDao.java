package dao;

import entity.Doctor;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class DoctorDao extends AbstractGenericDao<Doctor, String> {
    public DoctorDao() {
        super(Doctor.class);
    }

    public List<Object[]> getDoctorWorkload() {
        return doInTransaction(em -> {
            String jpql = """
                    select d.id, d.fullName, date(ap.id.appointmentTime), count (ap)
                    from Doctor d
                    join d.appointments ap
                    group by d.id, d.fullName, date(ap.id.appointmentTime)
                    having count(ap) >= 2
                    """;
            TypedQuery<Object[]> query = em.createQuery(jpql, Object[].class);
            return query.getResultList();
        });
    }
}
