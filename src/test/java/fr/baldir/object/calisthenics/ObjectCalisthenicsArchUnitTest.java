package fr.baldir.object.calisthenics;

import com.tngtech.archunit.core.importer.ClassFileImporter;
import org.junit.jupiter.api.Test;

import static fr.baldir.object.calisthenics.ObjectCalisthenicsArchUnitRules.rule_03_Wrap_All_Primitives_And_Strings;
import static fr.baldir.object.calisthenics.ObjectCalisthenicsArchUnitRules.rule_04_First_Class_Collections;
import static fr.baldir.object.calisthenics.ObjectCalisthenicsArchUnitRules.rule_08_No_Classes_With_More_Than_Two_Instance_Variables;

class ObjectCalisthenicsArchUnitTest {

    @Test
    void Only_One_Level_Of_Indentation_Per_Method() {
    }

    @Test
    void Don_t_Use_The_ELSE_Keyword() {
    }

    @Test
    void Rule_03_Wrap_All_Primitives_And_Strings() {
        rule_03_Wrap_All_Primitives_And_Strings()
                .check(new ClassFileImporter().importPackages("fr.baldir"));
    }


    @Test
    void First_Class_Collections() {
        rule_04_First_Class_Collections()
                .check(new ClassFileImporter().importPackages("fr.baldir.kata.passing"));
        try{
            rule_04_First_Class_Collections()
                    .check(new ClassFileImporter().importPackages("fr.baldir.kata.passing"));
        }catch (Exception e){
            TODO
        }

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
    void Rule_08_No_Classes_With_More_Than_Two_Instance_Variables() {
        rule_08_No_Classes_With_More_Than_Two_Instance_Variables()
                .check(new ClassFileImporter().importPackages("fr.baldir"));
    }

    @Test
    void No_Getters_Setters_Properties() {
    }
}
