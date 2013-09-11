	create database slippqna character set utf8 collate utf8_general_ci;
	
	GRANT ALL ON slippqna.* to samjung@localhost identified by 'apdlfskfk';
	
	set character_set_results = 'utf8';
	set character_set_client = 'utf8';
	set character_set_connection = 'utf8';

	-- FLUSH PRIVILEGES;
	
	use slippqna;

    alter table answer 
        drop 
        foreign key fk_answer_parent_id;

    alter table answer 
        drop 
        foreign key fk_answer_user_id;

    alter table bbs_content_holder 
        drop 
        foreign key fk_bbs_content_holder_bbs_id;

    alter table question_content_holder 
        drop 
        foreign key fk_question_content_holder_question_id;

    alter table question_tag 
        drop 
        foreign key fk_question_tag_question_id;

    alter table question_tag 
        drop 
        foreign key fk_question_tag_tag_id;

    alter table tag 
        drop 
        foreign key fk_tag_parent_id;

    drop table if exists answer;

    drop table if exists bbs;

    drop table if exists bbs_content_holder;

    drop table if exists question;

    drop table if exists question_content_holder;

    drop table if exists question_tag;

    drop table if exists tag;

    drop table if exists user;

    create table answer (
        answer_id bigint not null auto_increment,
        contents varchar(255),
        created_date datetime not null,
        updated_date datetime not null,
        question bigint,
        user varchar(255),
        primary key (answer_id)
    );

    create table bbs (
        bbs_id bigint not null auto_increment,
        created_date datetime not null,
        file_name varchar(255),
        title varchar(100) not null,
        updated_date datetime not null,
        writer_id varchar(255) not null,
        writer_name varchar(255) not null,
        primary key (bbs_id)
    );

    create table bbs_content_holder (
        bbs_id bigint not null unique,
        contents longtext not null
    );

    create table question (
        question_id bigint not null auto_increment,
        answer_count integer not null,
        created_date datetime not null,
        show_count integer not null,
        title varchar(100) not null,
        updated_date datetime not null,
        writer_id varchar(255) not null,
        writer_name varchar(255) not null,
        primary key (question_id)
    );

    create table question_content_holder (
        question_id bigint not null unique,
        contents longtext not null
    );

    create table question_tag (
        question_id bigint not null,
        tag_id bigint not null,
        primary key (question_id, tag_id)
    );

    create table tag (
        tag_id bigint not null auto_increment,
        name varchar(50) not null,
        tagged_count integer not null,
        parent bigint,
        primary key (tag_id)
    );

    create table user (
        user_id varchar(255) not null,
        email varchar(255),
        is_admin bit not null,
        name varchar(255),
        password varchar(255),
        primary key (user_id)
    );

    alter table answer 
        add index fk_answer_parent_id (question), 
        add constraint fk_answer_parent_id 
        foreign key (question) 
        references question (question_id);

    alter table answer 
        add index fk_answer_user_id (user), 
        add constraint fk_answer_user_id 
        foreign key (user) 
        references user (user_id);

    alter table bbs_content_holder 
        add index fk_bbs_content_holder_bbs_id (bbs_id), 
        add constraint fk_bbs_content_holder_bbs_id 
        foreign key (bbs_id) 
        references bbs (bbs_id);

    alter table question_content_holder 
        add index fk_question_content_holder_question_id (question_id), 
        add constraint fk_question_content_holder_question_id 
        foreign key (question_id) 
        references question (question_id);

    alter table question_tag 
        add index fk_question_tag_question_id (question_id), 
        add constraint fk_question_tag_question_id 
        foreign key (question_id) 
        references question (question_id);

    alter table question_tag 
        add index fk_question_tag_tag_id (tag_id), 
        add constraint fk_question_tag_tag_id 
        foreign key (tag_id) 
        references tag (tag_id);

    alter table tag 
        add index fk_tag_parent_id (parent), 
        add constraint fk_tag_parent_id 
        foreign key (parent) 
        references tag (tag_id);
