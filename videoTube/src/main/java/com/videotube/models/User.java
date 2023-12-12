package com.videotube.models;



import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.annotations.ManyToAny;
import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "User")
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User implements UserDetails,CredentialsContainer{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    @Column(nullable = false,unique = true)
    private String username;
    private String email;
    @JsonProperty(access = Access.WRITE_ONLY)
    private String password;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "userAuth", joinColumns = @JoinColumn(referencedColumnName = "userId"),
    inverseJoinColumns = @JoinColumn(referencedColumnName = "id"))
    private Set<RolesAndAuthority> authSet= new HashSet<>();
    @JsonIgnore
    private LocalDate registrationDate;
    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Video> videos;
    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<Comment> comments;
    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<Like> likes;
    @JsonIgnore
    @OneToMany(mappedBy = "subscriber")
    private List<Subscription> subscriptions;
    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<WatchHistory> watchHistory;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		 
		return  null;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	
	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void eraseCredentials() {
		this.password=null;
		
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.username;
	}
}
