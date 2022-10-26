package fr.baldir.object.calisthenics;

import com.tngtech.archunit.lang.ArchRule;
import fr.baldir.annotations.object.calisthenics.Rule_03_Wrap_All_Primitives_And_Strings;

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
                .as("Object Calisthenics - 03 - Wrap all primitives and strings")
                .because("\n" +
                         "\n" +
                         "   - Primitives are super types that have only contextual meaning. They can express anything so we tend to use them for everything.\n" +
                         "   - Make the code more explicit by expressing intent through types that become behavior attractors.\n" +
                         "   - Objects will have inherent meaning.\n" +
                         "   - Cure for Primitive Obsession code smell.\n" +
                         "\n")
               ;

    }
}
