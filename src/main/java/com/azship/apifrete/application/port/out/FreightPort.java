package com.azship.apifrete.application.port.out;


import com.azship.apifrete.domain.Freight;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;

public interface FreightPort {

    Freight findById(Long id);

    List<Freight> listAll();

    Page<Freight> searchByAttribute(String attributeValue, Pageable pageable);


    Freight save(Freight freight);

    Freight update(Long id, Freight freight);

    void deleteById(Long id);
}
