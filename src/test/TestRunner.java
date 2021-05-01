/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import SE.DB;
import java.util.ArrayList;
import java.util.List;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class TestRunner {

    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(JUnitTesting.class);

        List<Failure> failures = result.getFailures();

        for (int i = 0; i < failures.size(); i++) {
            System.out.println(failures.get(i).toString());
        }

        System.out.println(result.wasSuccessful());
    }
}
