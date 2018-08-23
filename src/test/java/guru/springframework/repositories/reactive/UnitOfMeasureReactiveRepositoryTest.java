package guru.springframework.repositories.reactive;


import com.oracle.jrockit.jfr.EventToken;
import guru.springframework.domain.UnitOfMeasure;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@DataMongoTest
public class UnitOfMeasureReactiveRepositoryTest {

    public static final String EACH = "Each";
    public static final String FOO = "foo";

    @Autowired
    UnitOfMeasureReactiveRepository unitOfMeasureReactiveRepository;

    @Before
    public void setUp() throws Exception {
        unitOfMeasureReactiveRepository.deleteAll().block();
    }

    @Test
    public void testSaveUom() throws Exception {

        UnitOfMeasure unitOfMeasure = new UnitOfMeasure();
        unitOfMeasure.setDescription(EACH);

        unitOfMeasureReactiveRepository.save(unitOfMeasure).block();

        Long result = unitOfMeasureReactiveRepository.count().block();

        assertEquals(Long.valueOf(1L), result);
    }

    @Test
    public void testFindByDescription() throws Exception {
        UnitOfMeasure unitOfMeasure = new UnitOfMeasure();
        unitOfMeasure.setDescription(FOO);

        unitOfMeasureReactiveRepository.save(unitOfMeasure).block();

        UnitOfMeasure fetchUnitOfMeasure = unitOfMeasureReactiveRepository.findByDescription(FOO).block();

        assertNotNull(fetchUnitOfMeasure.getId());

    }


}


