package sba.sms.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)

@Entity
@Table(name = "student")

public class Student {
	@NonNull
    @Id 
    @Column(length = 50,name = "email", unique = true)
    String email;
    @NonNull
    @Column(length = 50, nullable = false, name = "name")
    String name;
    @NonNull
    @Column(length = 50,name = "password")
    String password;
    
    @ToString.Exclude
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.DETACH}, fetch = FetchType.EAGER)
    @JoinTable(name = "student_courses", joinColumns = @JoinColumn(name="student_email"), inverseJoinColumns = @JoinColumn(name="course_id"))
    List<Course> course = new ArrayList<>();

	@Override
	public int hashCode() {
		return Objects.hash(email, name, password);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		return Objects.equals(email, other.email) && Objects.equals(name, other.name)
				&& Objects.equals(password, other.password);
	}
    
    
}
