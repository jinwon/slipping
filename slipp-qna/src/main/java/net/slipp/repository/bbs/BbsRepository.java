package net.slipp.repository.bbs;


import net.slipp.domain.bbs.Bbs;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

//public interface BbsRepository extends PagingAndSortingRepository<Bbs, Long>{	
public interface BbsRepository extends JpaRepository<Bbs, Long>{	
	
	Page<Bbs> findByTitleLike(String title, Pageable page);
}