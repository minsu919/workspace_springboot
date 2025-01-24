package entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name="users")
public class Users { // 테이블로 정의 생성
	@Id // pk 활용 insert 실행시 자동으로 1증가
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	long id;
	
	@Column(length = 20, nullable = false)
	String username;
	
	@Transient // age 변수 테이블에서 제외
	int age;
}
