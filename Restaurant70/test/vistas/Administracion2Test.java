/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas;

import javax.swing.JDesktopPane;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author john
 */
public class Administracion2Test {
	
	public Administracion2Test() {
	}
	
	@BeforeClass
	public static void setUpClass() {
	}
	
	@AfterClass
	public static void tearDownClass() {
	}
	
	@Before
	public void setUp() {
	}
	
	@After
	public void tearDown() {
	}

	/**
	 * Test of getEscritorio method, of class Administracion2.
	 */
	@Test
	public void testGetEscritorio() {
		System.out.println("getEscritorio");
		Administracion2 instance = new Administracion2();
		JDesktopPane expResult = null;
		JDesktopPane result = instance.getEscritorio();
		assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of main method, of class Administracion2.
	 */
	@Test
	public void testMain() {
		System.out.println("main");
		String[] args = null;
		Administracion2.main(args);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}
	
}
