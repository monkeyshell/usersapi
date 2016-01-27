package com.users.jpa;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "account")
public class Account implements UserDetails{

	private static final long serialVersionUID = 513814051405041265L;
	private Long id;
    private String username;
    private String password;
    private String clientId;
    private Long creationTime;
    private Set<Role> roles = new HashSet<Role>();

    public Account() {
        super();
    }

    public Account(final String username) {
        this.username = username;
    }

    // API

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    public Long getId() {
        return id;
    }

    @SuppressWarnings("unused")
    private void setId(final Long id) {
        this.id = id;
    }

    @Column(name = "username")
    public String getUsername() {
        return username;
    }

    public void setUsername(final String username) {
        this.username = username;
    }
    
	@Column(name = "creationTime")
    public Long getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(Long creationTime) {
		this.creationTime = creationTime;
	}

    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "account_role", joinColumns = { @JoinColumn(name = "account_id") }, inverseJoinColumns = { @JoinColumn(name = "role_id") })
    public Set<Role> getRoles() {
        return roles;
    }

    @Column(name = "clientId")
	public String getClientId(){
		return clientId;
	}
	
	public void setClientId(String clientId){
		this.clientId = clientId;
	}

	public void setRoles(final Set<Role> roles) {
        this.roles = roles;
    }

    @Transient
    public Set<Permission> getPermissions() {
        final Set<Permission> perms = new HashSet<Permission>();
        for (final Role role : roles) {
            perms.addAll(role.getPermissions());
        }
        return perms;
    }

    /* (non-Javadoc)
     * @see org.springframework.security.core.userdetails.UserDetails#getAuthorities()
     */
    @Transient
    public Collection<GrantedAuthority> getAuthorities() {
        final Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
        authorities.addAll(getRoles());
        authorities.addAll(getPermissions());
        return authorities;
    }
	
    @Override
    public String toString() {
        return username;
    }

	@Override
	@Transient
    public boolean isAccountNonExpired(){
	   // return isVerified == 1 ? true : false;
		return true;
    }

	@Override
	@Transient
    public boolean isAccountNonLocked(){
	    return true;
    }

	@Override
	@Transient
    public boolean isCredentialsNonExpired(){
	    return true;
    }

	@Override
	@Transient
    public boolean isEnabled(){
	    return true;
    }
}
