package me.prytkovd.numberplate.infrastructure.output.jdbc;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AvailableNumberRepository extends CrudRepository<AvailableNumber, Integer> {
    @Query("""
        with selected_number as
        (
            select *
            from available_number
            order by number
            limit 1
            for update
        )
        delete
        from available_number
        where number = (select number from selected_number)
        returning *;
        """)
    Optional<AvailableNumber> deleteFirstReturning();

    @Query("""
        with selected_number as
        (
            select *
            from available_number
            tablesample system_rows(1)
            for update
        )
        delete
        from available_number
        where number = (select number from selected_number)
        returning *;
        """)
    Optional<AvailableNumber> deleteRandomReturning();
}
