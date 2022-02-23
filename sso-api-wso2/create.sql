-- public.reg_association definition

-- Drop table

-- DROP TABLE public.reg_association;

CREATE TABLE public.reg_association (
	reg_association_id int4 NOT NULL DEFAULT nextval('reg_association_pk_seq'::regclass),
	reg_sourcepath varchar(2000) NOT NULL,
	reg_targetpath varchar(2000) NOT NULL,
	reg_association_type varchar(2000) NOT NULL,
	reg_tenant_id int4 NOT NULL DEFAULT 0,
	CONSTRAINT reg_association_pkey PRIMARY KEY (reg_association_id, reg_tenant_id)
);


-- public.reg_cluster_lock definition

-- Drop table

-- DROP TABLE public.reg_cluster_lock;

CREATE TABLE public.reg_cluster_lock (
	reg_lock_name varchar(20) NOT NULL,
	reg_lock_status varchar(20) NULL,
	reg_locked_time timestamp NULL,
	reg_tenant_id int4 NULL DEFAULT 0,
	CONSTRAINT reg_cluster_lock_pkey PRIMARY KEY (reg_lock_name)
);


-- public.reg_comment definition

-- Drop table

-- DROP TABLE public.reg_comment;

CREATE TABLE public.reg_comment (
	reg_id int4 NOT NULL DEFAULT nextval('reg_comment_pk_seq'::regclass),
	reg_comment_text varchar(500) NOT NULL,
	reg_user_id varchar(31) NOT NULL,
	reg_commented_time timestamp NOT NULL,
	reg_tenant_id int4 NOT NULL DEFAULT 0,
	CONSTRAINT pk_reg_comment PRIMARY KEY (reg_id, reg_tenant_id)
);


-- public.reg_content definition

-- Drop table

-- DROP TABLE public.reg_content;

CREATE TABLE public.reg_content (
	reg_content_id int4 NOT NULL DEFAULT nextval('reg_content_pk_seq'::regclass),
	reg_content_data bytea NULL,
	reg_tenant_id int4 NOT NULL DEFAULT 0,
	CONSTRAINT pk_reg_content PRIMARY KEY (reg_content_id, reg_tenant_id)
);


-- public.reg_content_history definition

-- Drop table

-- DROP TABLE public.reg_content_history;

CREATE TABLE public.reg_content_history (
	reg_content_id int4 NOT NULL,
	reg_content_data bytea NULL,
	reg_deleted int2 NULL,
	reg_tenant_id int4 NOT NULL DEFAULT 0,
	CONSTRAINT pk_reg_content_history PRIMARY KEY (reg_content_id, reg_tenant_id)
);


-- public.reg_log definition

-- Drop table

-- DROP TABLE public.reg_log;

CREATE TABLE public.reg_log (
	reg_log_id int4 NOT NULL DEFAULT nextval('reg_log_pk_seq'::regclass),
	reg_path varchar(2000) NULL,
	reg_user_id varchar(31) NOT NULL,
	reg_logged_time timestamp NOT NULL,
	reg_action int4 NOT NULL,
	reg_action_data varchar(500) NULL,
	reg_tenant_id int4 NOT NULL DEFAULT 0,
	CONSTRAINT reg_log_pkey PRIMARY KEY (reg_log_id, reg_tenant_id)
);
CREATE INDEX reg_log_ind_by_reg_logtime ON public.reg_log USING btree (reg_logged_time, reg_tenant_id);


-- public.reg_path definition

-- Drop table

-- DROP TABLE public.reg_path;

CREATE TABLE public.reg_path (
	reg_path_id int4 NOT NULL DEFAULT nextval('reg_path_pk_seq'::regclass),
	reg_path_value varchar(2000) NOT NULL,
	reg_path_parent_id int4 NULL,
	reg_tenant_id int4 NOT NULL DEFAULT 0,
	CONSTRAINT pk_reg_path PRIMARY KEY (reg_path_id, reg_tenant_id),
	CONSTRAINT unique_reg_path_tenant_id UNIQUE (reg_path_value, reg_tenant_id)
);
CREATE INDEX reg_path_ind_by_path_parent_id ON public.reg_path USING btree (reg_path_parent_id, reg_tenant_id);


-- public.reg_property definition

-- Drop table

-- DROP TABLE public.reg_property;

CREATE TABLE public.reg_property (
	reg_id int4 NOT NULL DEFAULT nextval('reg_property_pk_seq'::regclass),
	reg_name varchar(100) NOT NULL,
	reg_value varchar(1000) NULL,
	reg_tenant_id int4 NOT NULL DEFAULT 0,
	CONSTRAINT pk_reg_property PRIMARY KEY (reg_id, reg_tenant_id)
);


-- public.reg_rating definition

-- Drop table

-- DROP TABLE public.reg_rating;

CREATE TABLE public.reg_rating (
	reg_id int4 NOT NULL DEFAULT nextval('reg_rating_pk_seq'::regclass),
	reg_rating int4 NOT NULL,
	reg_user_id varchar(31) NOT NULL,
	reg_rated_time timestamp NOT NULL,
	reg_tenant_id int4 NOT NULL DEFAULT 0,
	CONSTRAINT pk_reg_rating PRIMARY KEY (reg_id, reg_tenant_id)
);


-- public.reg_tag definition

-- Drop table

-- DROP TABLE public.reg_tag;

CREATE TABLE public.reg_tag (
	reg_id int4 NOT NULL DEFAULT nextval('reg_tag_pk_seq'::regclass),
	reg_tag_name varchar(500) NOT NULL,
	reg_user_id varchar(31) NOT NULL,
	reg_tagged_time timestamp NOT NULL,
	reg_tenant_id int4 NOT NULL DEFAULT 0,
	CONSTRAINT pk_reg_tag PRIMARY KEY (reg_id, reg_tenant_id)
);


-- public.tb_situation definition

-- Drop table

-- DROP TABLE public.tb_situation;

CREATE TABLE public.tb_situation (
	id int8 NOT NULL,
	created_at timestamp NULL,
	updated_at timestamp NULL,
	description varchar(200) NOT NULL,
	CONSTRAINT tb_situation_pkey PRIMARY KEY (id)
);


-- public.tb_status definition

-- Drop table

-- DROP TABLE public.tb_status;

CREATE TABLE public.tb_status (
	id int8 NOT NULL,
	created_at timestamp NULL,
	updated_at timestamp NULL,
	active bool NOT NULL,
	code varchar(100) NOT NULL,
	description varchar(200) NOT NULL,
	CONSTRAINT tb_status_pkey PRIMARY KEY (id)
);


-- public.um_dialect definition

-- Drop table

-- DROP TABLE public.um_dialect;

CREATE TABLE public.um_dialect (
	um_id int4 NOT NULL DEFAULT nextval('um_dialect_pk_seq'::regclass),
	um_dialect_uri varchar(255) NOT NULL,
	um_tenant_id int4 NOT NULL DEFAULT 0,
	CONSTRAINT um_dialect_pkey PRIMARY KEY (um_id, um_tenant_id),
	CONSTRAINT um_dialect_um_dialect_uri_um_tenant_id_key UNIQUE (um_dialect_uri, um_tenant_id)
);


-- public.um_domain definition

-- Drop table

-- DROP TABLE public.um_domain;

CREATE TABLE public.um_domain (
	um_domain_id int4 NOT NULL DEFAULT nextval('um_domain_pk_seq'::regclass),
	um_domain_name varchar(255) NOT NULL,
	um_tenant_id int4 NOT NULL DEFAULT 0,
	CONSTRAINT um_domain_pkey PRIMARY KEY (um_domain_id, um_tenant_id),
	CONSTRAINT um_domain_um_domain_name_um_tenant_id_key UNIQUE (um_domain_name, um_tenant_id)
);


-- public.um_hybrid_remember_me definition

-- Drop table

-- DROP TABLE public.um_hybrid_remember_me;

CREATE TABLE public.um_hybrid_remember_me (
	um_id int4 NOT NULL DEFAULT nextval('um_hybrid_remember_me_pk_seq'::regclass),
	um_user_name varchar(255) NOT NULL,
	um_cookie_value varchar(1024) NULL,
	um_created_time timestamp NULL,
	um_tenant_id int4 NOT NULL DEFAULT 0,
	CONSTRAINT um_hybrid_remember_me_pkey PRIMARY KEY (um_id, um_tenant_id)
);


-- public.um_hybrid_role definition

-- Drop table

-- DROP TABLE public.um_hybrid_role;

CREATE TABLE public.um_hybrid_role (
	um_id int4 NOT NULL DEFAULT nextval('um_hybrid_role_pk_seq'::regclass),
	um_role_name varchar(255) NOT NULL,
	um_tenant_id int4 NOT NULL DEFAULT 0,
	CONSTRAINT um_hybrid_role_pkey PRIMARY KEY (um_id, um_tenant_id),
	CONSTRAINT um_hybrid_role_um_role_name_um_tenant_id_key UNIQUE (um_role_name, um_tenant_id)
);
CREATE INDEX um_role_name_ind ON public.um_hybrid_role USING btree (um_role_name);


-- public.um_module definition

-- Drop table

-- DROP TABLE public.um_module;

CREATE TABLE public.um_module (
	um_id int4 NOT NULL DEFAULT nextval('um_module_pk_seq'::regclass),
	um_module_name varchar(100) NULL,
	CONSTRAINT um_module_pkey PRIMARY KEY (um_id),
	CONSTRAINT um_module_um_module_name_key UNIQUE (um_module_name)
);


-- public.um_permission definition

-- Drop table

-- DROP TABLE public.um_permission;

CREATE TABLE public.um_permission (
	um_id int4 NOT NULL DEFAULT nextval('um_permission_pk_seq'::regclass),
	um_resource_id varchar(255) NOT NULL,
	um_action varchar(255) NOT NULL,
	um_tenant_id int4 NOT NULL DEFAULT 0,
	um_module_id int4 NULL DEFAULT 0,
	CONSTRAINT um_permission_pkey PRIMARY KEY (um_id, um_tenant_id),
	CONSTRAINT um_permission_um_resource_id_um_action_um_tenant_id_key UNIQUE (um_resource_id, um_action, um_tenant_id)
);
CREATE INDEX index_um_permission_um_resource_id_um_action ON public.um_permission USING btree (um_resource_id, um_action, um_tenant_id);


-- public.um_system_role definition

-- Drop table

-- DROP TABLE public.um_system_role;

CREATE TABLE public.um_system_role (
	um_id int4 NOT NULL DEFAULT nextval('um_system_role_pk_seq'::regclass),
	um_role_name varchar(255) NOT NULL,
	um_tenant_id int4 NOT NULL DEFAULT 0,
	CONSTRAINT um_system_role_pkey PRIMARY KEY (um_id, um_tenant_id),
	CONSTRAINT um_system_role_um_role_name_um_tenant_id_key UNIQUE (um_role_name, um_tenant_id)
);


-- public.um_system_user definition

-- Drop table

-- DROP TABLE public.um_system_user;

CREATE TABLE public.um_system_user (
	um_id int4 NOT NULL DEFAULT nextval('um_system_user_pk_seq'::regclass),
	um_user_name varchar(255) NOT NULL,
	um_user_password varchar(255) NOT NULL,
	um_salt_value varchar(31) NULL,
	um_require_change bool NULL DEFAULT false,
	um_changed_time timestamp NOT NULL,
	um_tenant_id int4 NOT NULL DEFAULT 0,
	CONSTRAINT um_system_user_pkey PRIMARY KEY (um_id, um_tenant_id),
	CONSTRAINT um_system_user_um_user_name_um_tenant_id_key UNIQUE (um_user_name, um_tenant_id)
);


-- public.um_tenant definition

-- Drop table

-- DROP TABLE public.um_tenant;

CREATE TABLE public.um_tenant (
	um_id int4 NOT NULL DEFAULT nextval('um_tenant_pk_seq'::regclass),
	um_tenant_uuid varchar(36) NOT NULL,
	um_domain_name varchar(255) NOT NULL,
	um_email varchar(255) NULL,
	um_active bool NULL DEFAULT false,
	um_created_date timestamp NOT NULL,
	um_user_config bytea NULL,
	CONSTRAINT um_tenant_pkey PRIMARY KEY (um_id),
	CONSTRAINT um_tenant_um_domain_name_key UNIQUE (um_domain_name),
	CONSTRAINT um_tenant_um_tenant_uuid_key UNIQUE (um_tenant_uuid)
);
CREATE INDEX index_um_tenant_um_domain_name ON public.um_tenant USING btree (um_domain_name);


-- public.reg_resource definition

-- Drop table

-- DROP TABLE public.reg_resource;

CREATE TABLE public.reg_resource (
	reg_path_id int4 NOT NULL,
	reg_name varchar(256) NULL,
	reg_version int4 NOT NULL DEFAULT nextval('reg_resource_pk_seq'::regclass),
	reg_media_type varchar(500) NULL,
	reg_creator varchar(31) NOT NULL,
	reg_created_time timestamp NOT NULL,
	reg_last_updator varchar(31) NULL,
	reg_last_updated_time timestamp NOT NULL,
	reg_description varchar(1000) NULL,
	reg_content_id int4 NULL,
	reg_tenant_id int4 NOT NULL DEFAULT 0,
	reg_uuid varchar(100) NOT NULL,
	CONSTRAINT pk_reg_resource PRIMARY KEY (reg_version, reg_tenant_id),
	CONSTRAINT reg_resource_fk_by_content_id FOREIGN KEY (reg_content_id,reg_tenant_id) REFERENCES public.reg_content(reg_content_id,reg_tenant_id),
	CONSTRAINT reg_resource_fk_by_path_id FOREIGN KEY (reg_path_id,reg_tenant_id) REFERENCES public.reg_path(reg_path_id,reg_tenant_id)
);
CREATE INDEX reg_resource_history_ind_by_path_id_name ON public.reg_resource USING btree (reg_path_id, reg_name, reg_tenant_id);
CREATE INDEX reg_resource_ind_by_name ON public.reg_resource USING btree (reg_name, reg_tenant_id);
CREATE INDEX reg_resource_ind_by_path_id_name ON public.reg_resource USING btree (reg_path_id, reg_name, reg_tenant_id);
CREATE INDEX reg_resource_ind_by_tenant ON public.reg_resource USING btree (reg_tenant_id, reg_uuid);
CREATE INDEX reg_resource_ind_by_type ON public.reg_resource USING btree (reg_tenant_id, reg_media_type);
CREATE INDEX reg_resource_ind_by_uuid ON public.reg_resource USING btree (reg_uuid);


-- public.reg_resource_comment definition

-- Drop table

-- DROP TABLE public.reg_resource_comment;

CREATE TABLE public.reg_resource_comment (
	reg_comment_id int4 NOT NULL,
	reg_version int4 NULL,
	reg_path_id int4 NULL,
	reg_resource_name varchar(256) NULL,
	reg_tenant_id int4 NULL DEFAULT 0,
	CONSTRAINT reg_resource_comment_fk_by_comment_id FOREIGN KEY (reg_comment_id,reg_tenant_id) REFERENCES public.reg_comment(reg_id,reg_tenant_id),
	CONSTRAINT reg_resource_comment_fk_by_path_id FOREIGN KEY (reg_path_id,reg_tenant_id) REFERENCES public.reg_path(reg_path_id,reg_tenant_id)
);
CREATE INDEX reg_resource_comment_ind_by_path_id_and_resource_name ON public.reg_resource_comment USING btree (reg_path_id, reg_resource_name, reg_tenant_id);
CREATE INDEX reg_resource_comment_ind_by_version ON public.reg_resource_comment USING btree (reg_version, reg_tenant_id);


-- public.reg_resource_history definition

-- Drop table

-- DROP TABLE public.reg_resource_history;

CREATE TABLE public.reg_resource_history (
	reg_path_id int4 NOT NULL,
	reg_name varchar(256) NULL,
	reg_version int4 NOT NULL,
	reg_media_type varchar(500) NULL,
	reg_creator varchar(31) NOT NULL,
	reg_created_time timestamp NOT NULL,
	reg_last_updator varchar(31) NULL,
	reg_last_updated_time timestamp NOT NULL,
	reg_description varchar(1000) NULL,
	reg_content_id int4 NULL,
	reg_deleted int2 NULL,
	reg_tenant_id int4 NOT NULL DEFAULT 0,
	reg_uuid varchar(100) NOT NULL,
	CONSTRAINT pk_reg_resource_history PRIMARY KEY (reg_version, reg_tenant_id),
	CONSTRAINT reg_resource_hist_fk_by_content_id FOREIGN KEY (reg_content_id,reg_tenant_id) REFERENCES public.reg_content_history(reg_content_id,reg_tenant_id),
	CONSTRAINT reg_resource_hist_fk_by_pathid FOREIGN KEY (reg_path_id,reg_tenant_id) REFERENCES public.reg_path(reg_path_id,reg_tenant_id)
);
CREATE INDEX reg_resource_history_ind_by_name ON public.reg_resource_history USING btree (reg_name, reg_tenant_id);


-- public.reg_resource_property definition

-- Drop table

-- DROP TABLE public.reg_resource_property;

CREATE TABLE public.reg_resource_property (
	reg_property_id int4 NOT NULL,
	reg_version int4 NULL,
	reg_path_id int4 NULL,
	reg_resource_name varchar(256) NULL,
	reg_tenant_id int4 NULL DEFAULT 0,
	CONSTRAINT reg_resource_property_fk_by_path_id FOREIGN KEY (reg_path_id,reg_tenant_id) REFERENCES public.reg_path(reg_path_id,reg_tenant_id),
	CONSTRAINT reg_resource_property_fk_by_tag_id FOREIGN KEY (reg_property_id,reg_tenant_id) REFERENCES public.reg_property(reg_id,reg_tenant_id)
);
CREATE INDEX reg_resource_property_ind_by_path_id_and_resource_name ON public.reg_resource_property USING btree (reg_path_id, reg_resource_name, reg_tenant_id);
CREATE INDEX reg_resource_property_ind_by_version ON public.reg_resource_property USING btree (reg_version, reg_tenant_id);


-- public.reg_resource_rating definition

-- Drop table

-- DROP TABLE public.reg_resource_rating;

CREATE TABLE public.reg_resource_rating (
	reg_rating_id int4 NOT NULL,
	reg_version int4 NULL,
	reg_path_id int4 NULL,
	reg_resource_name varchar(256) NULL,
	reg_tenant_id int4 NULL DEFAULT 0,
	CONSTRAINT reg_resource_rating_fk_by_path_id FOREIGN KEY (reg_path_id,reg_tenant_id) REFERENCES public.reg_path(reg_path_id,reg_tenant_id),
	CONSTRAINT reg_resource_rating_fk_by_rating_id FOREIGN KEY (reg_rating_id,reg_tenant_id) REFERENCES public.reg_rating(reg_id,reg_tenant_id)
);
CREATE INDEX reg_resource_rating_ind_by_path_id_and_resource_name ON public.reg_resource_rating USING btree (reg_path_id, reg_resource_name, reg_tenant_id);
CREATE INDEX reg_resource_rating_ind_by_version ON public.reg_resource_rating USING btree (reg_version, reg_tenant_id);


-- public.reg_resource_tag definition

-- Drop table

-- DROP TABLE public.reg_resource_tag;

CREATE TABLE public.reg_resource_tag (
	reg_tag_id int4 NOT NULL,
	reg_version int4 NULL,
	reg_path_id int4 NULL,
	reg_resource_name varchar(256) NULL,
	reg_tenant_id int4 NULL DEFAULT 0,
	CONSTRAINT reg_resource_tag_fk_by_path_id FOREIGN KEY (reg_path_id,reg_tenant_id) REFERENCES public.reg_path(reg_path_id,reg_tenant_id),
	CONSTRAINT reg_resource_tag_fk_by_tag_id FOREIGN KEY (reg_tag_id,reg_tenant_id) REFERENCES public.reg_tag(reg_id,reg_tenant_id)
);
CREATE INDEX reg_resource_tag_ind_by_path_id_and_resource_name ON public.reg_resource_tag USING btree (reg_path_id, reg_resource_name, reg_tenant_id);
CREATE INDEX reg_resource_tag_ind_by_version ON public.reg_resource_tag USING btree (reg_version, reg_tenant_id);


-- public.reg_snapshot definition

-- Drop table

-- DROP TABLE public.reg_snapshot;

CREATE TABLE public.reg_snapshot (
	reg_snapshot_id int4 NOT NULL DEFAULT nextval('reg_snapshot_pk_seq'::regclass),
	reg_path_id int4 NOT NULL,
	reg_resource_name varchar(255) NULL,
	reg_resource_vids bytea NOT NULL,
	reg_tenant_id int4 NOT NULL DEFAULT 0,
	CONSTRAINT pk_reg_snapshot PRIMARY KEY (reg_snapshot_id, reg_tenant_id),
	CONSTRAINT reg_snapshot_fk_by_path_id FOREIGN KEY (reg_path_id,reg_tenant_id) REFERENCES public.reg_path(reg_path_id,reg_tenant_id)
);
CREATE INDEX reg_snapshot_ind_by_path_id_and_resource_name ON public.reg_snapshot USING btree (reg_path_id, reg_resource_name, reg_tenant_id);


-- public.tb_event definition

-- Drop table

-- DROP TABLE public.tb_event;

CREATE TABLE public.tb_event (
	id int8 NOT NULL,
	created_at timestamp NULL,
	updated_at timestamp NULL,
	is_active bool NULL,
	code varchar(200) NOT NULL,
	description varchar(500) NOT NULL,
	status_id int8 NOT NULL,
	CONSTRAINT tb_event_pkey PRIMARY KEY (id),
	CONSTRAINT fkfx8yhrd3pog1fmh54c7ugoqs3 FOREIGN KEY (status_id) REFERENCES public.tb_status(id)
);


-- public.tb_event_status definition

-- Drop table

-- DROP TABLE public.tb_event_status;

CREATE TABLE public.tb_event_status (
	event_id int8 NOT NULL,
	status_id int8 NOT NULL,
	CONSTRAINT uk_f9n3bafimp3yt5wto19h9p4ge UNIQUE (status_id),
	CONSTRAINT fkamtdjhxeun3crwrctno23esj0 FOREIGN KEY (status_id) REFERENCES public.tb_event(id),
	CONSTRAINT fkc07fxur51enfmwwqyyf18pgvy FOREIGN KEY (event_id) REFERENCES public.tb_status(id)
);


-- public.um_account_mapping definition

-- Drop table

-- DROP TABLE public.um_account_mapping;

CREATE TABLE public.um_account_mapping (
	um_id int4 NOT NULL DEFAULT nextval('um_account_mapping_seq'::regclass),
	um_user_name varchar(255) NOT NULL,
	um_tenant_id int4 NOT NULL,
	um_user_store_domain varchar(100) NULL,
	um_acc_link_id int4 NOT NULL,
	CONSTRAINT um_account_mapping_pkey PRIMARY KEY (um_id),
	CONSTRAINT um_account_mapping_um_user_name_um_tenant_id_um_user_store__key UNIQUE (um_user_name, um_tenant_id, um_user_store_domain, um_acc_link_id),
	CONSTRAINT um_account_mapping_um_tenant_id_fkey FOREIGN KEY (um_tenant_id) REFERENCES public.um_tenant(um_id) ON DELETE CASCADE
);


-- public.um_claim definition

-- Drop table

-- DROP TABLE public.um_claim;

CREATE TABLE public.um_claim (
	um_id int4 NOT NULL DEFAULT nextval('um_claim_pk_seq'::regclass),
	um_dialect_id int4 NOT NULL,
	um_claim_uri varchar(255) NOT NULL,
	um_display_tag varchar(255) NULL,
	um_description varchar(255) NULL,
	um_mapped_attribute_domain varchar(255) NULL,
	um_mapped_attribute varchar(255) NULL,
	um_reg_ex varchar(255) NULL,
	um_supported int2 NULL,
	um_required int2 NULL,
	um_display_order int4 NULL,
	um_checked_attribute int2 NULL,
	um_read_only int2 NULL,
	um_tenant_id int4 NOT NULL DEFAULT 0,
	CONSTRAINT um_claim_pkey PRIMARY KEY (um_id, um_tenant_id),
	CONSTRAINT um_claim_um_dialect_id_um_claim_uri_um_tenant_id_key UNIQUE (um_dialect_id, um_claim_uri, um_tenant_id),
	CONSTRAINT um_claim_um_dialect_id_um_tenant_id_fkey FOREIGN KEY (um_dialect_id,um_tenant_id) REFERENCES public.um_dialect(um_id,um_tenant_id)
);


-- public.um_hybrid_group_role definition

-- Drop table

-- DROP TABLE public.um_hybrid_group_role;

CREATE TABLE public.um_hybrid_group_role (
	um_id int4 NOT NULL DEFAULT nextval('um_hybrid_group_role_pk_seq'::regclass),
	um_group_name varchar(255) NULL,
	um_role_id int4 NOT NULL,
	um_tenant_id int4 NOT NULL DEFAULT 0,
	um_domain_id int4 NULL,
	CONSTRAINT um_hybrid_group_role_pkey PRIMARY KEY (um_id, um_tenant_id),
	CONSTRAINT um_hybrid_group_role_um_group_name_um_role_id_um_tenant_id__key UNIQUE (um_group_name, um_role_id, um_tenant_id, um_domain_id),
	CONSTRAINT um_hybrid_group_role_um_domain_id_um_tenant_id_fkey FOREIGN KEY (um_domain_id,um_tenant_id) REFERENCES public.um_domain(um_domain_id,um_tenant_id) ON DELETE CASCADE,
	CONSTRAINT um_hybrid_group_role_um_role_id_um_tenant_id_fkey FOREIGN KEY (um_role_id,um_tenant_id) REFERENCES public.um_hybrid_role(um_id,um_tenant_id) ON DELETE CASCADE
);


-- public.um_hybrid_user_role definition

-- Drop table

-- DROP TABLE public.um_hybrid_user_role;

CREATE TABLE public.um_hybrid_user_role (
	um_id int4 NOT NULL DEFAULT nextval('um_hybrid_user_role_pk_seq'::regclass),
	um_user_name varchar(255) NULL,
	um_role_id int4 NOT NULL,
	um_tenant_id int4 NOT NULL DEFAULT 0,
	um_domain_id int4 NULL,
	CONSTRAINT um_hybrid_user_role_pkey PRIMARY KEY (um_id, um_tenant_id),
	CONSTRAINT um_hybrid_user_role_um_user_name_um_role_id_um_tenant_id_um_key UNIQUE (um_user_name, um_role_id, um_tenant_id, um_domain_id),
	CONSTRAINT um_hybrid_user_role_um_domain_id_um_tenant_id_fkey FOREIGN KEY (um_domain_id,um_tenant_id) REFERENCES public.um_domain(um_domain_id,um_tenant_id) ON DELETE CASCADE,
	CONSTRAINT um_hybrid_user_role_um_role_id_um_tenant_id_fkey FOREIGN KEY (um_role_id,um_tenant_id) REFERENCES public.um_hybrid_role(um_id,um_tenant_id) ON DELETE CASCADE
);


-- public.um_module_actions definition

-- Drop table

-- DROP TABLE public.um_module_actions;

CREATE TABLE public.um_module_actions (
	um_action varchar(255) NOT NULL,
	um_module_id int4 NOT NULL,
	CONSTRAINT um_module_actions_pkey PRIMARY KEY (um_action, um_module_id),
	CONSTRAINT um_module_actions_um_module_id_fkey FOREIGN KEY (um_module_id) REFERENCES public.um_module(um_id) ON DELETE CASCADE
);


-- public.um_profile_config definition

-- Drop table

-- DROP TABLE public.um_profile_config;

CREATE TABLE public.um_profile_config (
	um_id int4 NOT NULL DEFAULT nextval('um_profile_config_pk_seq'::regclass),
	um_dialect_id int4 NOT NULL,
	um_profile_name varchar(255) NULL,
	um_tenant_id int4 NOT NULL DEFAULT 0,
	CONSTRAINT um_profile_config_pkey PRIMARY KEY (um_id, um_tenant_id),
	CONSTRAINT um_profile_config_um_dialect_id_um_tenant_id_fkey FOREIGN KEY (um_dialect_id,um_tenant_id) REFERENCES public.um_dialect(um_id,um_tenant_id)
);


-- public.um_role definition

-- Drop table

-- DROP TABLE public.um_role;

CREATE TABLE public.um_role (
	um_id int4 NOT NULL DEFAULT nextval('um_role_pk_seq'::regclass),
	um_role_name varchar(255) NOT NULL,
	um_tenant_id int4 NOT NULL DEFAULT 0,
	um_shared_role bool NULL DEFAULT false,
	is_read bool NULL,
	situation_id int8 NULL,
	CONSTRAINT um_role_pkey PRIMARY KEY (um_id, um_tenant_id),
	CONSTRAINT um_role_um_role_name_um_tenant_id_key UNIQUE (um_role_name, um_tenant_id),
	CONSTRAINT fkkamluvkxby4s4tw1df89w2nb9 FOREIGN KEY (situation_id) REFERENCES public.tb_situation(id)
);

-- Table Triggers

create trigger u_update_role_situation after
insert
    or
update
    on
    public.um_role for each row execute function fn_update_role_situation();


-- public.um_role_permission definition

-- Drop table

-- DROP TABLE public.um_role_permission;

CREATE TABLE public.um_role_permission (
	um_id int4 NOT NULL DEFAULT nextval('um_role_permission_pk_seq'::regclass),
	um_permission_id int4 NOT NULL,
	um_role_name varchar(255) NOT NULL,
	um_is_allowed int2 NOT NULL,
	um_tenant_id int4 NOT NULL DEFAULT 0,
	um_domain_id int4 NULL,
	CONSTRAINT um_role_permission_pkey PRIMARY KEY (um_id, um_tenant_id),
	CONSTRAINT um_role_permission_um_domain_id_um_tenant_id_fkey FOREIGN KEY (um_domain_id,um_tenant_id) REFERENCES public.um_domain(um_domain_id,um_tenant_id) ON DELETE CASCADE,
	CONSTRAINT um_role_permission_um_permission_id_um_tenant_id_fkey FOREIGN KEY (um_permission_id,um_tenant_id) REFERENCES public.um_permission(um_id,um_tenant_id) ON DELETE CASCADE
);


-- public.um_system_user_role definition

-- Drop table

-- DROP TABLE public.um_system_user_role;

CREATE TABLE public.um_system_user_role (
	um_id int4 NOT NULL DEFAULT nextval('um_system_user_role_pk_seq'::regclass),
	um_user_name varchar(255) NULL,
	um_role_id int4 NOT NULL,
	um_tenant_id int4 NOT NULL DEFAULT 0,
	CONSTRAINT um_system_user_role_pkey PRIMARY KEY (um_id, um_tenant_id),
	CONSTRAINT um_system_user_role_um_user_name_um_role_id_um_tenant_id_key UNIQUE (um_user_name, um_role_id, um_tenant_id),
	CONSTRAINT um_system_user_role_um_role_id_um_tenant_id_fkey FOREIGN KEY (um_role_id,um_tenant_id) REFERENCES public.um_system_role(um_id,um_tenant_id)
);


-- public.um_user definition

-- Drop table

-- DROP TABLE public.um_user;

CREATE TABLE public.um_user (
	um_id int4 NOT NULL DEFAULT nextval('um_user_pk_seq'::regclass),
	um_user_id varchar(255) NOT NULL,
	um_user_name varchar(255) NOT NULL,
	um_user_password varchar(255) NOT NULL,
	um_salt_value varchar(31) NULL,
	um_require_change bool NULL DEFAULT false,
	um_changed_time timestamp NOT NULL,
	um_tenant_id int4 NOT NULL DEFAULT 0,
	is_read bool NULL,
	situation_id int8 NULL,
	CONSTRAINT um_user_pkey PRIMARY KEY (um_id, um_tenant_id),
	CONSTRAINT um_user_um_user_id_um_tenant_id_key UNIQUE (um_user_id, um_tenant_id),
	CONSTRAINT fklu3yvi1i5bhnoe0ai630vkn5b FOREIGN KEY (situation_id) REFERENCES public.tb_situation(id)
);

-- Table Triggers

create trigger u_update_user_situation after
insert
    or
update
    on
    public.um_user for each row execute function fn_update_user_situation();


-- public.um_user_attribute definition

-- Drop table

-- DROP TABLE public.um_user_attribute;

CREATE TABLE public.um_user_attribute (
	um_id int4 NOT NULL DEFAULT nextval('um_user_attribute_pk_seq'::regclass),
	um_attr_name varchar(255) NOT NULL,
	um_attr_value varchar(1024) NULL,
	um_profile_id varchar(255) NULL,
	um_user_id int4 NULL,
	um_tenant_id int4 NOT NULL DEFAULT 0,
	CONSTRAINT um_user_attribute_pkey PRIMARY KEY (um_id, um_tenant_id),
	CONSTRAINT um_user_attribute_um_user_id_um_tenant_id_fkey FOREIGN KEY (um_user_id,um_tenant_id) REFERENCES public.um_user(um_id,um_tenant_id)
);
CREATE INDEX um_attr_name_value_index ON public.um_user_attribute USING btree (um_attr_name, um_attr_value);
CREATE INDEX um_user_id_index ON public.um_user_attribute USING btree (um_user_id);


-- public.um_user_permission definition

-- Drop table

-- DROP TABLE public.um_user_permission;

CREATE TABLE public.um_user_permission (
	um_id int4 NOT NULL DEFAULT nextval('um_user_permission_pk_seq'::regclass),
	um_permission_id int4 NOT NULL,
	um_user_name varchar(255) NOT NULL,
	um_is_allowed int2 NOT NULL,
	um_tenant_id int4 NOT NULL DEFAULT 0,
	CONSTRAINT um_user_permission_pkey PRIMARY KEY (um_id, um_tenant_id),
	CONSTRAINT um_user_permission_um_permission_id_um_tenant_id_fkey FOREIGN KEY (um_permission_id,um_tenant_id) REFERENCES public.um_permission(um_id,um_tenant_id) ON DELETE CASCADE
);


-- public.um_user_role definition

-- Drop table

-- DROP TABLE public.um_user_role;

CREATE TABLE public.um_user_role (
	um_id int4 NOT NULL DEFAULT nextval('um_user_role_pk_seq'::regclass),
	um_role_id int4 NOT NULL,
	um_user_id int4 NOT NULL,
	um_tenant_id int4 NOT NULL DEFAULT 0,
	is_read bool NULL,
	situation_id int8 NULL,
	CONSTRAINT um_user_role_pkey PRIMARY KEY (um_id, um_tenant_id),
	CONSTRAINT um_user_role_um_user_id_um_role_id_um_tenant_id_key UNIQUE (um_user_id, um_role_id, um_tenant_id),
	CONSTRAINT fkq6gej16ouy9dgs3ua5jpxba53 FOREIGN KEY (situation_id) REFERENCES public.tb_situation(id),
	CONSTRAINT um_user_role_um_role_id_um_tenant_id_fkey FOREIGN KEY (um_role_id,um_tenant_id) REFERENCES public.um_role(um_id,um_tenant_id),
	CONSTRAINT um_user_role_um_user_id_um_tenant_id_fkey FOREIGN KEY (um_user_id,um_tenant_id) REFERENCES public.um_user(um_id,um_tenant_id)
);

-- Table Triggers

create trigger u_update_user_role_situation after
insert
    or
update
    on
    public.um_user_role for each row execute function fn_update_user_role_situation();


-- public.um_uuid_domain_mapper definition

-- Drop table

-- DROP TABLE public.um_uuid_domain_mapper;

CREATE TABLE public.um_uuid_domain_mapper (
	um_id int4 NOT NULL DEFAULT nextval('um_hybrid_remember_me_pk_seq'::regclass),
	um_user_id varchar(255) NOT NULL,
	um_domain_id int4 NOT NULL,
	um_tenant_id int4 NULL DEFAULT 0,
	CONSTRAINT um_uuid_domain_mapper_pkey PRIMARY KEY (um_id),
	CONSTRAINT um_uuid_domain_mapper_um_user_id_key UNIQUE (um_user_id),
	CONSTRAINT um_uuid_domain_mapper_um_domain_id_um_tenant_id_fkey FOREIGN KEY (um_domain_id,um_tenant_id) REFERENCES public.um_domain(um_domain_id,um_tenant_id) ON DELETE CASCADE
);
CREATE INDEX uuid_dm_uid_tid ON public.um_uuid_domain_mapper USING btree (um_user_id, um_tenant_id);


-- public.um_claim_behavior definition

-- Drop table

-- DROP TABLE public.um_claim_behavior;

CREATE TABLE public.um_claim_behavior (
	um_id int4 NOT NULL DEFAULT nextval('um_claim_behavior_pk_seq'::regclass),
	um_profile_id int4 NULL,
	um_claim_id int4 NULL,
	um_behaviour int2 NULL,
	um_tenant_id int4 NOT NULL DEFAULT 0,
	CONSTRAINT um_claim_behavior_pkey PRIMARY KEY (um_id, um_tenant_id),
	CONSTRAINT um_claim_behavior_um_claim_id_um_tenant_id_fkey FOREIGN KEY (um_claim_id,um_tenant_id) REFERENCES public.um_claim(um_id,um_tenant_id),
	CONSTRAINT um_claim_behavior_um_profile_id_um_tenant_id_fkey FOREIGN KEY (um_profile_id,um_tenant_id) REFERENCES public.um_profile_config(um_id,um_tenant_id)
);


-- public.um_shared_user_role definition

-- Drop table

-- DROP TABLE public.um_shared_user_role;

CREATE TABLE public.um_shared_user_role (
	um_role_id int4 NOT NULL,
	um_user_id int4 NOT NULL,
	um_user_tenant_id int4 NOT NULL,
	um_role_tenant_id int4 NOT NULL,
	CONSTRAINT um_shared_user_role_um_user_id_um_role_id_um_user_tenant_id_key UNIQUE (um_user_id, um_role_id, um_user_tenant_id, um_role_tenant_id),
	CONSTRAINT um_shared_user_role_um_role_id_um_role_tenant_id_fkey FOREIGN KEY (um_role_id,um_role_tenant_id) REFERENCES public.um_role(um_id,um_tenant_id) ON DELETE CASCADE,
	CONSTRAINT um_shared_user_role_um_user_id_um_user_tenant_id_fkey FOREIGN KEY (um_user_id,um_user_tenant_id) REFERENCES public.um_user(um_id,um_tenant_id) ON DELETE CASCADE
);



CREATE OR REPLACE FUNCTION public.fn_update_role_situation()
 RETURNS trigger
 LANGUAGE plpgsql
AS $function$begin
		 
		if OLD.situation_id = 1 or OLD.situation_id is null then
		 	update public.um_role set situation_id = 3 where um_id = NEW.um_id;
		END IF;
       return null;
     end;$function$
;

CREATE OR REPLACE FUNCTION public.fn_update_user_role_situation()
 RETURNS trigger
 LANGUAGE plpgsql
AS $function$begin
		 
		if OLD.situation_id = 1 or OLD.situation_id is null then
		 	update public.um_user_role set situation_id = 3 where um_id = NEW.um_id;
		END IF;
       return null;
     end;$function$
;

CREATE OR REPLACE FUNCTION public.fn_update_user_situation()
 RETURNS trigger
 LANGUAGE plpgsql
AS $function$begin
		 
		if OLD.situation_id = 1 or OLD.situation_id is null then
		 	update public.um_user set situation_id = 3 where um_user_id = NEW.um_user_id;
		END IF;
       return null;
     end;$function$
;


-- DROP ROLE "server";

CREATE ROLE "server" WITH 
	SUPERUSER
	NOCREATEDB
	CREATEROLE
	INHERIT
	LOGIN
	NOREPLICATION
	NOBYPASSRLS
	CONNECTION LIMIT UNLIMITED;
