package com.zxc.j2eeStudy.lesson8_facelets;

import java.io.Serializable;
import java.util.Random;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 * @program: j2eeStudy
 * @description:
 * @author: Xiangchun Zeng
 * @create: 2018-06-25 15:21
 **/
@Named
@SessionScoped
public class UserNumberBean implements Serializable {

	private static final long serialVersionUID = 1L;

	Integer randomInt = null;
	private Integer userNumber = null;
	String response = null;
	private long maximum = 10;
	private long minimum = 0;

	public UserNumberBean() {
		Random randomGe = new Random();
		randomInt = randomGe.nextInt((int) maximum + 1);
		System.out.println("Duke's number:" + randomInt);
	}

	public Integer getUserNumber() {
		return userNumber;
	}

	public void setUserNumber(Integer userNumber) {
		this.userNumber = userNumber;
	}

	public String getResponse() {
		if ((userNumber != null) && (userNumber.compareTo(randomInt) == 0)) {
			return "Yay! You got it!";
		} else {
			return "Sorry, " + userNumber + " is incorrect.";
		}
	}

	public long getMaximum() {
		return maximum;
	}

	public void setMaximum(long maximum) {
		this.maximum = maximum;
	}

	public long getMinimum() {
		return minimum;
	}

	public void setMinimum(long minimum) {
		this.minimum = minimum;
	}
}
