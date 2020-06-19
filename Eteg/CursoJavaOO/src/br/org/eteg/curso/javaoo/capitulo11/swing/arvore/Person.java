package br.org.eteg.curso.javaoo.capitulo11.swing.arvore;

import java.util.Vector;

public class Person {
    Person father;
    Person mother;
    Vector<Person> children;
    private String name;

    public Person(String name) {
        this.name = name;
        mother = father = null;
        children = new Vector<Person>();
    }

    /**
    *   Link together all members of a family.
    *
    *   @param pa the father
    *   @param ma the mother
    *   @param kids the children
    */
    public static void linkFamily(Person pa,
                                  Person ma,
                                  Person[] kids) {
        for (Person kid : kids) {
            pa.children.addElement(kid);
            ma.children.addElement(kid);
            kid.father = pa;
            kid.mother = ma;
        }
    }

/// getter methods ///////////////////////////////////

    public String toString() { return name; }
    public String getName() { return name; }
    public Person getFather() { return father; }
    public Person getMother() { return mother; }
    public int getChildCount() { return children.size(); }
    public Person getChildAt(int i) {
        return (Person)children.elementAt(i);
    }
    public int getIndexOfChild(Person kid) {
        return children.indexOf(kid);
    }
}
