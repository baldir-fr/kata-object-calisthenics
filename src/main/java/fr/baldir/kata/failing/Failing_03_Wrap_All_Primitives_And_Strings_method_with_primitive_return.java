package fr.baldir.kata.failing;

import fr.baldir.annotations.object.calisthenics.Rule_03_Wrap_All_Primitives_And_Strings;

@Rule_03_Wrap_All_Primitives_And_Strings
@SuppressWarnings("java:S101")
public class Failing_03_Wrap_All_Primitives_And_Strings_method_with_primitive_return {
    public String aMethodWithPrimitiveReturn() {
        return null;
    }
}
