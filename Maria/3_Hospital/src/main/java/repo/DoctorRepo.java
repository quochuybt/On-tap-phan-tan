package repo;

import java.util.List;

public class DoctorRepo extends AbstractGenericRepo{
    public List<Object[]> getDoctorWorkload() {
        return doInTransaction(em-> {
            String jpql = """
                    select d.id, d.fullName, date(ap.id.appointmentTime),count(ap)
                    from Doctor d
                    join d.appointments ap
                    group by d.id, d.fullName, date(ap.id.appointmentTime)
                    having count(ap) >= 2
                    """;
            return em.createQuery(jpql, Object[].class).getResultList();
        });
    }
}
