package fr.baldir.object.calisthenics;

import com.tngtech.archunit.core.domain.JavaClass;
import com.tngtech.archunit.core.domain.JavaField;
import com.tngtech.archunit.core.domain.JavaType;
import com.tngtech.archunit.lang.ArchCondition;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.lang.ConditionEvents;
import com.tngtech.archunit.lang.SimpleConditionEvent;
import fr.baldir.annotations.object.calisthenics.Rule_03_Wrap_All_Primitives_And_Strings;
import fr.baldir.annotations.object.calisthenics.Rule_04_First_Class_Collections;
import fr.baldir.annotations.object.calisthenics.Rule_08_No_Classes_With_More_Than_Two_Instance_Variables;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.fields;

public final class ObjectCalisthenicsArchUnitRules {
    public static ArchRule rule_03_Wrap_All_Primitives_And_Strings() {
        return fields().that().areDeclaredInClassesThat().areAnnotatedWith(Rule_03_Wrap_All_Primitives_And_Strings.class)
                .should().notHaveRawType("java.lang.Integer")
                .andShould().notHaveRawType("java.lang.Long")
                .andShould().notHaveRawType("java.math.BigDecimal")
                .andShould().notHaveRawType("short")
                .andShould().notHaveRawType("long")
                .andShould().notHaveRawType("java.lang.Float")
                .andShould().notHaveRawType("char")
                .andShould().notHaveRawType("java.lang.Character")
                .andShould().notHaveRawType("java.lang.CharSequence")
                .andShould().notHaveRawType("java.lang.Byte")
                .andShould().notHaveRawType("float")
                .andShould().notHaveRawType("double")
                .andShould().notHaveRawType("java.util.concurrent.atomic.AtomicLong")
                .andShould().notHaveRawType("java.lang.Double")
                .andShould().notHaveRawType("byte")
                .andShould().notHaveRawType("java.lang.String")
                .andShould().notHaveRawType("int")
                .andShould().notHaveRawType("java.util.concurrent.atomic.AtomicBoolean")
                .andShould().notHaveRawType("java.util.concurrent.atomic.AtomicInteger")
                .andShould().notHaveRawType("java.lang.Boolean")
                .andShould().notHaveRawType("java.math.BigInteger")
                .andShould().notHaveRawType("boolean")
                .andShould().notHaveRawType("java.lang.Short")
                .as("\n\nObject Calisthenics - 03 - Wrap all primitives and strings\n\n")
                .because("\n" +
                         "\n   - You cannot have method arguments (except in constructors) that are primitive types (`int`, `float`, `double`, `bool`, `string`, `char`,...)." +
                         "\n   - You cannot have method return values that are primitive types (`int`, `float`, `double`, `bool`, `string`, `char`,...)." +
                         "\n   - For every primitive type that you need to pass around, create a class to act as home for related behaviors." +
                         "\n   - It is okay to have primitive types in class private member variables." +
                         "\n" +
                         "\nWhy?\n" +
                         "\n   - Primitives are super types that have only contextual meaning. They can express anything so we tend to use them for everything." +
                         "\n   - Make the code more explicit by expressing intent through types that become behavior attractors." +
                         "\n   - Objects will have inherent meaning." +
                         "\n   - Cure for Primitive Obsession code smell." +
                         "\n\n")
                ;

    }

    public static ArchRule rule_08_No_Classes_With_More_Than_Two_Instance_Variables() {

        return classes().that()
                .areAnnotatedWith(Rule_08_No_Classes_With_More_Than_Two_Instance_Variables.class)
                .should(new ArchCondition<JavaClass>(
                        "Object Calisthenics - 08 - No classes with more than two instance variables") {
                    @Override
                    public void check(JavaClass item, ConditionEvents events) {
                        int numberOfInstanceVariables = item.getAllFields().size();
                        if (numberOfInstanceVariables > 2) {
                            events.add(SimpleConditionEvent.violated(item,
                                    item.getFullName() + " has " + numberOfInstanceVariables + " instance variables"));
                        }
                    }
                })
                .as("\n" +
                    "\nObject Calisthenics - 08 - No classes with more than two instance variables" +
                    "\n\n")
                .because("\n" +
                         "\n   - The more instance variables, the lower the Cohesion within the class" +
                         "\n   - Usually classes with more than one instance variable are orchestrators," +
                         "\n     and classes with one instance variables are actuators. Separate the " +
                         "\n     responsibilities." +
                         "\n\n");
    }

    private final static List<String> COLLECTIONS_INTERFACES = Arrays.asList("java.util.Collection", "java.lang.Iterable", "java.util.Set");

    public static ArchRule rule_04_First_Class_Collections() {
        return classes().that().areAnnotatedWith(Rule_04_First_Class_Collections.class)
                .should(new ArchCondition<JavaClass>("04_First_Class_Collections") {
                    @Override
                    public void check(JavaClass item, ConditionEvents events) {

                        Set<JavaField> allFields = item.getAllFields();

                        boolean hasCollection = allFields.stream()
                                .map(JavaField::getRawType)
                                .flatMap(javaClass -> javaClass.getInterfaces().stream())
                                .map(JavaType::toErasure)
                                .map(javaType -> javaType.getName())
                                .anyMatch(s -> COLLECTIONS_INTERFACES.contains(s));

                        // rawType begins with : "["
                        boolean hasArray = allFields.stream().map(JavaField::getRawType)
                                .map(JavaClass::getFullName)
                                .anyMatch(s -> s.startsWith("["));

                        if (hasCollection || hasArray) {
                            events.add(SimpleConditionEvent.violated(
                                    item,
                                    item.getFullName() + " contains at least an unwrapped collection or array"));
                        }
                    }
                })
                .as("\n" +
                    "\nObject Calisthenics - 04 - First class collections" +
                    "\n\n")
                .because("\n" +
                         "   - You cannot have method arguments (except in constructors) that are collections (array, hash table, set, list, ...).\n" +
                         "   - Create a class for all collections, even if a collection is just a private member variable in a class. This gives collection behaviors a home.\n" +
                         "   - Any class that contains a collection should contain no other member variables.\n" +
                         "\n" +
                         "Why?\n" +
                         "\n" +
                         "   - Consider collections as primitives; this way any behavior specific to your collection will be attracted to a single place.\n" +
                         "   - Filters will become part of the class.\n" +
                         "   - Joins or special rules applied to collection elements will be contained within the class.\n" +
                         "   - Changing the internal representation won’t affect the clients that improve decoupling." +
                         "\n\n");
    }
}
