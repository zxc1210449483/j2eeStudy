package com.zxc.j2eeStudy.lesson8_facelets;

/**
 * @program: j2eeStudy
 * @description:
 * @author: Xiangchun Zeng
 * @create: 2018-06-26 17:26
 **/
public class Author {

	private String First;

	private String last;

	private String bio;

	public Author() {
	}

	public Author(String first, String last, String bio) {
		First = first;
		this.last = last;
		this.bio = bio;
	}

	public String getFirst() {
		return First;
	}

	public void setFirst(String first) {
		First = first;
	}

	public String getLast() {
		return last;
	}

	public void setLast(String last) {
		this.last = last;
	}

	public String getBio() {
		return bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}
}
