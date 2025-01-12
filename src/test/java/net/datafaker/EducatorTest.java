package net.datafaker;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class EducatorTest extends AbstractFakerTest {

    @Test
    public void testUniversity() {
        assertThat(faker.educator().university()).matches("(\\w+ ?){2,3}");
    }

    @Test
    public void testCourse() {
        assertThat(faker.educator().course()).matches("(\\(?\\w+\\)? ?){3,6}");
    }

    @Test
    public void testSecondarySchool() {
        assertThat(faker.educator().secondarySchool()).matches("(\\w+ ?){2,3}");
    }

    @Test
    public void testCampus() {
        assertThat(faker.educator().campus()).matches("(\\w+ ?){1,2}");
    }
}
