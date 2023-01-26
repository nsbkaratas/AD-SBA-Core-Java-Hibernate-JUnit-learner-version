package sba.sms.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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

@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Setter
@Getter
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)

@Entity
@Table(name="course")
public class Course {
	    @Id 
	    @GeneratedValue(strategy = GenerationType.IDENTITY) 
	    @Column(name = "id")
	    int id;
	    @NonNull
	    @Column(length = 50, name = "name")
	    String name;
	    @NonNull
	    @Column(length = 50, name="instructor")
	    String instructor;
	    
	    @ToString.Exclude
	    @ManyToMany(mappedBy = "courses", cascade= {CascadeType.PERSIST, CascadeType.MERGE,CascadeType.REFRESH,CascadeType.DETACH},fetch = FetchType.EAGER)
	    List<Student> student= new ArrayList<>();

		@Override
		public int hashCode() {
			return Objects.hash(id, instructor, name);
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Course other = (Course) obj;
			return id == other.id && Objects.equals(instructor, other.instructor) && Objects.equals(name, other.name);
		}
	    
}
