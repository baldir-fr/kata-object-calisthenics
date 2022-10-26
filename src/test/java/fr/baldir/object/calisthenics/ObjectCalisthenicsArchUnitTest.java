package fr.baldir.object.calisthenics;
import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import org.junit.jupiter.api.Test;

class ObjectCalisthenicsArchUnitTest {

    @Test
    void Only_One_Level_Of_Indentation_Per_Method() {
    }

    @Test
    void Don_t_Use_The_ELSE_Keyword() {
    }

    @Test
    void Rule_03_Wrap_All_Primitives_And_Strings() {

        JavaClasses importedClasses = new ClassFileImporter().importPackages("fr.baldir");
        ObjectCalisthenicsArchUnitRules.rule_03_Wrap_All_Primitives_And_Strings()
                .check(importedClasses);
    }



    @Test
    void First_Class_Collections() {
    }

    @Test
    void One_Dot_Per_Line() {
    }

    @Test
    void Don_t_Abbreviate() {
    }

    @Test
    void Keep_All_Entities_Small() {
    }

    @Test
    void No_Classes_With_More_Than_Two_Instance_Variables() {
    }

    @Test
    void No_Getters_Setters_Properties() {
    }
}
