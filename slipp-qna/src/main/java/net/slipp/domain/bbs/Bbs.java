package net.slipp.domain.bbs;

import java.util.Collection;
import java.util.Date;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

import net.slipp.domain.CreatedAndUpdatedDateEntityListener;
import net.slipp.domain.HasCreatedAndUpdatedDate;
import net.slipp.domain.qna.Question;
import net.slipp.domain.user.User;


@Entity
@EntityListeners({ CreatedAndUpdatedDateEntityListener.class })
public class Bbs implements HasCreatedAndUpdatedDate {
	
	private static final Logger logger = LoggerFactory.getLogger(Bbs.class);
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long bbsId;
	
	@Column(name = "title", length=100, nullable = false)
	private String title;
	
	@Column(name = "writer_id", nullable = false)
	private String writerId;
	
	@Column(name = "writer_name", nullable = false)
	private String writerName;	
	
	@Column(name = "fileName")
	private String fileName;	
	
	@ElementCollection(fetch = FetchType.LAZY)
	@CollectionTable(name = "bbs_content_holder", joinColumns = @JoinColumn(name = "bbs_id", unique = true))
	@org.hibernate.annotations.ForeignKey(name = "fk_bbs_content_holder_bbs_id")
	@Lob
	@Column(name = "contents", nullable = false)
	private Collection<String> contentsHolder;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_date", nullable = false, updatable = false)
	private Date createdDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated_date", nullable = false)
	private Date updatedDate;
	
	public Bbs() {
		
	}
	
	Bbs(String writerId, String writerName, String title, String contents) {
		this.writerId = writerId;
		this.writerName = writerName;
		this.title = title;

		setContents(contents);
	}
	
	public Long getBbsId() {
		return bbsId;
	}
	
	public void setBbsId(Long bbsId) {
		this.bbsId = bbsId;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}	

	public void setContents(String newContents) {
		if (isEmptyContentsHolder()) {
			contentsHolder = Lists.newArrayList(newContents);
		} else {
			contentsHolder.clear();
			contentsHolder.add(newContents);
		}
	}
	
	private boolean isEmptyContentsHolder() {
		return contentsHolder == null || contentsHolder.isEmpty();
	}

	public String getContents() {
		if (isEmptyContentsHolder()) {
			return "";
		}

		return Iterables.getFirst(contentsHolder, "");
	}
	
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	@Override
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	@Override
	public Date getCreatedDate() {
		return createdDate;
	}

	@Override
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	@Override
	public Date getUpdatedDate() {
		return updatedDate;
	}
	
	public void writedBy(User user) {
		this.writerId = user.getUserId();
		this.writerName = user.getName();
	}

	public String getWriterId() {
		return writerId;
	}

	public String getWriterName() {
		return writerName;
	}
	
	public void update(Bbs newBbs) {
		this.title = newBbs.title;
		this.contentsHolder = newBbs.contentsHolder;
	}	
	
	@Override
	public String toString() {
		return "Bbs [title=" +  title + ",contentsHolder=" + contentsHolder + ",fileName =" + fileName  +  "]";
	}
}
