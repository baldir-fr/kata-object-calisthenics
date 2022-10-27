package fr.baldir.kata.failing;

import fr.baldir.annotations.object.calisthenics.Rule_04_First_Class_Collections;

@Rule_04_First_Class_Collections
public class Failing_04_First_Class_Collections_a_collection_among_multiple_fields {

    // Only 1 field which is a collection is a first class collection
    int[] aArrayOfInt;
    String field2;

}
