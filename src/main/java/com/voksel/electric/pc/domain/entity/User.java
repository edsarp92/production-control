package com.voksel.electric.pc.domain.entity;


import org.springframework.data.elasticsearch.annotations.Document;
import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


@Document(indexName = "sys_user")
@Entity
@Table(name = "sys_user")
public class User implements Serializable {

	@javax.persistence.Id
	@org.springframework.data.annotation.Id
    @GeneratedValue(strategy = GenerationType.AUTO)    
    @Column(name="user_id")
    private Integer userId;

	@Column(name = "user_name")
    private String userName;   

	@Column(name = "password", updatable = false)
    private String password;   

	@Column(name = "email")
    private String email;
    
	@Column(name ="enabled")
	private Integer enabled;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user", fetch = FetchType.EAGER)
	private List<UserRole> roles;
	
	public User(){
		
	}
	
	public User(User user) {
	        this.userId = user.userId;
	        this.userName = user.userName;
	        this.email = user.email;       
	        this.password = user.password;
	        this.enabled=user.enabled;        
	}
	
	public Integer getEnabled() {
		return enabled;
	}

	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}	

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userid) {
		this.userId = userid;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public List<UserRole> getRoles() {
		return roles;
	}

	public void setRoles(List<UserRole> roles) {
		this.roles = roles;
	}

}