package net.slipp.repository.bbs;

import net.slipp.domain.bbs.Bbs;
import net.slipp.domain.qna.Question;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface BbsRepository extends PagingAndSortingRepository<Bbs, Long>{

}