--
-- PostgreSQL database dump
--

-- Started on 2010-05-05 14:54:09

SET client_encoding = 'UTF8';
SET standard_conforming_strings = off;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET escape_string_warning = off;

--
-- Name: iso21090schema; Type: SCHEMA; Schema: -; Owner: -
--

CREATE SCHEMA iso21090schema;


SET search_path = iso21090schema, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: ad_ad_datatype_value9; Type: TABLE 
--

CREATE TABLE ad_ad_datatype_value9 (
    ad_datatype_id integer NOT NULL,
    ad_datatype_value9_id integer NOT NULL
);


--
-- Name: ad_datatype; Type: TABLE 
--

CREATE TABLE ad_datatype (
    id integer NOT NULL,
    value1_al_value character varying(50),
    value2_dal_value character varying(50),
    value2_dal_code character varying(50),
    value3_al_value character varying(50),
    value3_al_code character varying(50),
    value3_al_codesystem character varying(50),
    value4_al1_value character varying(50),
    value4_al2_value character varying(50),
    value4_al1_code character varying(50),
    value4_al2_code character varying(50),
    value4_al1_codesystem character varying(50),
    value4_al2_codesystem character varying(50),
    value5_al_value character varying(50),
    value5_al_code character varying(50),
    value5_al_codesystem character varying(50),
    value5_dal_code character varying(50),
    value5_dal_value character varying(50),
    value5_dal_codesystem character varying(50),
    value5_cty_value character varying(50),
    value5_cty_code character varying(50),
    value5_cty_codesystem character varying(50),
    value6_adl_value character varying(50),
    value6_al_value character varying(50),
    value6_bnn_value character varying(50),
    value6_bnr_value character varying(50),
    value6_bns_value character varying(50),
    value6_car_value character varying(50),
    value6_cen_value character varying(50),
    value6_cnt_value character varying(50),
    value6_cpa_value character varying(50),
    value6_cty_value character varying(50),
    value6_dal_value character varying(50),
    value6_del_value character varying(50),
    value6_dinsta_value character varying(50),
    value6_dinstq_value character varying(50),
    value6_dir_value character varying(50),
    value6_dmod_value character varying(50),
    value6_dmodid_value character varying(50),
    value6_int_value character varying(50),
    value6_pob_value character varying(50),
    value6_pre_value character varying(50),
    value6_sal_value character varying(50),
    value6_sta_value character varying(50),
    value6_stb_value character varying(50),
    value6_str_value character varying(50),
    value6_sttyp_value character varying(50),
    value6_unid_value character varying(50),
    value6_unit_value character varying(50),
    value6_zip_value character varying(50),
    value6_adl_code character varying(50),
    value6_bns_code character varying(50),
    value6_bns_codesystem character varying(50),
    value6_dal_code character varying(50),
    value6_dal_codesystem character varying(50),
    value6_int_code character varying(50),
    value6_int_codesystem character varying(50),
    value6_stb_code character varying(50),
    value6_stb_codesystem character varying(50),
    value6_zip_codesystem character varying(50),
    value6_zip_code character varying(50),
    ad_datatype_value8_id integer
);


--
-- Name: ad_datatype_value7; Type: TABLE 
--

CREATE TABLE ad_datatype_value7 (
    ad_datatype_id integer,
    al_value character varying(50),
    al_code character varying(50),
    al_codesystem character varying(50),
    dal_code character varying(50),
    dal_value character varying(50),
    dal_codesystem character varying(50),
    cty_value character varying(50),
    cty_code character varying(50),
    cty_codesystem character varying(50)
);


--
-- Name: ad_datatype_value8; Type: TABLE 
--

CREATE TABLE ad_datatype_value8 (
    id integer NOT NULL,
    al_value character varying(50),
    al_code character varying(50),
    al_codesystem character varying(50),
    dal_code character varying(50),
    dal_value character varying(50),
    dal_codesystem character varying(50),
    cty_value character varying(50),
    cty_code character varying(50),
    cty_codesystem character varying(50)
);


--
-- Name: ad_datatype_value9; Type: TABLE 
--

CREATE TABLE ad_datatype_value9 (
    id integer NOT NULL,
    al_value character varying(50),
    al_code character varying(50),
    al_codesystem character varying(50),
    dal_code character varying(50),
    dal_value character varying(50),
    dal_codesystem character varying(50),
    cty_value character varying(50),
    cty_code character varying(50),
    cty_codesystem character varying(50)
);


--
-- Name: bl_datatype; Type: TABLE 
--

CREATE TABLE bl_datatype (
    id integer NOT NULL,
    value1_value character varying(1),
    value2_null_flavor character varying(50),
    value2_value character varying(1)
);


--
-- Name: bl_nonnull_datatype; Type: TABLE 
--

CREATE TABLE bl_nonnull_datatype (
    id integer NOT NULL,
    value1_value character varying(1)
);


--
-- Name: cd_cd_datatype_value8; Type: TABLE 
--

CREATE TABLE cd_cd_datatype_value8 (
    cd_data_type_id integer NOT NULL,
    cd_datatype_value8_id integer NOT NULL
);


--
-- Name: cd_datatype; Type: TABLE 
--

CREATE TABLE cd_datatype (
    id integer NOT NULL,
    value1_code character varying(50),
    value2_null_flavor character varying(50),
    value2_code character varying(50),
    value3_code character varying(50),
    value4_null_flavor character varying(50),
    value4_code character varying(50),
    value4_code_system character varying(50),
    value4_code_system_version character varying(50),
    value4_code_system_name character varying(50),
    value4_display_null_flavor character varying(50),
    value4_display_value character varying(50),
    value4_orig_txt_null_flavor character varying(50),
    value4_orig_txt_value text,
    value4_orig_txt_description character varying(50),
    value5_null_flavor character varying(50),
    value5_code character varying(50),
    value5_code_system character varying(50),
    value5_code_system_name character varying(50),
    value5_code_system_version character varying(50),
    value5_display_null_flavor character varying(50),
    value5_display_value character varying(50),
    value5_orig_txt_null_flavor character varying(50),
    value5_orig_txt_value text,
    value5_orig_txt_description character varying(50),
    cd_datatype_value7_id integer
);


--
-- Name: cd_datatype_value6; Type: TABLE 
--

CREATE TABLE cd_datatype_value6 (
    cd_datatype_id integer NOT NULL,
    null_flavor character varying(50),
    code character varying(50),
    code_system character varying(50),
    code_system_name character varying(50),
    code_system_version character varying(50),
    display_null_flavor character varying(50),
    display_value character varying(50),
    orig_txt_null_flavor character varying(50),
    orig_txt_value text,
    orig_txt_description character varying(50)
);


--
-- Name: cd_datatype_value7; Type: TABLE 
--

CREATE TABLE cd_datatype_value7 (
    id integer NOT NULL,
    null_flavor character varying(50),
    code character varying(50),
    code_system character varying(50),
    code_system_name character varying(50),
    code_system_version character varying(50),
    display_null_flavor character varying(50),
    display_value character varying(50),
    orig_txt_null_flavor character varying(50),
    orig_txt_value text,
    orig_txt_description character varying(50)
);


--
-- Name: cd_datatype_value8; Type: TABLE 
--

CREATE TABLE cd_datatype_value8 (
    id integer NOT NULL,
    null_flavor character varying(50),
    code character varying(50),
    code_system character varying(50),
    code_system_name character varying(50),
    code_system_version character varying(50),
    display_null_flavor character varying(50),
    display_value character varying(50),
    orig_txt_null_flavor character varying(50),
    orig_txt_value text,
    orig_txt_description character varying(50)
);


--
-- Name: dset_ad_datatype; Type: TABLE 
--

CREATE TABLE dset_ad_datatype (
    id integer NOT NULL
);


--
-- Name: dset_ad_dset_ad_value8; Type: TABLE 
--

CREATE TABLE dset_ad_dset_ad_value8 (
    dset_ad_datatype_id integer NOT NULL,
    dset_ad_value8_id integer NOT NULL
);


--
-- Name: dset_ad_value1; Type: TABLE 
--

CREATE TABLE dset_ad_value1 (
    dset_ad_datatype_id integer NOT NULL,
    adxp_al_value character varying(50)
);


--
-- Name: dset_ad_value2; Type: TABLE 
--

CREATE TABLE dset_ad_value2 (
    dset_ad_datatype_id integer NOT NULL,
    adxp_dal_value character varying(50),
    adxp_dal_code character varying(50)
);


--
-- Name: dset_ad_value3; Type: TABLE 
--

CREATE TABLE dset_ad_value3 (
    dset_ad_datatype_id integer NOT NULL,
    adxp_al_value character varying(50),
    adxp_al_code character varying(50),
    adxp_al_codesystem character varying(50)
);


--
-- Name: dset_ad_value4; Type: TABLE 
--

CREATE TABLE dset_ad_value4 (
    dset_ad_datatype_id integer NOT NULL,
    adxp_al1_value character varying(50),
    adxp_al2_value character varying(50),
    adxp_al1_code character varying(50),
    adxp_al2_code character varying(50),
    adxp_al2_codesystem character varying(50),
    adxp_al1_codesystem character varying(50)
);


--
-- Name: dset_ad_value5; Type: TABLE 
--

CREATE TABLE dset_ad_value5 (
    dset_ad_datatype_id integer NOT NULL,
    adxp_al_value character varying(50),
    adxp_al_code character varying(50),
    adxp_al_codesystem character varying(50),
    adxp_dal_code character varying(50),
    adxp_dal_codesystem character varying(50),
    adxp_dal_value character varying(50),
    adxp_cty_value character varying(50),
    adxp_cty_code character varying(50),
    adxp_cty_codesystem character varying(50)
);


--
-- Name: dset_ad_value6; Type: TABLE 
--

CREATE TABLE dset_ad_value6 (
    dset_ad_datatype_id integer NOT NULL,
    adxp_adl_value character varying(50),
    adxp_al_value character varying(50),
    adxp_bnn_value character varying(50),
    adxp_bnr_value character varying(50),
    adxp_bns_value character varying(50),
    adxp_car_value character varying(50),
    adxp_cen_value character varying(50),
    adxp_cnt_value character varying(50),
    adxp_cpa_value character varying(50),
    adxp_cty_value character varying(50),
    adxp_dal_value character varying(50),
    adxp_del_value character varying(50),
    adxp_dinsta_value character varying(50),
    adxp_dinstq_value character varying(50),
    adxp_dir_value character varying(50),
    adxp_dmod_value character varying(50),
    adxp_dmodid_value character varying(50),
    adxp_int_value character varying(50),
    adxp_pob_value character varying(50),
    adxp_pre_value character varying(50),
    adxp_sal_value character varying(50),
    adxp_sta_value character varying(50),
    adxp_stb_value character varying(50),
    adxp_str_value character varying(50),
    adxp_sttyp_value character varying(50),
    adxp_unid_value character varying(50),
    adxp_unit_value character varying(50),
    adxp_zip_value character varying(50),
    adxp_adl_code character varying(50),
    adxp_bns_code character varying(50),
    adxp_bns_codesystem character varying(50),
    adxp_dal_code character varying(50),
    adxp_dal_codesystem character varying(50),
    adxp_int_code character varying(50),
    adxp_int_codesystem character varying(50),
    adxp_stb_code character varying(50),
    adxp_stb_codesystem character varying(50),
    adxp_zip_codesystem character varying(50),
    adxp_zip_code character varying(50)
);


--
-- Name: dset_ad_value7; Type: TABLE 
--

CREATE TABLE dset_ad_value7 (
    id integer NOT NULL,
    dset_ad_datatype_id integer,
    adxp_al_value character varying(50),
    adxp_al_code character varying(50),
    adxp_al_codesystem character varying(50),
    adxp_dal_code character varying(50),
    adxp_dal_codesystem character varying(50),
    adxp_dal_value character varying(50),
    adxp_cty_value character varying(50),
    adxp_cty_code character varying(50),
    adxp_cty_codesystem character varying(50)
);


--
-- Name: dset_ad_value8; Type: TABLE 
--

CREATE TABLE dset_ad_value8 (
    id integer NOT NULL,
    adxp_al_value character varying(50),
    adxp_al_code character varying(50),
    adxp_al_codesystem character varying(50),
    adxp_dal_code character varying(50),
    adxp_dal_codesystem character varying(50),
    adxp_dal_value character varying(50),
    adxp_cty_value character varying(50),
    adxp_cty_code character varying(50),
    adxp_cty_codesystem character varying(50)
);


--
-- Name: dset_cd; Type: TABLE 
--

CREATE TABLE dset_cd (
    id integer NOT NULL
);


--
-- Name: dset_cd_cd_value7; Type: TABLE 
--

CREATE TABLE dset_cd_cd_value7 (
    dset_cd_id integer NOT NULL,
    dset_cd_value7_id integer NOT NULL
);


--
-- Name: dset_cd_value1; Type: TABLE 
--

CREATE TABLE dset_cd_value1 (
    dset_cd_id integer NOT NULL,
    code character varying(50)
);


--
-- Name: dset_cd_value2; Type: TABLE 
--

CREATE TABLE dset_cd_value2 (
    dset_cd_id integer NOT NULL,
    code character varying(50),
    null_flavor character varying(50)
);


--
-- Name: dset_cd_value3; Type: TABLE 
--

CREATE TABLE dset_cd_value3 (
    dset_cd_id integer NOT NULL,
    code character varying(50),
    code_system character varying(50),
    code_system_name character varying(50)
);


--
-- Name: dset_cd_value4; Type: TABLE 
--

CREATE TABLE dset_cd_value4 (
    dset_cd_id integer NOT NULL,
    code character varying(50),
    code_system character varying(50),
    code_system_name character varying(50),
    code_system_version character varying(50),
    displayable_value character varying(50),
    originaltext_value character varying(50),
    originaltext_desc character varying(50)
);


--
-- Name: dset_cd_value5; Type: TABLE 
--

CREATE TABLE dset_cd_value5 (
    dset_cd_id integer NOT NULL,
    code character varying(50),
    code_system character varying(50),
    code_system_name character varying(50),
    code_system_version character varying(50),
    displayable_value character varying(50),
    originaltext_value character varying(50),
    originaltext_desc character varying(50)
);


--
-- Name: dset_cd_value6; Type: TABLE 
--

CREATE TABLE dset_cd_value6 (
    id integer NOT NULL,
    dset_cd_id integer,
    code character varying(50)
);


--
-- Name: dset_cd_value7; Type: TABLE 
--

CREATE TABLE dset_cd_value7 (
    id integer NOT NULL,
    code character varying(50)
);


--
-- Name: dset_en; Type: TABLE 
--

CREATE TABLE dset_en (
    id integer NOT NULL
);


--
-- Name: dset_en_value1; Type: TABLE 
--

CREATE TABLE dset_en_value1 (
    dset_en_datatype_id integer NOT NULL,
    enxp_pn_value character varying(50)
);


--
-- Name: dset_en_value2; Type: TABLE 
--

CREATE TABLE dset_en_value2 (
    dset_en_datatype_id integer NOT NULL,
    enxp_pn_value character varying(50),
    enxp_pn_code character varying(50)
);


--
-- Name: dset_en_value3; Type: TABLE 
--

CREATE TABLE dset_en_value3 (
    dset_en_datatype_id integer NOT NULL,
    enxp_pn_code character varying(50),
    enxp_pn_value character varying(50),
    enxp_pn_codesystem character varying(50),
    enxp_pn_codesystemversion character varying(50)
);


--
-- Name: dset_en_value4; Type: TABLE 
--

CREATE TABLE dset_en_value4 (
    dset_en_datatype_id integer NOT NULL,
    enxp_pn_value character varying(50),
    enxp_pn_enpq character varying(50)
);


--
-- Name: dset_en_value5; Type: TABLE 
--

CREATE TABLE dset_en_value5 (
    dset_en_datatype_id integer NOT NULL,
    enxp_pn_value character varying(50),
    enxp_pn2_value character varying(50),
    enxp_pn_code character varying(50),
    enxp_pn2_code character varying(50),
    enxp_pn_codesystem character varying(50),
    enxp_pn2_codesystem character varying(50),
    enxp_pn2_codesystemversion character varying(50),
    enxp_pn_codesystemversion character varying(50)
);


--
-- Name: dset_en_value6; Type: TABLE 
--

CREATE TABLE dset_en_value6 (
    dset_en_datatype_id integer NOT NULL,
    enxp_pn_value character varying(50),
    enxp_pn_code character varying(50),
    enxp_pn_codesystem character varying(50),
    enxp_pn_codesystemversion character varying(50),
    enxp_on_value character varying(50),
    enxp_on_code character varying(50),
    enxp_on_codesystem character varying(50),
    enxp_on_codesystemversion character varying(50)
);


--
-- Name: dset_en_value7; Type: TABLE 
--

CREATE TABLE dset_en_value7 (
    dset_en_datatype_id integer NOT NULL,
    enxp_pn_code character varying(50),
    enxp_on_code character varying(50),
    enxp_pn_value character varying(50),
    enxp_on_value character varying(50),
    enxp_on_codesystem character varying(50),
    enxp_pn_codesystem character varying(50),
    enxp_on_codesystemversion character varying(50),
    enxp_pn_codesystemversion character varying(50)
);


--
-- Name: dset_ii; Type: TABLE 
--

CREATE TABLE dset_ii (
    id integer NOT NULL
);


--
-- Name: dset_ii_ii_value6; Type: TABLE 
--

CREATE TABLE dset_ii_ii_value6 (
    dset_ii_id integer NOT NULL,
    dset_ii_value6_id integer NOT NULL
);


--
-- Name: dset_ii_value1; Type: TABLE 
--

CREATE TABLE dset_ii_value1 (
    dset_ii_id integer NOT NULL,
    extension character varying(50)
);


--
-- Name: dset_ii_value2; Type: TABLE 
--

CREATE TABLE dset_ii_value2 (
    dset_ii_id integer NOT NULL,
    extension character varying(50),
    root character varying(50),
    null_flavor character varying(50)
);


--
-- Name: dset_ii_value3; Type: TABLE 
--

CREATE TABLE dset_ii_value3 (
    dset_ii_id integer NOT NULL,
    extension character varying(50),
    identifier_name character varying(50),
    displayable_value character varying(1),
    reliability character varying(50),
    scope character varying(50)
);


--
-- Name: dset_ii_value4; Type: TABLE 
--

CREATE TABLE dset_ii_value4 (
    dset_ii_id integer NOT NULL,
    root character varying(50),
    extension character varying(50),
    identifier_name character varying(50),
    reliability character varying(50),
    scope character varying(50),
    displayable_value character varying(1),
    null_flavor character varying(50)
);


--
-- Name: dset_ii_value5; Type: TABLE 
--

CREATE TABLE dset_ii_value5 (
    id integer NOT NULL,
    dset_ii_id integer,
    extension character varying(50)
);


--
-- Name: dset_ii_value6; Type: TABLE 
--

CREATE TABLE dset_ii_value6 (
    id integer NOT NULL,
    extension character varying(50)
);


--
-- Name: dset_tel; Type: TABLE 
--

CREATE TABLE dset_tel (
    id integer NOT NULL,
    null_flavor character varying(50)
);


--
-- Name: dset_tel_email; Type: TABLE 
--

CREATE TABLE dset_tel_email (
    id integer NOT NULL,
    null_flavor character varying(50)
);


--
-- Name: dset_tel_email_value1; Type: TABLE 
--

CREATE TABLE dset_tel_email_value1 (
    dset_tel_email_id integer NOT NULL,
    tel_email_value character varying(50)
);


--
-- Name: dset_tel_email_value2; Type: TABLE 
--

CREATE TABLE dset_tel_email_value2 (
    dset_tel_email_id integer,
    tel_email_value character varying(50),
    tel_email_null_flavor character varying(50)
);


--
-- Name: dset_tel_person; Type: TABLE 
--

CREATE TABLE dset_tel_person (
    id integer NOT NULL,
    null_flavor character varying(50)
);


--
-- Name: dset_tel_person_value1; Type: TABLE 
--

CREATE TABLE dset_tel_person_value1 (
    dset_tel_person_id integer NOT NULL,
    tel_person_value character varying(50)
);


--
-- Name: dset_tel_phone; Type: TABLE 
--

CREATE TABLE dset_tel_phone (
    id integer NOT NULL,
    null_flavor character varying(50)
);


--
-- Name: dset_tel_phone_value1; Type: TABLE 
--

CREATE TABLE dset_tel_phone_value1 (
    dset_tel_phone_id integer NOT NULL,
    tel_phone_value character varying(50)
);


--
-- Name: dset_tel_tel_value_3; Type: TABLE 
--

CREATE TABLE dset_tel_tel_value_3 (
    dset_tel_id integer NOT NULL,
    dset_tel_value3_id integer NOT NULL
);


--
-- Name: dset_tel_url; Type: TABLE 
--

CREATE TABLE dset_tel_url (
    id integer NOT NULL,
    null_flavor character varying(50)
);


--
-- Name: dset_tel_url_value1; Type: TABLE 
--

CREATE TABLE dset_tel_url_value1 (
    dset_tel_url_id integer NOT NULL,
    tel_url_value character varying(50)
);


--
-- Name: dset_tel_value1; Type: TABLE 
--

CREATE TABLE dset_tel_value1 (
    dset_tel_id integer NOT NULL,
    tel_value character varying(50)
);


--
-- Name: dset_tel_value2; Type: TABLE 
--

CREATE TABLE dset_tel_value2 (
    id integer NOT NULL,
    dset_tel_id integer,
    tel_value character varying(50)
);


--
-- Name: dset_tel_value3; Type: TABLE 
--

CREATE TABLE dset_tel_value3 (
    id integer NOT NULL,
    tel_value character varying(50)
);


--
-- Name: ed_datatype; Type: TABLE 
--

CREATE TABLE ed_datatype (
    id integer NOT NULL,
    value1_data bytea,
    value2_null_flavor character varying(50),
    value2_data bytea,
    value2_compression character varying(50),
    value3_null_flavor character varying(50),
    value3_data bytea,
    value3_compression character varying(50),
    value3_description character varying(50),
    value3_value character varying(50)
);


--
-- Name: ed_text_datatype; Type: TABLE 
--

CREATE TABLE ed_text_datatype (
    id integer NOT NULL,
    value1_value character varying(50),
    value2_null_flavor character varying(50),
    value2_value character varying(50),
    value3_null_flavor character varying(50),
    value3_data bytea,
    value3_compression character varying(50),
    value3_description character varying(50),
    value3_value character varying(50)
);


--
-- Name: en_datatype; Type: TABLE 
--

CREATE TABLE en_datatype (
    id integer NOT NULL,
    value1_pn_value character varying(50),
    value2_pn_value character varying(50),
    value2_pn_code character varying(50),
    value3_pn_value character varying(50),
    value3_pn_code character varying(50),
    value3_pn_code_system character varying(50),
    value3_pn_code_system_version character varying(50),
    value4_pn_value character varying(50),
    value4_pn_enpq character varying(50),
    value5_pn_value character varying(50),
    value5_pn2_value character varying(50),
    value5_pn_code character varying(50),
    value5_pn2_code character varying(50),
    value5_pn_code_system character varying(50),
    value5_pn2_code_system character varying(50),
    value5_pn_code_system_version character varying(50),
    value5_pn2_code_system_version character varying(50),
    value6_pn_value character varying(50),
    value6_on_value character varying(50),
    value6_pn_code character varying(50),
    value6_on_code character varying(50),
    value6_pn_code_system character varying(50),
    value6_on_code_system character varying(50),
    value6_pn_code_system_version character varying(50),
    value6_on_code_system_version character varying(50)
);


--
-- Name: en_on_datatype; Type: TABLE 
--

CREATE TABLE en_on_datatype (
    id integer NOT NULL,
    value1_on_value character varying(50)
);


--
-- Name: en_pn_datatype; Type: TABLE 
--

CREATE TABLE en_pn_datatype (
    id integer NOT NULL,
    value1_pn_value character varying(50)
);


--
-- Name: ii_datatype; Type: TABLE 
--

CREATE TABLE ii_datatype (
    id integer NOT NULL,
    value1_extension character varying(50),
    value2_null_flavor character varying(50),
    value2_root character varying(50),
    value2_extension character varying(50),
    value3_null_flavor character varying(50),
    value3_extension character varying(50),
    value3_identifier_name character varying(50),
    value3_reliability character varying(50),
    value3_scope character varying(50),
    value3_displayable character varying(1),
    value4_null_flavor character varying(50),
    value4_root character varying(50),
    value4_extension character varying(50),
    value4_identifier_name character varying(50),
    value4_reliability character varying(50),
    value4_scope character varying(50),
    value4_displayable character varying(1)
);


--
-- Name: int_datatype; Type: TABLE 
--

CREATE TABLE int_datatype (
    id integer NOT NULL,
    value1_value numeric,
    value2_null_flavor character varying(50),
    value2_value numeric
);


--

-- Name: ivl_int; Type: TABLE 
--

CREATE TABLE ivl_int (
    id integer NOT NULL,
    value1_low_value numeric,
    value1_high_value numeric,
    value2_high_value numeric,
    value2_lowclosed character varying(1),
    value3_any_value numeric,
    value3_low_value numeric,
    value3_high_closed character varying(1),
    value4_low_value numeric,
    value4_high_value numeric,
    value4_width_value integer,
    value4_null_flavor character varying(50)
);


--

-- Name: ivl_pq; Type: TABLE 
--

CREATE TABLE ivl_pq (
    id integer NOT NULL,
    value1_low_value numeric,
    value2_low_value numeric,
    value2_low_precision numeric,
    value2_low_unit character varying(50),
    value3_low_value numeric,
    value3_low_precision numeric,
    value3_low_unit character varying(50),
    value3_null_flavor character varying(50),
    value4_high_value numeric,
    value4_high_precision numeric,
    value4_high_unit character varying(50),
    value4_high_closed character varying(1),
    value4_high_null_flavor character varying(50),
    value4_low_value numeric,
    value4_low_precision numeric,
    value4_low_unit character varying(50),
    value4_low_null_flavor character varying(50),
    value4_low_closed character varying(1),
    value4_width_value numeric,
    value4_width_precision numeric,
    value4_width_unit character varying(50),
    value4_width_null_flavor character varying(50),
    value4_null_flavor character varying(50)
);


--

-- Name: ivl_pqv; Type: TABLE 
--

CREATE TABLE ivl_pqv (
    id integer NOT NULL,
    value1_low_value numeric,
    value1_high_value numeric,
    value2_low_value numeric,
    value2_low_precision numeric,
    value2_high_closed character varying(1),
    value3_low_value numeric,
    value3_low_precision numeric,
    value3_high_value numeric,
    value3_high_precision numeric,
    value3_high_null_flavor character varying(50),
    value4_high_value numeric,
    value4_high_precision numeric,
    value4_high_null_flavor character varying(50),
    value4_low_value numeric,
    value4_low_precision numeric,
    value4_low_null_flavor character varying(50),
    value4_width_value numeric,
    value4_width_precision numeric,
    value4_width_null_flavor character varying(50)
);


--

-- Name: ivl_real; Type: TABLE 
--

CREATE TABLE ivl_real (
    id integer,
    value1_low_value double precision,
    value1_high_value double precision,
    value2_high_value double precision,
    value2_low_closed character varying(1),
    value3_any_value double precision,
    value3_high_value double precision,
    value3_high_closed character varying(1),
    value3_low_value double precision,
    value3_low_closed character varying(1),
    value3_width_value double precision,
    value3_width_null_flavor character varying(50),
    value3_null_flavor character varying(50)
);


--

-- Name: ivl_ts; Type: TABLE 
--

CREATE TABLE ivl_ts (
    id integer,
    value1_low_value timestamp without time zone,
    value1_high_value timestamp without time zone,
    value2_high_value timestamp without time zone,
    value2_low_closed character varying(1),
    value3_high_value timestamp without time zone,
    value3_low_value timestamp without time zone,
    value3_width_null_flavor character varying(50),
    value3_width_value integer,
    value3_null_flavor character varying(50)
);


--

-- Name: pq_datatype; Type: TABLE 
--

CREATE TABLE pq_datatype (
    id integer NOT NULL,
    value1_value numeric,
    value1_unit character varying(50),
    value2_value numeric,
    value3_null_flavor character varying(50),
    value3_unit character varying(50),
    value3_value numeric,
    value3_precision numeric
);


--
-- Name: pqv_datatype; Type: TABLE 
--

CREATE TABLE pqv_datatype (
    id integer NOT NULL,
    value1_value numeric,
    value2_null_flavor character varying(50),
    value2_value numeric,
    value2_precision numeric,
    value3_value numeric,
    value3_precision numeric,
    value4_null_flavor character varying(50),
    value4_precision numeric,
    value4_value numeric
);


--
-- Name: real_datatype; Type: TABLE 
--

CREATE TABLE real_datatype (
    id integer NOT NULL,
    value1_value double precision,
    value2_null_flavor character varying(50),
    value2_value double precision
);


--
-- Name: sc_datatype; Type: TABLE 
--

CREATE TABLE sc_datatype (
    id integer NOT NULL,
    value1_value character varying(50),
    value1_code_code character varying(50),
    value2_null_flavor character varying(50),
    value2_value character varying(50),
    value2_code_null_flavor character varying(50),
    value2_code_code character varying(50),
    value2_code_code_system character varying(50),
    value2_code_code_system_name character varying(50),
    value2_code_code_system_ver character varying(50),
    value3_null_flavor character varying(50),
    value3_value character varying(50),
    value3_code_null_flavor character varying(50),
    value3_code_code character varying(50),
    value3_code_code_system character varying(50),
    value3_code_code_system_name character varying(50),
    value3_code_code_system_ver character varying(50),
    value3_code_display_nflavor character varying(50),
    value3_code_display_value character varying(50),
    value3_code_orig_txt_nflavor character varying(50),
    value3_code_orig_txt_desc character varying(50),
    value3_code_orig_txt_value character varying(50)
);


--
-- Name: st_datatype; Type: TABLE 
--

CREATE TABLE st_datatype (
    id integer NOT NULL,
    value1_value character varying(50),
    value2_null_flavor character varying(50),
    value2_value character varying(50)
);


--
-- Name: st_nt_datatype; Type: TABLE 
--

CREATE TABLE st_nt_datatype (
    id integer NOT NULL,
    value1_value character varying(50),
    value2_null_flavor character varying(50),
    value2_value character varying(50)
);


--
-- Name: tel_datatype; Type: TABLE 
--

CREATE TABLE tel_datatype (
    id integer NOT NULL,
    value1_value character varying(50),
    value2_null_flavor character varying(50),
    value2_value character varying(50)
);


--
-- Name: tel_email_datatype; Type: TABLE 
--

CREATE TABLE tel_email_datatype (
    id integer NOT NULL,
    value1_value character varying(50),
    value2_null_flavor character varying(50),
    value2_value character varying(50)
);


--
-- Name: tel_person_datatype; Type: TABLE 
--

CREATE TABLE tel_person_datatype (
    id integer NOT NULL,
    value1_value character varying(50),
    value2_null_flavor character varying(50),
    value2_value character varying(50)
);


--
-- Name: tel_phone_datatype; Type: TABLE 
--

CREATE TABLE tel_phone_datatype (
    id integer NOT NULL,
    value1_value character varying(50),
    value2_null_flavor character varying(50),
    value2_value character varying(50)
);


--
-- Name: tel_url_datatype; Type: TABLE 
--

CREATE TABLE tel_url_datatype (
    id integer NOT NULL,
    value1_value character varying(50),
    value2_null_flavor character varying(50),
    value2_value character varying(50)
);


--
-- Name: ts_datatype; Type: TABLE 
--

CREATE TABLE ts_datatype (
    id integer NOT NULL,
    value1_value timestamp without time zone,
    value2_null_flavor character varying(50),
    value2_value timestamp without time zone
);


--
-- Data for Name: ad_ad_datatype_value9; Type: TABLE DATA; 
--

INSERT INTO ad_ad_datatype_value9 (ad_datatype_id, ad_datatype_value9_id) VALUES (44, 2);
INSERT INTO ad_ad_datatype_value9 (ad_datatype_id, ad_datatype_value9_id) VALUES (45, 2);
INSERT INTO ad_ad_datatype_value9 (ad_datatype_id, ad_datatype_value9_id) VALUES (46, 2);
INSERT INTO ad_ad_datatype_value9 (ad_datatype_id, ad_datatype_value9_id) VALUES (47, 2);
INSERT INTO ad_ad_datatype_value9 (ad_datatype_id, ad_datatype_value9_id) VALUES (48, 2);


--
-- Data for Name: ad_datatype; Type: TABLE DATA; 
--

INSERT INTO ad_datatype (id, value1_al_value, value2_dal_value, value2_dal_code, value3_al_value, value3_al_code, value3_al_codesystem, value4_al1_value, value4_al2_value, value4_al1_code, value4_al2_code, value4_al1_codesystem, value4_al2_codesystem, value5_al_value, value5_al_code, value5_al_codesystem, value5_dal_code, value5_dal_value, value5_dal_codesystem, value5_cty_value, value5_cty_code, value5_cty_codesystem, value6_adl_value, value6_al_value, value6_bnn_value, value6_bnr_value, value6_bns_value, value6_car_value, value6_cen_value, value6_cnt_value, value6_cpa_value, value6_cty_value, value6_dal_value, value6_del_value, value6_dinsta_value, value6_dinstq_value, value6_dir_value, value6_dmod_value, value6_dmodid_value, value6_int_value, value6_pob_value, value6_pre_value, value6_sal_value, value6_sta_value, value6_stb_value, value6_str_value, value6_sttyp_value, value6_unid_value, value6_unit_value, value6_zip_value, value6_adl_code, value6_bns_code, value6_bns_codesystem, value6_dal_code, value6_dal_codesystem, value6_int_code, value6_int_codesystem, value6_stb_code, value6_stb_codesystem, value6_zip_codesystem, value6_zip_code, ad_datatype_value8_id) VALUES (1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ad_datatype (id, value1_al_value, value2_dal_value, value2_dal_code, value3_al_value, value3_al_code, value3_al_codesystem, value4_al1_value, value4_al2_value, value4_al1_code, value4_al2_code, value4_al1_codesystem, value4_al2_codesystem, value5_al_value, value5_al_code, value5_al_codesystem, value5_dal_code, value5_dal_value, value5_dal_codesystem, value5_cty_value, value5_cty_code, value5_cty_codesystem, value6_adl_value, value6_al_value, value6_bnn_value, value6_bnr_value, value6_bns_value, value6_car_value, value6_cen_value, value6_cnt_value, value6_cpa_value, value6_cty_value, value6_dal_value, value6_del_value, value6_dinsta_value, value6_dinstq_value, value6_dir_value, value6_dmod_value, value6_dmodid_value, value6_int_value, value6_pob_value, value6_pre_value, value6_sal_value, value6_sta_value, value6_stb_value, value6_str_value, value6_sttyp_value, value6_unid_value, value6_unit_value, value6_zip_value, value6_adl_code, value6_bns_code, value6_bns_codesystem, value6_dal_code, value6_dal_codesystem, value6_int_code, value6_int_codesystem, value6_stb_code, value6_stb_codesystem, value6_zip_codesystem, value6_zip_code, ad_datatype_value8_id) VALUES (2, '1 Jefferson Street', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ad_datatype (id, value1_al_value, value2_dal_value, value2_dal_code, value3_al_value, value3_al_code, value3_al_codesystem, value4_al1_value, value4_al2_value, value4_al1_code, value4_al2_code, value4_al1_codesystem, value4_al2_codesystem, value5_al_value, value5_al_code, value5_al_codesystem, value5_dal_code, value5_dal_value, value5_dal_codesystem, value5_cty_value, value5_cty_code, value5_cty_codesystem, value6_adl_value, value6_al_value, value6_bnn_value, value6_bnr_value, value6_bns_value, value6_car_value, value6_cen_value, value6_cnt_value, value6_cpa_value, value6_cty_value, value6_dal_value, value6_del_value, value6_dinsta_value, value6_dinstq_value, value6_dir_value, value6_dmod_value, value6_dmodid_value, value6_int_value, value6_pob_value, value6_pre_value, value6_sal_value, value6_sta_value, value6_stb_value, value6_str_value, value6_sttyp_value, value6_unid_value, value6_unit_value, value6_zip_value, value6_adl_code, value6_bns_code, value6_bns_codesystem, value6_dal_code, value6_dal_codesystem, value6_int_code, value6_int_codesystem, value6_stb_code, value6_stb_codesystem, value6_zip_codesystem, value6_zip_code, ad_datatype_value8_id) VALUES (3, '2 E Jefferson Street', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ad_datatype (id, value1_al_value, value2_dal_value, value2_dal_code, value3_al_value, value3_al_code, value3_al_codesystem, value4_al1_value, value4_al2_value, value4_al1_code, value4_al2_code, value4_al1_codesystem, value4_al2_codesystem, value5_al_value, value5_al_code, value5_al_codesystem, value5_dal_code, value5_dal_value, value5_dal_codesystem, value5_cty_value, value5_cty_code, value5_cty_codesystem, value6_adl_value, value6_al_value, value6_bnn_value, value6_bnr_value, value6_bns_value, value6_car_value, value6_cen_value, value6_cnt_value, value6_cpa_value, value6_cty_value, value6_dal_value, value6_del_value, value6_dinsta_value, value6_dinstq_value, value6_dir_value, value6_dmod_value, value6_dmodid_value, value6_int_value, value6_pob_value, value6_pre_value, value6_sal_value, value6_sta_value, value6_stb_value, value6_str_value, value6_sttyp_value, value6_unid_value, value6_unit_value, value6_zip_value, value6_adl_code, value6_bns_code, value6_bns_codesystem, value6_dal_code, value6_dal_codesystem, value6_int_code, value6_int_codesystem, value6_stb_code, value6_stb_codesystem, value6_zip_codesystem, value6_zip_code, ad_datatype_value8_id) VALUES (4, '3 Jefferson Street', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ad_datatype (id, value1_al_value, value2_dal_value, value2_dal_code, value3_al_value, value3_al_code, value3_al_codesystem, value4_al1_value, value4_al2_value, value4_al1_code, value4_al2_code, value4_al1_codesystem, value4_al2_codesystem, value5_al_value, value5_al_code, value5_al_codesystem, value5_dal_code, value5_dal_value, value5_dal_codesystem, value5_cty_value, value5_cty_code, value5_cty_codesystem, value6_adl_value, value6_al_value, value6_bnn_value, value6_bnr_value, value6_bns_value, value6_car_value, value6_cen_value, value6_cnt_value, value6_cpa_value, value6_cty_value, value6_dal_value, value6_del_value, value6_dinsta_value, value6_dinstq_value, value6_dir_value, value6_dmod_value, value6_dmodid_value, value6_int_value, value6_pob_value, value6_pre_value, value6_sal_value, value6_sta_value, value6_stb_value, value6_str_value, value6_sttyp_value, value6_unid_value, value6_unit_value, value6_zip_value, value6_adl_code, value6_bns_code, value6_bns_codesystem, value6_dal_code, value6_dal_codesystem, value6_int_code, value6_int_codesystem, value6_stb_code, value6_stb_codesystem, value6_zip_codesystem, value6_zip_code, ad_datatype_value8_id) VALUES (5, '4 Sun Street', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ad_datatype (id, value1_al_value, value2_dal_value, value2_dal_code, value3_al_value, value3_al_code, value3_al_codesystem, value4_al1_value, value4_al2_value, value4_al1_code, value4_al2_code, value4_al1_codesystem, value4_al2_codesystem, value5_al_value, value5_al_code, value5_al_codesystem, value5_dal_code, value5_dal_value, value5_dal_codesystem, value5_cty_value, value5_cty_code, value5_cty_codesystem, value6_adl_value, value6_al_value, value6_bnn_value, value6_bnr_value, value6_bns_value, value6_car_value, value6_cen_value, value6_cnt_value, value6_cpa_value, value6_cty_value, value6_dal_value, value6_del_value, value6_dinsta_value, value6_dinstq_value, value6_dir_value, value6_dmod_value, value6_dmodid_value, value6_int_value, value6_pob_value, value6_pre_value, value6_sal_value, value6_sta_value, value6_stb_value, value6_str_value, value6_sttyp_value, value6_unid_value, value6_unit_value, value6_zip_value, value6_adl_code, value6_bns_code, value6_bns_codesystem, value6_dal_code, value6_dal_codesystem, value6_int_code, value6_int_codesystem, value6_stb_code, value6_stb_codesystem, value6_zip_codesystem, value6_zip_code, ad_datatype_value8_id) VALUES (6, '5 Sun Street', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ad_datatype (id, value1_al_value, value2_dal_value, value2_dal_code, value3_al_value, value3_al_code, value3_al_codesystem, value4_al1_value, value4_al2_value, value4_al1_code, value4_al2_code, value4_al1_codesystem, value4_al2_codesystem, value5_al_value, value5_al_code, value5_al_codesystem, value5_dal_code, value5_dal_value, value5_dal_codesystem, value5_cty_value, value5_cty_code, value5_cty_codesystem, value6_adl_value, value6_al_value, value6_bnn_value, value6_bnr_value, value6_bns_value, value6_car_value, value6_cen_value, value6_cnt_value, value6_cpa_value, value6_cty_value, value6_dal_value, value6_del_value, value6_dinsta_value, value6_dinstq_value, value6_dir_value, value6_dmod_value, value6_dmodid_value, value6_int_value, value6_pob_value, value6_pre_value, value6_sal_value, value6_sta_value, value6_stb_value, value6_str_value, value6_sttyp_value, value6_unid_value, value6_unit_value, value6_zip_value, value6_adl_code, value6_bns_code, value6_bns_codesystem, value6_dal_code, value6_dal_codesystem, value6_int_code, value6_int_codesystem, value6_stb_code, value6_stb_codesystem, value6_zip_codesystem, value6_zip_code, ad_datatype_value8_id) VALUES (7, NULL, '5th Floor', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ad_datatype (id, value1_al_value, value2_dal_value, value2_dal_code, value3_al_value, value3_al_code, value3_al_codesystem, value4_al1_value, value4_al2_value, value4_al1_code, value4_al2_code, value4_al1_codesystem, value4_al2_codesystem, value5_al_value, value5_al_code, value5_al_codesystem, value5_dal_code, value5_dal_value, value5_dal_codesystem, value5_cty_value, value5_cty_code, value5_cty_codesystem, value6_adl_value, value6_al_value, value6_bnn_value, value6_bnr_value, value6_bns_value, value6_car_value, value6_cen_value, value6_cnt_value, value6_cpa_value, value6_cty_value, value6_dal_value, value6_del_value, value6_dinsta_value, value6_dinstq_value, value6_dir_value, value6_dmod_value, value6_dmodid_value, value6_int_value, value6_pob_value, value6_pre_value, value6_sal_value, value6_sta_value, value6_stb_value, value6_str_value, value6_sttyp_value, value6_unid_value, value6_unit_value, value6_zip_value, value6_adl_code, value6_bns_code, value6_bns_codesystem, value6_dal_code, value6_dal_codesystem, value6_int_code, value6_int_codesystem, value6_stb_code, value6_stb_codesystem, value6_zip_codesystem, value6_zip_code, ad_datatype_value8_id) VALUES (8, NULL, '6th Floor', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ad_datatype (id, value1_al_value, value2_dal_value, value2_dal_code, value3_al_value, value3_al_code, value3_al_codesystem, value4_al1_value, value4_al2_value, value4_al1_code, value4_al2_code, value4_al1_codesystem, value4_al2_codesystem, value5_al_value, value5_al_code, value5_al_codesystem, value5_dal_code, value5_dal_value, value5_dal_codesystem, value5_cty_value, value5_cty_code, value5_cty_codesystem, value6_adl_value, value6_al_value, value6_bnn_value, value6_bnr_value, value6_bns_value, value6_car_value, value6_cen_value, value6_cnt_value, value6_cpa_value, value6_cty_value, value6_dal_value, value6_del_value, value6_dinsta_value, value6_dinstq_value, value6_dir_value, value6_dmod_value, value6_dmodid_value, value6_int_value, value6_pob_value, value6_pre_value, value6_sal_value, value6_sta_value, value6_stb_value, value6_str_value, value6_sttyp_value, value6_unid_value, value6_unit_value, value6_zip_value, value6_adl_code, value6_bns_code, value6_bns_codesystem, value6_dal_code, value6_dal_codesystem, value6_int_code, value6_int_codesystem, value6_stb_code, value6_stb_codesystem, value6_zip_codesystem, value6_zip_code, ad_datatype_value8_id) VALUES (9, NULL, '7th Floor', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ad_datatype (id, value1_al_value, value2_dal_value, value2_dal_code, value3_al_value, value3_al_code, value3_al_codesystem, value4_al1_value, value4_al2_value, value4_al1_code, value4_al2_code, value4_al1_codesystem, value4_al2_codesystem, value5_al_value, value5_al_code, value5_al_codesystem, value5_dal_code, value5_dal_value, value5_dal_codesystem, value5_cty_value, value5_cty_code, value5_cty_codesystem, value6_adl_value, value6_al_value, value6_bnn_value, value6_bnr_value, value6_bns_value, value6_car_value, value6_cen_value, value6_cnt_value, value6_cpa_value, value6_cty_value, value6_dal_value, value6_del_value, value6_dinsta_value, value6_dinstq_value, value6_dir_value, value6_dmod_value, value6_dmodid_value, value6_int_value, value6_pob_value, value6_pre_value, value6_sal_value, value6_sta_value, value6_stb_value, value6_str_value, value6_sttyp_value, value6_unid_value, value6_unit_value, value6_zip_value, value6_adl_code, value6_bns_code, value6_bns_codesystem, value6_dal_code, value6_dal_codesystem, value6_int_code, value6_int_codesystem, value6_stb_code, value6_stb_codesystem, value6_zip_codesystem, value6_zip_code, ad_datatype_value8_id) VALUES (10, NULL, '8th Floor', 'NCI5', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ad_datatype (id, value1_al_value, value2_dal_value, value2_dal_code, value3_al_value, value3_al_code, value3_al_codesystem, value4_al1_value, value4_al2_value, value4_al1_code, value4_al2_code, value4_al1_codesystem, value4_al2_codesystem, value5_al_value, value5_al_code, value5_al_codesystem, value5_dal_code, value5_dal_value, value5_dal_codesystem, value5_cty_value, value5_cty_code, value5_cty_codesystem, value6_adl_value, value6_al_value, value6_bnn_value, value6_bnr_value, value6_bns_value, value6_car_value, value6_cen_value, value6_cnt_value, value6_cpa_value, value6_cty_value, value6_dal_value, value6_del_value, value6_dinsta_value, value6_dinstq_value, value6_dir_value, value6_dmod_value, value6_dmodid_value, value6_int_value, value6_pob_value, value6_pre_value, value6_sal_value, value6_sta_value, value6_stb_value, value6_str_value, value6_sttyp_value, value6_unid_value, value6_unit_value, value6_zip_value, value6_adl_code, value6_bns_code, value6_bns_codesystem, value6_dal_code, value6_dal_codesystem, value6_int_code, value6_int_codesystem, value6_stb_code, value6_stb_codesystem, value6_zip_codesystem, value6_zip_code, ad_datatype_value8_id) VALUES (11, NULL, '9th Floor', 'NCI6', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ad_datatype (id, value1_al_value, value2_dal_value, value2_dal_code, value3_al_value, value3_al_code, value3_al_codesystem, value4_al1_value, value4_al2_value, value4_al1_code, value4_al2_code, value4_al1_codesystem, value4_al2_codesystem, value5_al_value, value5_al_code, value5_al_codesystem, value5_dal_code, value5_dal_value, value5_dal_codesystem, value5_cty_value, value5_cty_code, value5_cty_codesystem, value6_adl_value, value6_al_value, value6_bnn_value, value6_bnr_value, value6_bns_value, value6_car_value, value6_cen_value, value6_cnt_value, value6_cpa_value, value6_cty_value, value6_dal_value, value6_del_value, value6_dinsta_value, value6_dinstq_value, value6_dir_value, value6_dmod_value, value6_dmodid_value, value6_int_value, value6_pob_value, value6_pre_value, value6_sal_value, value6_sta_value, value6_stb_value, value6_str_value, value6_sttyp_value, value6_unid_value, value6_unit_value, value6_zip_value, value6_adl_code, value6_bns_code, value6_bns_codesystem, value6_dal_code, value6_dal_codesystem, value6_int_code, value6_int_codesystem, value6_stb_code, value6_stb_codesystem, value6_zip_codesystem, value6_zip_code, ad_datatype_value8_id) VALUES (12, NULL, NULL, NULL, 'E Jefferson Street', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ad_datatype (id, value1_al_value, value2_dal_value, value2_dal_code, value3_al_value, value3_al_code, value3_al_codesystem, value4_al1_value, value4_al2_value, value4_al1_code, value4_al2_code, value4_al1_codesystem, value4_al2_codesystem, value5_al_value, value5_al_code, value5_al_codesystem, value5_dal_code, value5_dal_value, value5_dal_codesystem, value5_cty_value, value5_cty_code, value5_cty_codesystem, value6_adl_value, value6_al_value, value6_bnn_value, value6_bnr_value, value6_bns_value, value6_car_value, value6_cen_value, value6_cnt_value, value6_cpa_value, value6_cty_value, value6_dal_value, value6_del_value, value6_dinsta_value, value6_dinstq_value, value6_dir_value, value6_dmod_value, value6_dmodid_value, value6_int_value, value6_pob_value, value6_pre_value, value6_sal_value, value6_sta_value, value6_stb_value, value6_str_value, value6_sttyp_value, value6_unid_value, value6_unit_value, value6_zip_value, value6_adl_code, value6_bns_code, value6_bns_codesystem, value6_dal_code, value6_dal_codesystem, value6_int_code, value6_int_codesystem, value6_stb_code, value6_stb_codesystem, value6_zip_codesystem, value6_zip_code, ad_datatype_value8_id) VALUES (13, NULL, NULL, NULL, 'E Jefferson Street', 'NCI', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ad_datatype (id, value1_al_value, value2_dal_value, value2_dal_code, value3_al_value, value3_al_code, value3_al_codesystem, value4_al1_value, value4_al2_value, value4_al1_code, value4_al2_code, value4_al1_codesystem, value4_al2_codesystem, value5_al_value, value5_al_code, value5_al_codesystem, value5_dal_code, value5_dal_value, value5_dal_codesystem, value5_cty_value, value5_cty_code, value5_cty_codesystem, value6_adl_value, value6_al_value, value6_bnn_value, value6_bnr_value, value6_bns_value, value6_car_value, value6_cen_value, value6_cnt_value, value6_cpa_value, value6_cty_value, value6_dal_value, value6_del_value, value6_dinsta_value, value6_dinstq_value, value6_dir_value, value6_dmod_value, value6_dmodid_value, value6_int_value, value6_pob_value, value6_pre_value, value6_sal_value, value6_sta_value, value6_stb_value, value6_str_value, value6_sttyp_value, value6_unid_value, value6_unit_value, value6_zip_value, value6_adl_code, value6_bns_code, value6_bns_codesystem, value6_dal_code, value6_dal_codesystem, value6_int_code, value6_int_codesystem, value6_stb_code, value6_stb_codesystem, value6_zip_codesystem, value6_zip_code, ad_datatype_value8_id) VALUES (14, NULL, NULL, NULL, 'Jefferson Street', 'NCI1', 'codeSystem1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ad_datatype (id, value1_al_value, value2_dal_value, value2_dal_code, value3_al_value, value3_al_code, value3_al_codesystem, value4_al1_value, value4_al2_value, value4_al1_code, value4_al2_code, value4_al1_codesystem, value4_al2_codesystem, value5_al_value, value5_al_code, value5_al_codesystem, value5_dal_code, value5_dal_value, value5_dal_codesystem, value5_cty_value, value5_cty_code, value5_cty_codesystem, value6_adl_value, value6_al_value, value6_bnn_value, value6_bnr_value, value6_bns_value, value6_car_value, value6_cen_value, value6_cnt_value, value6_cpa_value, value6_cty_value, value6_dal_value, value6_del_value, value6_dinsta_value, value6_dinstq_value, value6_dir_value, value6_dmod_value, value6_dmodid_value, value6_int_value, value6_pob_value, value6_pre_value, value6_sal_value, value6_sta_value, value6_stb_value, value6_str_value, value6_sttyp_value, value6_unid_value, value6_unit_value, value6_zip_value, value6_adl_code, value6_bns_code, value6_bns_codesystem, value6_dal_code, value6_dal_codesystem, value6_int_code, value6_int_codesystem, value6_stb_code, value6_stb_codesystem, value6_zip_codesystem, value6_zip_code, ad_datatype_value8_id) VALUES (15, NULL, NULL, NULL, 'F Jefferson Street', 'NCI2', 'codeSystem2', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ad_datatype (id, value1_al_value, value2_dal_value, value2_dal_code, value3_al_value, value3_al_code, value3_al_codesystem, value4_al1_value, value4_al2_value, value4_al1_code, value4_al2_code, value4_al1_codesystem, value4_al2_codesystem, value5_al_value, value5_al_code, value5_al_codesystem, value5_dal_code, value5_dal_value, value5_dal_codesystem, value5_cty_value, value5_cty_code, value5_cty_codesystem, value6_adl_value, value6_al_value, value6_bnn_value, value6_bnr_value, value6_bns_value, value6_car_value, value6_cen_value, value6_cnt_value, value6_cpa_value, value6_cty_value, value6_dal_value, value6_del_value, value6_dinsta_value, value6_dinstq_value, value6_dir_value, value6_dmod_value, value6_dmodid_value, value6_int_value, value6_pob_value, value6_pre_value, value6_sal_value, value6_sta_value, value6_stb_value, value6_str_value, value6_sttyp_value, value6_unid_value, value6_unit_value, value6_zip_value, value6_adl_code, value6_bns_code, value6_bns_codesystem, value6_dal_code, value6_dal_codesystem, value6_int_code, value6_int_codesystem, value6_stb_code, value6_stb_codesystem, value6_zip_codesystem, value6_zip_code, ad_datatype_value8_id) VALUES (16, NULL, NULL, NULL, 'G Jefferson Street', 'NCI3', 'codeSystem3', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ad_datatype (id, value1_al_value, value2_dal_value, value2_dal_code, value3_al_value, value3_al_code, value3_al_codesystem, value4_al1_value, value4_al2_value, value4_al1_code, value4_al2_code, value4_al1_codesystem, value4_al2_codesystem, value5_al_value, value5_al_code, value5_al_codesystem, value5_dal_code, value5_dal_value, value5_dal_codesystem, value5_cty_value, value5_cty_code, value5_cty_codesystem, value6_adl_value, value6_al_value, value6_bnn_value, value6_bnr_value, value6_bns_value, value6_car_value, value6_cen_value, value6_cnt_value, value6_cpa_value, value6_cty_value, value6_dal_value, value6_del_value, value6_dinsta_value, value6_dinstq_value, value6_dir_value, value6_dmod_value, value6_dmodid_value, value6_int_value, value6_pob_value, value6_pre_value, value6_sal_value, value6_sta_value, value6_stb_value, value6_str_value, value6_sttyp_value, value6_unid_value, value6_unit_value, value6_zip_value, value6_adl_code, value6_bns_code, value6_bns_codesystem, value6_dal_code, value6_dal_codesystem, value6_int_code, value6_int_codesystem, value6_stb_code, value6_stb_codesystem, value6_zip_codesystem, value6_zip_code, ad_datatype_value8_id) VALUES (17, NULL, NULL, NULL, NULL, NULL, NULL, 'E Jefferson Street', NULL, 'NCI1', NULL, 'codeSystem1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ad_datatype (id, value1_al_value, value2_dal_value, value2_dal_code, value3_al_value, value3_al_code, value3_al_codesystem, value4_al1_value, value4_al2_value, value4_al1_code, value4_al2_code, value4_al1_codesystem, value4_al2_codesystem, value5_al_value, value5_al_code, value5_al_codesystem, value5_dal_code, value5_dal_value, value5_dal_codesystem, value5_cty_value, value5_cty_code, value5_cty_codesystem, value6_adl_value, value6_al_value, value6_bnn_value, value6_bnr_value, value6_bns_value, value6_car_value, value6_cen_value, value6_cnt_value, value6_cpa_value, value6_cty_value, value6_dal_value, value6_del_value, value6_dinsta_value, value6_dinstq_value, value6_dir_value, value6_dmod_value, value6_dmodid_value, value6_int_value, value6_pob_value, value6_pre_value, value6_sal_value, value6_sta_value, value6_stb_value, value6_str_value, value6_sttyp_value, value6_unid_value, value6_unit_value, value6_zip_value, value6_adl_code, value6_bns_code, value6_bns_codesystem, value6_dal_code, value6_dal_codesystem, value6_int_code, value6_int_codesystem, value6_stb_code, value6_stb_codesystem, value6_zip_codesystem, value6_zip_code, ad_datatype_value8_id) VALUES (18, NULL, NULL, NULL, NULL, NULL, NULL, 'F Jefferson Street', NULL, 'NCI2', NULL, 'codeSystem2', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ad_datatype (id, value1_al_value, value2_dal_value, value2_dal_code, value3_al_value, value3_al_code, value3_al_codesystem, value4_al1_value, value4_al2_value, value4_al1_code, value4_al2_code, value4_al1_codesystem, value4_al2_codesystem, value5_al_value, value5_al_code, value5_al_codesystem, value5_dal_code, value5_dal_value, value5_dal_codesystem, value5_cty_value, value5_cty_code, value5_cty_codesystem, value6_adl_value, value6_al_value, value6_bnn_value, value6_bnr_value, value6_bns_value, value6_car_value, value6_cen_value, value6_cnt_value, value6_cpa_value, value6_cty_value, value6_dal_value, value6_del_value, value6_dinsta_value, value6_dinstq_value, value6_dir_value, value6_dmod_value, value6_dmodid_value, value6_int_value, value6_pob_value, value6_pre_value, value6_sal_value, value6_sta_value, value6_stb_value, value6_str_value, value6_sttyp_value, value6_unid_value, value6_unit_value, value6_zip_value, value6_adl_code, value6_bns_code, value6_bns_codesystem, value6_dal_code, value6_dal_codesystem, value6_int_code, value6_int_codesystem, value6_stb_code, value6_stb_codesystem, value6_zip_codesystem, value6_zip_code, ad_datatype_value8_id) VALUES (19, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'E Jefferson Street', NULL, 'NCI-DC1', NULL, 'codeSystem2', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ad_datatype (id, value1_al_value, value2_dal_value, value2_dal_code, value3_al_value, value3_al_code, value3_al_codesystem, value4_al1_value, value4_al2_value, value4_al1_code, value4_al2_code, value4_al1_codesystem, value4_al2_codesystem, value5_al_value, value5_al_code, value5_al_codesystem, value5_dal_code, value5_dal_value, value5_dal_codesystem, value5_cty_value, value5_cty_code, value5_cty_codesystem, value6_adl_value, value6_al_value, value6_bnn_value, value6_bnr_value, value6_bns_value, value6_car_value, value6_cen_value, value6_cnt_value, value6_cpa_value, value6_cty_value, value6_dal_value, value6_del_value, value6_dinsta_value, value6_dinstq_value, value6_dir_value, value6_dmod_value, value6_dmodid_value, value6_int_value, value6_pob_value, value6_pre_value, value6_sal_value, value6_sta_value, value6_stb_value, value6_str_value, value6_sttyp_value, value6_unid_value, value6_unit_value, value6_zip_value, value6_adl_code, value6_bns_code, value6_bns_codesystem, value6_dal_code, value6_dal_codesystem, value6_int_code, value6_int_codesystem, value6_stb_code, value6_stb_codesystem, value6_zip_codesystem, value6_zip_code, ad_datatype_value8_id) VALUES (20, NULL, NULL, NULL, NULL, NULL, NULL, 'F Jefferson Street', 'F Jefferson Street', NULL, 'NCI-DC2', NULL, 'codeSystem2', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ad_datatype (id, value1_al_value, value2_dal_value, value2_dal_code, value3_al_value, value3_al_code, value3_al_codesystem, value4_al1_value, value4_al2_value, value4_al1_code, value4_al2_code, value4_al1_codesystem, value4_al2_codesystem, value5_al_value, value5_al_code, value5_al_codesystem, value5_dal_code, value5_dal_value, value5_dal_codesystem, value5_cty_value, value5_cty_code, value5_cty_codesystem, value6_adl_value, value6_al_value, value6_bnn_value, value6_bnr_value, value6_bns_value, value6_car_value, value6_cen_value, value6_cnt_value, value6_cpa_value, value6_cty_value, value6_dal_value, value6_del_value, value6_dinsta_value, value6_dinstq_value, value6_dir_value, value6_dmod_value, value6_dmodid_value, value6_int_value, value6_pob_value, value6_pre_value, value6_sal_value, value6_sta_value, value6_stb_value, value6_str_value, value6_sttyp_value, value6_unid_value, value6_unit_value, value6_zip_value, value6_adl_code, value6_bns_code, value6_bns_codesystem, value6_dal_code, value6_dal_codesystem, value6_int_code, value6_int_codesystem, value6_stb_code, value6_stb_codesystem, value6_zip_codesystem, value6_zip_code, ad_datatype_value8_id) VALUES (21, NULL, NULL, NULL, NULL, NULL, NULL, 'G Jefferson Street', 'G Jefferson Street', 'NCI3', 'NCI-DC3', NULL, 'codeSystem2', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ad_datatype (id, value1_al_value, value2_dal_value, value2_dal_code, value3_al_value, value3_al_code, value3_al_codesystem, value4_al1_value, value4_al2_value, value4_al1_code, value4_al2_code, value4_al1_codesystem, value4_al2_codesystem, value5_al_value, value5_al_code, value5_al_codesystem, value5_dal_code, value5_dal_value, value5_dal_codesystem, value5_cty_value, value5_cty_code, value5_cty_codesystem, value6_adl_value, value6_al_value, value6_bnn_value, value6_bnr_value, value6_bns_value, value6_car_value, value6_cen_value, value6_cnt_value, value6_cpa_value, value6_cty_value, value6_dal_value, value6_del_value, value6_dinsta_value, value6_dinstq_value, value6_dir_value, value6_dmod_value, value6_dmodid_value, value6_int_value, value6_pob_value, value6_pre_value, value6_sal_value, value6_sta_value, value6_stb_value, value6_str_value, value6_sttyp_value, value6_unid_value, value6_unit_value, value6_zip_value, value6_adl_code, value6_bns_code, value6_bns_codesystem, value6_dal_code, value6_dal_codesystem, value6_int_code, value6_int_codesystem, value6_stb_code, value6_stb_codesystem, value6_zip_codesystem, value6_zip_code, ad_datatype_value8_id) VALUES (22, NULL, NULL, NULL, NULL, NULL, NULL, 'H Jefferson Street', 'H Jefferson Street', 'NCI4', 'NCI-DC4', 'codeSystem4', 'codeSystem2', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ad_datatype (id, value1_al_value, value2_dal_value, value2_dal_code, value3_al_value, value3_al_code, value3_al_codesystem, value4_al1_value, value4_al2_value, value4_al1_code, value4_al2_code, value4_al1_codesystem, value4_al2_codesystem, value5_al_value, value5_al_code, value5_al_codesystem, value5_dal_code, value5_dal_value, value5_dal_codesystem, value5_cty_value, value5_cty_code, value5_cty_codesystem, value6_adl_value, value6_al_value, value6_bnn_value, value6_bnr_value, value6_bns_value, value6_car_value, value6_cen_value, value6_cnt_value, value6_cpa_value, value6_cty_value, value6_dal_value, value6_del_value, value6_dinsta_value, value6_dinstq_value, value6_dir_value, value6_dmod_value, value6_dmodid_value, value6_int_value, value6_pob_value, value6_pre_value, value6_sal_value, value6_sta_value, value6_stb_value, value6_str_value, value6_sttyp_value, value6_unid_value, value6_unit_value, value6_zip_value, value6_adl_code, value6_bns_code, value6_bns_codesystem, value6_dal_code, value6_dal_codesystem, value6_int_code, value6_int_codesystem, value6_stb_code, value6_stb_codesystem, value6_zip_codesystem, value6_zip_code, ad_datatype_value8_id) VALUES (23, NULL, NULL, NULL, NULL, NULL, NULL, 'I Jefferson Street', 'I Jefferson Street', 'NCI5', 'NCI-DC5', 'codeSystem5', 'codeSystem2', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ad_datatype (id, value1_al_value, value2_dal_value, value2_dal_code, value3_al_value, value3_al_code, value3_al_codesystem, value4_al1_value, value4_al2_value, value4_al1_code, value4_al2_code, value4_al1_codesystem, value4_al2_codesystem, value5_al_value, value5_al_code, value5_al_codesystem, value5_dal_code, value5_dal_value, value5_dal_codesystem, value5_cty_value, value5_cty_code, value5_cty_codesystem, value6_adl_value, value6_al_value, value6_bnn_value, value6_bnr_value, value6_bns_value, value6_car_value, value6_cen_value, value6_cnt_value, value6_cpa_value, value6_cty_value, value6_dal_value, value6_del_value, value6_dinsta_value, value6_dinstq_value, value6_dir_value, value6_dmod_value, value6_dmodid_value, value6_int_value, value6_pob_value, value6_pre_value, value6_sal_value, value6_sta_value, value6_stb_value, value6_str_value, value6_sttyp_value, value6_unid_value, value6_unit_value, value6_zip_value, value6_adl_code, value6_bns_code, value6_bns_codesystem, value6_dal_code, value6_dal_codesystem, value6_int_code, value6_int_codesystem, value6_stb_code, value6_stb_codesystem, value6_zip_codesystem, value6_zip_code, ad_datatype_value8_id) VALUES (24, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'Address line1', NULL, NULL, NULL, 'Delivery line1', NULL, 'Rockville', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ad_datatype (id, value1_al_value, value2_dal_value, value2_dal_code, value3_al_value, value3_al_code, value3_al_codesystem, value4_al1_value, value4_al2_value, value4_al1_code, value4_al2_code, value4_al1_codesystem, value4_al2_codesystem, value5_al_value, value5_al_code, value5_al_codesystem, value5_dal_code, value5_dal_value, value5_dal_codesystem, value5_cty_value, value5_cty_code, value5_cty_codesystem, value6_adl_value, value6_al_value, value6_bnn_value, value6_bnr_value, value6_bns_value, value6_car_value, value6_cen_value, value6_cnt_value, value6_cpa_value, value6_cty_value, value6_dal_value, value6_del_value, value6_dinsta_value, value6_dinstq_value, value6_dir_value, value6_dmod_value, value6_dmodid_value, value6_int_value, value6_pob_value, value6_pre_value, value6_sal_value, value6_sta_value, value6_stb_value, value6_str_value, value6_sttyp_value, value6_unid_value, value6_unit_value, value6_zip_value, value6_adl_code, value6_bns_code, value6_bns_codesystem, value6_dal_code, value6_dal_codesystem, value6_int_code, value6_int_codesystem, value6_stb_code, value6_stb_codesystem, value6_zip_codesystem, value6_zip_code, ad_datatype_value8_id) VALUES (25, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'Address line1', 'AL_CODE1', NULL, 'DAL_CODE1', 'Delivery line1', NULL, 'Rockville', 'CITY_CODE1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ad_datatype (id, value1_al_value, value2_dal_value, value2_dal_code, value3_al_value, value3_al_code, value3_al_codesystem, value4_al1_value, value4_al2_value, value4_al1_code, value4_al2_code, value4_al1_codesystem, value4_al2_codesystem, value5_al_value, value5_al_code, value5_al_codesystem, value5_dal_code, value5_dal_value, value5_dal_codesystem, value5_cty_value, value5_cty_code, value5_cty_codesystem, value6_adl_value, value6_al_value, value6_bnn_value, value6_bnr_value, value6_bns_value, value6_car_value, value6_cen_value, value6_cnt_value, value6_cpa_value, value6_cty_value, value6_dal_value, value6_del_value, value6_dinsta_value, value6_dinstq_value, value6_dir_value, value6_dmod_value, value6_dmodid_value, value6_int_value, value6_pob_value, value6_pre_value, value6_sal_value, value6_sta_value, value6_stb_value, value6_str_value, value6_sttyp_value, value6_unid_value, value6_unit_value, value6_zip_value, value6_adl_code, value6_bns_code, value6_bns_codesystem, value6_dal_code, value6_dal_codesystem, value6_int_code, value6_int_codesystem, value6_stb_code, value6_stb_codesystem, value6_zip_codesystem, value6_zip_code, ad_datatype_value8_id) VALUES (26, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'Address line1', NULL, 'AL_CODE_SYSTEM1', NULL, 'Delivery line1', 'DAL_CODE_SYSTEM1', 'Rockville', NULL, 'CITY_CODE_SYSTEM', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ad_datatype (id, value1_al_value, value2_dal_value, value2_dal_code, value3_al_value, value3_al_code, value3_al_codesystem, value4_al1_value, value4_al2_value, value4_al1_code, value4_al2_code, value4_al1_codesystem, value4_al2_codesystem, value5_al_value, value5_al_code, value5_al_codesystem, value5_dal_code, value5_dal_value, value5_dal_codesystem, value5_cty_value, value5_cty_code, value5_cty_codesystem, value6_adl_value, value6_al_value, value6_bnn_value, value6_bnr_value, value6_bns_value, value6_car_value, value6_cen_value, value6_cnt_value, value6_cpa_value, value6_cty_value, value6_dal_value, value6_del_value, value6_dinsta_value, value6_dinstq_value, value6_dir_value, value6_dmod_value, value6_dmodid_value, value6_int_value, value6_pob_value, value6_pre_value, value6_sal_value, value6_sta_value, value6_stb_value, value6_str_value, value6_sttyp_value, value6_unid_value, value6_unit_value, value6_zip_value, value6_adl_code, value6_bns_code, value6_bns_codesystem, value6_dal_code, value6_dal_codesystem, value6_int_code, value6_int_codesystem, value6_stb_code, value6_stb_codesystem, value6_zip_codesystem, value6_zip_code, ad_datatype_value8_id) VALUES (27, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'Address line1', 'AL_CODE2', 'AL_CODE_SYSTEM1', 'DAL_CODE2', 'Delivery line1', 'DAL_CODE_SYSTEM2', 'Rockville', 'CITY_CODE2', 'CITY_CODE_SYSTEM', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ad_datatype (id, value1_al_value, value2_dal_value, value2_dal_code, value3_al_value, value3_al_code, value3_al_codesystem, value4_al1_value, value4_al2_value, value4_al1_code, value4_al2_code, value4_al1_codesystem, value4_al2_codesystem, value5_al_value, value5_al_code, value5_al_codesystem, value5_dal_code, value5_dal_value, value5_dal_codesystem, value5_cty_value, value5_cty_code, value5_cty_codesystem, value6_adl_value, value6_al_value, value6_bnn_value, value6_bnr_value, value6_bns_value, value6_car_value, value6_cen_value, value6_cnt_value, value6_cpa_value, value6_cty_value, value6_dal_value, value6_del_value, value6_dinsta_value, value6_dinstq_value, value6_dir_value, value6_dmod_value, value6_dmodid_value, value6_int_value, value6_pob_value, value6_pre_value, value6_sal_value, value6_sta_value, value6_stb_value, value6_str_value, value6_sttyp_value, value6_unid_value, value6_unit_value, value6_zip_value, value6_adl_code, value6_bns_code, value6_bns_codesystem, value6_dal_code, value6_dal_codesystem, value6_int_code, value6_int_codesystem, value6_stb_code, value6_stb_codesystem, value6_zip_codesystem, value6_zip_code, ad_datatype_value8_id) VALUES (28, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'Address line1', 'AL_CODE3', 'AL_CODE_SYSTEM1', 'DAL_CODE3', 'Delivery line1', 'DAL_CODE_SYSTEM3', 'Rockville', 'CITY_CODE3', 'CITY_CODE_SYSTEM', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ad_datatype (id, value1_al_value, value2_dal_value, value2_dal_code, value3_al_value, value3_al_code, value3_al_codesystem, value4_al1_value, value4_al2_value, value4_al1_code, value4_al2_code, value4_al1_codesystem, value4_al2_codesystem, value5_al_value, value5_al_code, value5_al_codesystem, value5_dal_code, value5_dal_value, value5_dal_codesystem, value5_cty_value, value5_cty_code, value5_cty_codesystem, value6_adl_value, value6_al_value, value6_bnn_value, value6_bnr_value, value6_bns_value, value6_car_value, value6_cen_value, value6_cnt_value, value6_cpa_value, value6_cty_value, value6_dal_value, value6_del_value, value6_dinsta_value, value6_dinstq_value, value6_dir_value, value6_dmod_value, value6_dmodid_value, value6_int_value, value6_pob_value, value6_pre_value, value6_sal_value, value6_sta_value, value6_stb_value, value6_str_value, value6_sttyp_value, value6_unid_value, value6_unit_value, value6_zip_value, value6_adl_code, value6_bns_code, value6_bns_codesystem, value6_dal_code, value6_dal_codesystem, value6_int_code, value6_int_codesystem, value6_stb_code, value6_stb_codesystem, value6_zip_codesystem, value6_zip_code, ad_datatype_value8_id) VALUES (29, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'VALUE6_ADL_VALUE1', 'VALUE6_AL_VALUE1', 'VALUE6_BNN_VALUE1', 'VALUE6_BNR_VALUE1', 'VALUE6_BNS_VALUE1', 'VALUE6_CAR_VALUE1', 'VALUE6_CEN_VALUE1', 'VALUE6_CNT_VALUE1', 'VALUE6_CPA_VALUE1', 'VALUE6_CTY_VALUE1', 'VALUE6_DAL_VALUE1', 'VALUE6_DEL_VALUE1', 'VALUE6_DINSTA_VALUE1', 'VALUE6_DINSTQ_VALUE1', 'VALUE6_DIR_VALUE1', 'VALUE6_DMOD_VALUE1', 'VALUE6_DMODID_VALUE1', 'VALUE6_INT_VALUE1', 'VALUE6_POB_VALUE1', 'VALUE6_PRE_VALUE1', 'VALUE6_SAL_VALUE1', 'VALUE6_STA_VALUE1', 'VALUE6_STB_VALUE1', 'VALUE6_STR_VALUE1', 'VALUE6_STTYP_VALUE1', 'VALUE6_UNID_VALUE1', 'VALUE6_UNIT_VALUE1', 'VALUE6_ZIP_VALUE1', 'VALUE6_ADL_CODE1', 'VALUE6_BNS_CODE1', 'VALUE6_BNS_CODESYSTEM1', 'VALUE6_DAL_CODE1', 'VALUE6_DAL_CODESYSTEM1', 'VALUE6_INT_CODE1', 'VALUE6_INT_CODESYSTEM1', 'VALUE6_STB_CODE1', 'VALUE6_STB_CODESYSTEM1', 'VALUE6_ZIP_CODESYSTEM1', 'VALUE6_ZIP_CODE1', NULL);
INSERT INTO ad_datatype (id, value1_al_value, value2_dal_value, value2_dal_code, value3_al_value, value3_al_code, value3_al_codesystem, value4_al1_value, value4_al2_value, value4_al1_code, value4_al2_code, value4_al1_codesystem, value4_al2_codesystem, value5_al_value, value5_al_code, value5_al_codesystem, value5_dal_code, value5_dal_value, value5_dal_codesystem, value5_cty_value, value5_cty_code, value5_cty_codesystem, value6_adl_value, value6_al_value, value6_bnn_value, value6_bnr_value, value6_bns_value, value6_car_value, value6_cen_value, value6_cnt_value, value6_cpa_value, value6_cty_value, value6_dal_value, value6_del_value, value6_dinsta_value, value6_dinstq_value, value6_dir_value, value6_dmod_value, value6_dmodid_value, value6_int_value, value6_pob_value, value6_pre_value, value6_sal_value, value6_sta_value, value6_stb_value, value6_str_value, value6_sttyp_value, value6_unid_value, value6_unit_value, value6_zip_value, value6_adl_code, value6_bns_code, value6_bns_codesystem, value6_dal_code, value6_dal_codesystem, value6_int_code, value6_int_codesystem, value6_stb_code, value6_stb_codesystem, value6_zip_codesystem, value6_zip_code, ad_datatype_value8_id) VALUES (30, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'VALUE6_ADL_VALUE2', 'VALUE6_AL_VALUE2', 'VALUE6_BNN_VALUE2', 'VALUE6_BNR_VALUE2', 'VALUE6_BNS_VALUE2', 'VALUE6_CAR_VALUE2', 'VALUE6_CEN_VALUE2', 'VALUE6_CNT_VALUE2', 'VALUE6_CPA_VALUE2', 'VALUE6_CTY_VALUE2', 'VALUE6_DAL_VALUE2', 'VALUE6_DEL_VALUE2', 'VALUE6_DINSTA_VALUE2', 'VALUE6_DINSTQ_VALUE2', 'VALUE6_DIR_VALUE2', 'VALUE6_DMOD_VALUE2', 'VALUE6_DMODID_VALUE2', 'VALUE6_INT_VALUE2', 'VALUE6_POB_VALUE2', 'VALUE6_PRE_VALUE2', 'VALUE6_SAL_VALUE2', 'VALUE6_STA_VALUE2', 'VALUE6_STB_VALUE2', 'VALUE6_STR_VALUE2', 'VALUE6_STTYP_VALUE2', 'VALUE6_UNID_VALUE2', 'VALUE6_UNIT_VALUE2', 'VALUE6_ZIP_VALUE2', 'VALUE6_ADL_CODE2', 'VALUE6_BNS_CODE2', 'VALUE6_BNS_CODESYSTEM2', 'VALUE6_DAL_CODE2', 'VALUE6_DAL_CODESYSTEM2', 'VALUE6_INT_CODE2', 'VALUE6_INT_CODESYSTEM2', 'VALUE6_STB_CODE2', 'VALUE6_STB_CODESYSTEM2', 'VALUE6_ZIP_CODESYSTEM2', 'VALUE6_ZIP_CODE2', NULL);
INSERT INTO ad_datatype (id, value1_al_value, value2_dal_value, value2_dal_code, value3_al_value, value3_al_code, value3_al_codesystem, value4_al1_value, value4_al2_value, value4_al1_code, value4_al2_code, value4_al1_codesystem, value4_al2_codesystem, value5_al_value, value5_al_code, value5_al_codesystem, value5_dal_code, value5_dal_value, value5_dal_codesystem, value5_cty_value, value5_cty_code, value5_cty_codesystem, value6_adl_value, value6_al_value, value6_bnn_value, value6_bnr_value, value6_bns_value, value6_car_value, value6_cen_value, value6_cnt_value, value6_cpa_value, value6_cty_value, value6_dal_value, value6_del_value, value6_dinsta_value, value6_dinstq_value, value6_dir_value, value6_dmod_value, value6_dmodid_value, value6_int_value, value6_pob_value, value6_pre_value, value6_sal_value, value6_sta_value, value6_stb_value, value6_str_value, value6_sttyp_value, value6_unid_value, value6_unit_value, value6_zip_value, value6_adl_code, value6_bns_code, value6_bns_codesystem, value6_dal_code, value6_dal_codesystem, value6_int_code, value6_int_codesystem, value6_stb_code, value6_stb_codesystem, value6_zip_codesystem, value6_zip_code, ad_datatype_value8_id) VALUES (31, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'VALUE6_ADL_VALUE3', 'VALUE6_AL_VALUE3', 'VALUE6_BNN_VALUE3', 'VALUE6_BNR_VALUE3', 'VALUE6_BNS_VALUE3', 'VALUE6_CAR_VALUE3', 'VALUE6_CEN_VALUE3', 'VALUE6_CNT_VALUE3', 'VALUE6_CPA_VALUE3', 'VALUE6_CTY_VALUE3', 'VALUE6_DAL_VALUE3', 'VALUE6_DEL_VALUE3', 'VALUE6_DINSTA_VALUE3', 'VALUE6_DINSTQ_VALUE3', 'VALUE6_DIR_VALUE3', 'VALUE6_DMOD_VALUE3', 'VALUE6_DMODID_VALUE3', 'VALUE6_INT_VALUE3', 'VALUE6_POB_VALUE3', 'VALUE6_PRE_VALUE3', 'VALUE6_SAL_VALUE3', 'VALUE6_STA_VALUE3', 'VALUE6_STB_VALUE3', 'VALUE6_STR_VALUE3', 'VALUE6_STTYP_VALUE3', 'VALUE6_UNID_VALUE3', 'VALUE6_UNIT_VALUE3', 'VALUE6_ZIP_VALUE3', 'VALUE6_ADL_CODE3', 'VALUE6_BNS_CODE3', 'VALUE6_BNS_CODESYSTEM3', 'VALUE6_DAL_CODE3', 'VALUE6_DAL_CODESYSTEM3', 'VALUE6_INT_CODE3', 'VALUE6_INT_CODESYSTEM3', 'VALUE6_STB_CODE3', 'VALUE6_STB_CODESYSTEM3', 'VALUE6_ZIP_CODESYSTEM3', 'VALUE6_ZIP_CODE3', NULL);
INSERT INTO ad_datatype (id, value1_al_value, value2_dal_value, value2_dal_code, value3_al_value, value3_al_code, value3_al_codesystem, value4_al1_value, value4_al2_value, value4_al1_code, value4_al2_code, value4_al1_codesystem, value4_al2_codesystem, value5_al_value, value5_al_code, value5_al_codesystem, value5_dal_code, value5_dal_value, value5_dal_codesystem, value5_cty_value, value5_cty_code, value5_cty_codesystem, value6_adl_value, value6_al_value, value6_bnn_value, value6_bnr_value, value6_bns_value, value6_car_value, value6_cen_value, value6_cnt_value, value6_cpa_value, value6_cty_value, value6_dal_value, value6_del_value, value6_dinsta_value, value6_dinstq_value, value6_dir_value, value6_dmod_value, value6_dmodid_value, value6_int_value, value6_pob_value, value6_pre_value, value6_sal_value, value6_sta_value, value6_stb_value, value6_str_value, value6_sttyp_value, value6_unid_value, value6_unit_value, value6_zip_value, value6_adl_code, value6_bns_code, value6_bns_codesystem, value6_dal_code, value6_dal_codesystem, value6_int_code, value6_int_codesystem, value6_stb_code, value6_stb_codesystem, value6_zip_codesystem, value6_zip_code, ad_datatype_value8_id) VALUES (32, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'VALUE6_ADL_VALUE4', 'VALUE6_AL_VALUE4', 'VALUE6_BNN_VALUE4', 'VALUE6_BNR_VALUE4', 'VALUE6_BNS_VALUE4', 'VALUE6_CAR_VALUE4', 'VALUE6_CEN_VALUE4', 'VALUE6_CNT_VALUE4', 'VALUE6_CPA_VALUE4', 'VALUE6_CTY_VALUE4', 'VALUE6_DAL_VALUE4', 'VALUE6_DEL_VALUE4', 'VALUE6_DINSTA_VALUE4', 'VALUE6_DINSTQ_VALUE4', 'VALUE6_DIR_VALUE4', 'VALUE6_DMOD_VALUE4', 'VALUE6_DMODID_VALUE4', 'VALUE6_INT_VALUE4', 'VALUE6_POB_VALUE4', 'VALUE6_PRE_VALUE4', 'VALUE6_SAL_VALUE4', 'VALUE6_STA_VALUE4', 'VALUE6_STB_VALUE4', 'VALUE6_STR_VALUE4', 'VALUE6_STTYP_VALUE4', 'VALUE6_UNID_VALUE4', 'VALUE6_UNIT_VALUE4', 'VALUE6_ZIP_VALUE4', 'VALUE6_ADL_CODE4', 'VALUE6_BNS_CODE4', 'VALUE6_BNS_CODESYSTEM4', 'VALUE6_DAL_CODE4', 'VALUE6_DAL_CODESYSTEM4', 'VALUE6_INT_CODE4', 'VALUE6_INT_CODESYSTEM4', 'VALUE6_STB_CODE4', 'VALUE6_STB_CODESYSTEM4', 'VALUE6_ZIP_CODESYSTEM4', 'VALUE6_ZIP_CODE4', NULL);
INSERT INTO ad_datatype (id, value1_al_value, value2_dal_value, value2_dal_code, value3_al_value, value3_al_code, value3_al_codesystem, value4_al1_value, value4_al2_value, value4_al1_code, value4_al2_code, value4_al1_codesystem, value4_al2_codesystem, value5_al_value, value5_al_code, value5_al_codesystem, value5_dal_code, value5_dal_value, value5_dal_codesystem, value5_cty_value, value5_cty_code, value5_cty_codesystem, value6_adl_value, value6_al_value, value6_bnn_value, value6_bnr_value, value6_bns_value, value6_car_value, value6_cen_value, value6_cnt_value, value6_cpa_value, value6_cty_value, value6_dal_value, value6_del_value, value6_dinsta_value, value6_dinstq_value, value6_dir_value, value6_dmod_value, value6_dmodid_value, value6_int_value, value6_pob_value, value6_pre_value, value6_sal_value, value6_sta_value, value6_stb_value, value6_str_value, value6_sttyp_value, value6_unid_value, value6_unit_value, value6_zip_value, value6_adl_code, value6_bns_code, value6_bns_codesystem, value6_dal_code, value6_dal_codesystem, value6_int_code, value6_int_codesystem, value6_stb_code, value6_stb_codesystem, value6_zip_codesystem, value6_zip_code, ad_datatype_value8_id) VALUES (33, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'VALUE6_ADL_VALUE5', 'VALUE6_AL_VALUE5', 'VALUE6_BNN_VALUE5', 'VALUE6_BNR_VALUE5', 'VALUE6_BNS_VALUE5', 'VALUE6_CAR_VALUE5', 'VALUE6_CEN_VALUE5', 'VALUE6_CNT_VALUE5', 'VALUE6_CPA_VALUE5', 'VALUE6_CTY_VALUE5', 'VALUE6_DAL_VALUE5', 'VALUE6_DEL_VALUE5', 'VALUE6_DINSTA_VALUE5', 'VALUE6_DINSTQ_VALUE5', 'VALUE6_DIR_VALUE5', 'VALUE6_DMOD_VALUE5', 'VALUE6_DMODID_VALUE5', 'VALUE6_INT_VALUE5', 'VALUE6_POB_VALUE5', 'VALUE6_PRE_VALUE5', 'VALUE6_SAL_VALUE5', 'VALUE6_STA_VALUE5', 'VALUE6_STB_VALUE5', 'VALUE6_STR_VALUE5', 'VALUE6_STTYP_VALUE5', 'VALUE6_UNID_VALUE5', 'VALUE6_UNIT_VALUE5', 'VALUE6_ZIP_VALUE5', 'VALUE6_ADL_CODE5', 'VALUE6_BNS_CODE5', 'VALUE6_BNS_CODESYSTEM5', 'VALUE6_DAL_CODE5', 'VALUE6_DAL_CODESYSTEM5', 'VALUE6_INT_CODE5', 'VALUE6_INT_CODESYSTEM5', 'VALUE6_STB_CODE5', 'VALUE6_STB_CODESYSTEM5', 'VALUE6_ZIP_CODESYSTEM5', 'VALUE6_ZIP_CODE5', NULL);
INSERT INTO ad_datatype (id, value1_al_value, value2_dal_value, value2_dal_code, value3_al_value, value3_al_code, value3_al_codesystem, value4_al1_value, value4_al2_value, value4_al1_code, value4_al2_code, value4_al1_codesystem, value4_al2_codesystem, value5_al_value, value5_al_code, value5_al_codesystem, value5_dal_code, value5_dal_value, value5_dal_codesystem, value5_cty_value, value5_cty_code, value5_cty_codesystem, value6_adl_value, value6_al_value, value6_bnn_value, value6_bnr_value, value6_bns_value, value6_car_value, value6_cen_value, value6_cnt_value, value6_cpa_value, value6_cty_value, value6_dal_value, value6_del_value, value6_dinsta_value, value6_dinstq_value, value6_dir_value, value6_dmod_value, value6_dmodid_value, value6_int_value, value6_pob_value, value6_pre_value, value6_sal_value, value6_sta_value, value6_stb_value, value6_str_value, value6_sttyp_value, value6_unid_value, value6_unit_value, value6_zip_value, value6_adl_code, value6_bns_code, value6_bns_codesystem, value6_dal_code, value6_dal_codesystem, value6_int_code, value6_int_codesystem, value6_stb_code, value6_stb_codesystem, value6_zip_codesystem, value6_zip_code, ad_datatype_value8_id) VALUES (34, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ad_datatype (id, value1_al_value, value2_dal_value, value2_dal_code, value3_al_value, value3_al_code, value3_al_codesystem, value4_al1_value, value4_al2_value, value4_al1_code, value4_al2_code, value4_al1_codesystem, value4_al2_codesystem, value5_al_value, value5_al_code, value5_al_codesystem, value5_dal_code, value5_dal_value, value5_dal_codesystem, value5_cty_value, value5_cty_code, value5_cty_codesystem, value6_adl_value, value6_al_value, value6_bnn_value, value6_bnr_value, value6_bns_value, value6_car_value, value6_cen_value, value6_cnt_value, value6_cpa_value, value6_cty_value, value6_dal_value, value6_del_value, value6_dinsta_value, value6_dinstq_value, value6_dir_value, value6_dmod_value, value6_dmodid_value, value6_int_value, value6_pob_value, value6_pre_value, value6_sal_value, value6_sta_value, value6_stb_value, value6_str_value, value6_sttyp_value, value6_unid_value, value6_unit_value, value6_zip_value, value6_adl_code, value6_bns_code, value6_bns_codesystem, value6_dal_code, value6_dal_codesystem, value6_int_code, value6_int_codesystem, value6_stb_code, value6_stb_codesystem, value6_zip_codesystem, value6_zip_code, ad_datatype_value8_id) VALUES (35, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ad_datatype (id, value1_al_value, value2_dal_value, value2_dal_code, value3_al_value, value3_al_code, value3_al_codesystem, value4_al1_value, value4_al2_value, value4_al1_code, value4_al2_code, value4_al1_codesystem, value4_al2_codesystem, value5_al_value, value5_al_code, value5_al_codesystem, value5_dal_code, value5_dal_value, value5_dal_codesystem, value5_cty_value, value5_cty_code, value5_cty_codesystem, value6_adl_value, value6_al_value, value6_bnn_value, value6_bnr_value, value6_bns_value, value6_car_value, value6_cen_value, value6_cnt_value, value6_cpa_value, value6_cty_value, value6_dal_value, value6_del_value, value6_dinsta_value, value6_dinstq_value, value6_dir_value, value6_dmod_value, value6_dmodid_value, value6_int_value, value6_pob_value, value6_pre_value, value6_sal_value, value6_sta_value, value6_stb_value, value6_str_value, value6_sttyp_value, value6_unid_value, value6_unit_value, value6_zip_value, value6_adl_code, value6_bns_code, value6_bns_codesystem, value6_dal_code, value6_dal_codesystem, value6_int_code, value6_int_codesystem, value6_stb_code, value6_stb_codesystem, value6_zip_codesystem, value6_zip_code, ad_datatype_value8_id) VALUES (36, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ad_datatype (id, value1_al_value, value2_dal_value, value2_dal_code, value3_al_value, value3_al_code, value3_al_codesystem, value4_al1_value, value4_al2_value, value4_al1_code, value4_al2_code, value4_al1_codesystem, value4_al2_codesystem, value5_al_value, value5_al_code, value5_al_codesystem, value5_dal_code, value5_dal_value, value5_dal_codesystem, value5_cty_value, value5_cty_code, value5_cty_codesystem, value6_adl_value, value6_al_value, value6_bnn_value, value6_bnr_value, value6_bns_value, value6_car_value, value6_cen_value, value6_cnt_value, value6_cpa_value, value6_cty_value, value6_dal_value, value6_del_value, value6_dinsta_value, value6_dinstq_value, value6_dir_value, value6_dmod_value, value6_dmodid_value, value6_int_value, value6_pob_value, value6_pre_value, value6_sal_value, value6_sta_value, value6_stb_value, value6_str_value, value6_sttyp_value, value6_unid_value, value6_unit_value, value6_zip_value, value6_adl_code, value6_bns_code, value6_bns_codesystem, value6_dal_code, value6_dal_codesystem, value6_int_code, value6_int_codesystem, value6_stb_code, value6_stb_codesystem, value6_zip_codesystem, value6_zip_code, ad_datatype_value8_id) VALUES (37, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ad_datatype (id, value1_al_value, value2_dal_value, value2_dal_code, value3_al_value, value3_al_code, value3_al_codesystem, value4_al1_value, value4_al2_value, value4_al1_code, value4_al2_code, value4_al1_codesystem, value4_al2_codesystem, value5_al_value, value5_al_code, value5_al_codesystem, value5_dal_code, value5_dal_value, value5_dal_codesystem, value5_cty_value, value5_cty_code, value5_cty_codesystem, value6_adl_value, value6_al_value, value6_bnn_value, value6_bnr_value, value6_bns_value, value6_car_value, value6_cen_value, value6_cnt_value, value6_cpa_value, value6_cty_value, value6_dal_value, value6_del_value, value6_dinsta_value, value6_dinstq_value, value6_dir_value, value6_dmod_value, value6_dmodid_value, value6_int_value, value6_pob_value, value6_pre_value, value6_sal_value, value6_sta_value, value6_stb_value, value6_str_value, value6_sttyp_value, value6_unid_value, value6_unit_value, value6_zip_value, value6_adl_code, value6_bns_code, value6_bns_codesystem, value6_dal_code, value6_dal_codesystem, value6_int_code, value6_int_codesystem, value6_stb_code, value6_stb_codesystem, value6_zip_codesystem, value6_zip_code, ad_datatype_value8_id) VALUES (38, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ad_datatype (id, value1_al_value, value2_dal_value, value2_dal_code, value3_al_value, value3_al_code, value3_al_codesystem, value4_al1_value, value4_al2_value, value4_al1_code, value4_al2_code, value4_al1_codesystem, value4_al2_codesystem, value5_al_value, value5_al_code, value5_al_codesystem, value5_dal_code, value5_dal_value, value5_dal_codesystem, value5_cty_value, value5_cty_code, value5_cty_codesystem, value6_adl_value, value6_al_value, value6_bnn_value, value6_bnr_value, value6_bns_value, value6_car_value, value6_cen_value, value6_cnt_value, value6_cpa_value, value6_cty_value, value6_dal_value, value6_del_value, value6_dinsta_value, value6_dinstq_value, value6_dir_value, value6_dmod_value, value6_dmodid_value, value6_int_value, value6_pob_value, value6_pre_value, value6_sal_value, value6_sta_value, value6_stb_value, value6_str_value, value6_sttyp_value, value6_unid_value, value6_unit_value, value6_zip_value, value6_adl_code, value6_bns_code, value6_bns_codesystem, value6_dal_code, value6_dal_codesystem, value6_int_code, value6_int_codesystem, value6_stb_code, value6_stb_codesystem, value6_zip_codesystem, value6_zip_code, ad_datatype_value8_id) VALUES (39, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1);
INSERT INTO ad_datatype (id, value1_al_value, value2_dal_value, value2_dal_code, value3_al_value, value3_al_code, value3_al_codesystem, value4_al1_value, value4_al2_value, value4_al1_code, value4_al2_code, value4_al1_codesystem, value4_al2_codesystem, value5_al_value, value5_al_code, value5_al_codesystem, value5_dal_code, value5_dal_value, value5_dal_codesystem, value5_cty_value, value5_cty_code, value5_cty_codesystem, value6_adl_value, value6_al_value, value6_bnn_value, value6_bnr_value, value6_bns_value, value6_car_value, value6_cen_value, value6_cnt_value, value6_cpa_value, value6_cty_value, value6_dal_value, value6_del_value, value6_dinsta_value, value6_dinstq_value, value6_dir_value, value6_dmod_value, value6_dmodid_value, value6_int_value, value6_pob_value, value6_pre_value, value6_sal_value, value6_sta_value, value6_stb_value, value6_str_value, value6_sttyp_value, value6_unid_value, value6_unit_value, value6_zip_value, value6_adl_code, value6_bns_code, value6_bns_codesystem, value6_dal_code, value6_dal_codesystem, value6_int_code, value6_int_codesystem, value6_stb_code, value6_stb_codesystem, value6_zip_codesystem, value6_zip_code, ad_datatype_value8_id) VALUES (40, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 2);
INSERT INTO ad_datatype (id, value1_al_value, value2_dal_value, value2_dal_code, value3_al_value, value3_al_code, value3_al_codesystem, value4_al1_value, value4_al2_value, value4_al1_code, value4_al2_code, value4_al1_codesystem, value4_al2_codesystem, value5_al_value, value5_al_code, value5_al_codesystem, value5_dal_code, value5_dal_value, value5_dal_codesystem, value5_cty_value, value5_cty_code, value5_cty_codesystem, value6_adl_value, value6_al_value, value6_bnn_value, value6_bnr_value, value6_bns_value, value6_car_value, value6_cen_value, value6_cnt_value, value6_cpa_value, value6_cty_value, value6_dal_value, value6_del_value, value6_dinsta_value, value6_dinstq_value, value6_dir_value, value6_dmod_value, value6_dmodid_value, value6_int_value, value6_pob_value, value6_pre_value, value6_sal_value, value6_sta_value, value6_stb_value, value6_str_value, value6_sttyp_value, value6_unid_value, value6_unit_value, value6_zip_value, value6_adl_code, value6_bns_code, value6_bns_codesystem, value6_dal_code, value6_dal_codesystem, value6_int_code, value6_int_codesystem, value6_stb_code, value6_stb_codesystem, value6_zip_codesystem, value6_zip_code, ad_datatype_value8_id) VALUES (41, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 3);
INSERT INTO ad_datatype (id, value1_al_value, value2_dal_value, value2_dal_code, value3_al_value, value3_al_code, value3_al_codesystem, value4_al1_value, value4_al2_value, value4_al1_code, value4_al2_code, value4_al1_codesystem, value4_al2_codesystem, value5_al_value, value5_al_code, value5_al_codesystem, value5_dal_code, value5_dal_value, value5_dal_codesystem, value5_cty_value, value5_cty_code, value5_cty_codesystem, value6_adl_value, value6_al_value, value6_bnn_value, value6_bnr_value, value6_bns_value, value6_car_value, value6_cen_value, value6_cnt_value, value6_cpa_value, value6_cty_value, value6_dal_value, value6_del_value, value6_dinsta_value, value6_dinstq_value, value6_dir_value, value6_dmod_value, value6_dmodid_value, value6_int_value, value6_pob_value, value6_pre_value, value6_sal_value, value6_sta_value, value6_stb_value, value6_str_value, value6_sttyp_value, value6_unid_value, value6_unit_value, value6_zip_value, value6_adl_code, value6_bns_code, value6_bns_codesystem, value6_dal_code, value6_dal_codesystem, value6_int_code, value6_int_codesystem, value6_stb_code, value6_stb_codesystem, value6_zip_codesystem, value6_zip_code, ad_datatype_value8_id) VALUES (42, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 4);
INSERT INTO ad_datatype (id, value1_al_value, value2_dal_value, value2_dal_code, value3_al_value, value3_al_code, value3_al_codesystem, value4_al1_value, value4_al2_value, value4_al1_code, value4_al2_code, value4_al1_codesystem, value4_al2_codesystem, value5_al_value, value5_al_code, value5_al_codesystem, value5_dal_code, value5_dal_value, value5_dal_codesystem, value5_cty_value, value5_cty_code, value5_cty_codesystem, value6_adl_value, value6_al_value, value6_bnn_value, value6_bnr_value, value6_bns_value, value6_car_value, value6_cen_value, value6_cnt_value, value6_cpa_value, value6_cty_value, value6_dal_value, value6_del_value, value6_dinsta_value, value6_dinstq_value, value6_dir_value, value6_dmod_value, value6_dmodid_value, value6_int_value, value6_pob_value, value6_pre_value, value6_sal_value, value6_sta_value, value6_stb_value, value6_str_value, value6_sttyp_value, value6_unid_value, value6_unit_value, value6_zip_value, value6_adl_code, value6_bns_code, value6_bns_codesystem, value6_dal_code, value6_dal_codesystem, value6_int_code, value6_int_codesystem, value6_stb_code, value6_stb_codesystem, value6_zip_codesystem, value6_zip_code, ad_datatype_value8_id) VALUES (43, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 5);
INSERT INTO ad_datatype (id, value1_al_value, value2_dal_value, value2_dal_code, value3_al_value, value3_al_code, value3_al_codesystem, value4_al1_value, value4_al2_value, value4_al1_code, value4_al2_code, value4_al1_codesystem, value4_al2_codesystem, value5_al_value, value5_al_code, value5_al_codesystem, value5_dal_code, value5_dal_value, value5_dal_codesystem, value5_cty_value, value5_cty_code, value5_cty_codesystem, value6_adl_value, value6_al_value, value6_bnn_value, value6_bnr_value, value6_bns_value, value6_car_value, value6_cen_value, value6_cnt_value, value6_cpa_value, value6_cty_value, value6_dal_value, value6_del_value, value6_dinsta_value, value6_dinstq_value, value6_dir_value, value6_dmod_value, value6_dmodid_value, value6_int_value, value6_pob_value, value6_pre_value, value6_sal_value, value6_sta_value, value6_stb_value, value6_str_value, value6_sttyp_value, value6_unid_value, value6_unit_value, value6_zip_value, value6_adl_code, value6_bns_code, value6_bns_codesystem, value6_dal_code, value6_dal_codesystem, value6_int_code, value6_int_codesystem, value6_stb_code, value6_stb_codesystem, value6_zip_codesystem, value6_zip_code, ad_datatype_value8_id) VALUES (44, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ad_datatype (id, value1_al_value, value2_dal_value, value2_dal_code, value3_al_value, value3_al_code, value3_al_codesystem, value4_al1_value, value4_al2_value, value4_al1_code, value4_al2_code, value4_al1_codesystem, value4_al2_codesystem, value5_al_value, value5_al_code, value5_al_codesystem, value5_dal_code, value5_dal_value, value5_dal_codesystem, value5_cty_value, value5_cty_code, value5_cty_codesystem, value6_adl_value, value6_al_value, value6_bnn_value, value6_bnr_value, value6_bns_value, value6_car_value, value6_cen_value, value6_cnt_value, value6_cpa_value, value6_cty_value, value6_dal_value, value6_del_value, value6_dinsta_value, value6_dinstq_value, value6_dir_value, value6_dmod_value, value6_dmodid_value, value6_int_value, value6_pob_value, value6_pre_value, value6_sal_value, value6_sta_value, value6_stb_value, value6_str_value, value6_sttyp_value, value6_unid_value, value6_unit_value, value6_zip_value, value6_adl_code, value6_bns_code, value6_bns_codesystem, value6_dal_code, value6_dal_codesystem, value6_int_code, value6_int_codesystem, value6_stb_code, value6_stb_codesystem, value6_zip_codesystem, value6_zip_code, ad_datatype_value8_id) VALUES (45, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ad_datatype (id, value1_al_value, value2_dal_value, value2_dal_code, value3_al_value, value3_al_code, value3_al_codesystem, value4_al1_value, value4_al2_value, value4_al1_code, value4_al2_code, value4_al1_codesystem, value4_al2_codesystem, value5_al_value, value5_al_code, value5_al_codesystem, value5_dal_code, value5_dal_value, value5_dal_codesystem, value5_cty_value, value5_cty_code, value5_cty_codesystem, value6_adl_value, value6_al_value, value6_bnn_value, value6_bnr_value, value6_bns_value, value6_car_value, value6_cen_value, value6_cnt_value, value6_cpa_value, value6_cty_value, value6_dal_value, value6_del_value, value6_dinsta_value, value6_dinstq_value, value6_dir_value, value6_dmod_value, value6_dmodid_value, value6_int_value, value6_pob_value, value6_pre_value, value6_sal_value, value6_sta_value, value6_stb_value, value6_str_value, value6_sttyp_value, value6_unid_value, value6_unit_value, value6_zip_value, value6_adl_code, value6_bns_code, value6_bns_codesystem, value6_dal_code, value6_dal_codesystem, value6_int_code, value6_int_codesystem, value6_stb_code, value6_stb_codesystem, value6_zip_codesystem, value6_zip_code, ad_datatype_value8_id) VALUES (46, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ad_datatype (id, value1_al_value, value2_dal_value, value2_dal_code, value3_al_value, value3_al_code, value3_al_codesystem, value4_al1_value, value4_al2_value, value4_al1_code, value4_al2_code, value4_al1_codesystem, value4_al2_codesystem, value5_al_value, value5_al_code, value5_al_codesystem, value5_dal_code, value5_dal_value, value5_dal_codesystem, value5_cty_value, value5_cty_code, value5_cty_codesystem, value6_adl_value, value6_al_value, value6_bnn_value, value6_bnr_value, value6_bns_value, value6_car_value, value6_cen_value, value6_cnt_value, value6_cpa_value, value6_cty_value, value6_dal_value, value6_del_value, value6_dinsta_value, value6_dinstq_value, value6_dir_value, value6_dmod_value, value6_dmodid_value, value6_int_value, value6_pob_value, value6_pre_value, value6_sal_value, value6_sta_value, value6_stb_value, value6_str_value, value6_sttyp_value, value6_unid_value, value6_unit_value, value6_zip_value, value6_adl_code, value6_bns_code, value6_bns_codesystem, value6_dal_code, value6_dal_codesystem, value6_int_code, value6_int_codesystem, value6_stb_code, value6_stb_codesystem, value6_zip_codesystem, value6_zip_code, ad_datatype_value8_id) VALUES (47, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ad_datatype (id, value1_al_value, value2_dal_value, value2_dal_code, value3_al_value, value3_al_code, value3_al_codesystem, value4_al1_value, value4_al2_value, value4_al1_code, value4_al2_code, value4_al1_codesystem, value4_al2_codesystem, value5_al_value, value5_al_code, value5_al_codesystem, value5_dal_code, value5_dal_value, value5_dal_codesystem, value5_cty_value, value5_cty_code, value5_cty_codesystem, value6_adl_value, value6_al_value, value6_bnn_value, value6_bnr_value, value6_bns_value, value6_car_value, value6_cen_value, value6_cnt_value, value6_cpa_value, value6_cty_value, value6_dal_value, value6_del_value, value6_dinsta_value, value6_dinstq_value, value6_dir_value, value6_dmod_value, value6_dmodid_value, value6_int_value, value6_pob_value, value6_pre_value, value6_sal_value, value6_sta_value, value6_stb_value, value6_str_value, value6_sttyp_value, value6_unid_value, value6_unit_value, value6_zip_value, value6_adl_code, value6_bns_code, value6_bns_codesystem, value6_dal_code, value6_dal_codesystem, value6_int_code, value6_int_codesystem, value6_stb_code, value6_stb_codesystem, value6_zip_codesystem, value6_zip_code, ad_datatype_value8_id) VALUES (48, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);


--
-- Data for Name: ad_datatype_value7; Type: TABLE DATA; 
--

INSERT INTO ad_datatype_value7 (ad_datatype_id, al_value, al_code, al_codesystem, dal_code, dal_value, dal_codesystem, cty_value, cty_code, cty_codesystem) VALUES (34, 'AL_VALUE1', 'AL_CODE1', 'AL_CODESYSTEM1', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ad_datatype_value7 (ad_datatype_id, al_value, al_code, al_codesystem, dal_code, dal_value, dal_codesystem, cty_value, cty_code, cty_codesystem) VALUES (35, NULL, NULL, NULL, 'DAL_CODE1', 'DAL_VALUE1', 'DAL_CODESYSTEM1', 'CTY_VALUE1', 'CTY_CODE1', 'CTY_CODESYSTEM1');
INSERT INTO ad_datatype_value7 (ad_datatype_id, al_value, al_code, al_codesystem, dal_code, dal_value, dal_codesystem, cty_value, cty_code, cty_codesystem) VALUES (36, 'AL_VALUE2', 'AL_CODE2', 'AL_CODESYSTEM2', 'DAL_CODE2', 'DAL_VALUE2', 'DAL_CODESYSTEM2', NULL, NULL, NULL);
INSERT INTO ad_datatype_value7 (ad_datatype_id, al_value, al_code, al_codesystem, dal_code, dal_value, dal_codesystem, cty_value, cty_code, cty_codesystem) VALUES (37, 'AL_VALUE3', 'AL_CODE3', 'AL_CODESYSTEM3', 'DAL_CODE3', 'DAL_VALUE3', 'DAL_CODESYSTEM3', 'CTY_VALUE3', 'CTY_CODE3', 'CTY_CODESYSTEM3');
INSERT INTO ad_datatype_value7 (ad_datatype_id, al_value, al_code, al_codesystem, dal_code, dal_value, dal_codesystem, cty_value, cty_code, cty_codesystem) VALUES (38, 'AL_VALUE4', 'AL_CODE4', 'AL_CODESYSTEM4', 'DAL_CODE4', 'DAL_VALUE4', 'DAL_CODESYSTEM4', 'CTY_VALUE4', 'CTY_CODE4', 'CTY_CODESYSTEM4');


--
-- Data for Name: ad_datatype_value8; Type: TABLE DATA; 
--

INSERT INTO ad_datatype_value8 (id, al_value, al_code, al_codesystem, dal_code, dal_value, dal_codesystem, cty_value, cty_code, cty_codesystem) VALUES (1, 'AL_VALUE1', 'AL_CODE1', 'AL_CODESYSTEM1', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ad_datatype_value8 (id, al_value, al_code, al_codesystem, dal_code, dal_value, dal_codesystem, cty_value, cty_code, cty_codesystem) VALUES (2, NULL, NULL, NULL, 'DAL_CODE1', 'DAL_VALUE1', 'DAL_CODESYSTEM1', 'CTY_VALUE1', 'CTY_CODE1', 'CTY_CODESYSTEM1');
INSERT INTO ad_datatype_value8 (id, al_value, al_code, al_codesystem, dal_code, dal_value, dal_codesystem, cty_value, cty_code, cty_codesystem) VALUES (3, 'AL_VALUE2', 'AL_CODE2', 'AL_CODESYSTEM2', NULL, NULL, NULL, 'CTY_VALUE2', 'CTY_CODE2', 'CTY_CODESYSTEM2');
INSERT INTO ad_datatype_value8 (id, al_value, al_code, al_codesystem, dal_code, dal_value, dal_codesystem, cty_value, cty_code, cty_codesystem) VALUES (4, 'AL_VALUE3', 'AL_CODE3', 'AL_CODESYSTEM3', 'DAL_CODE3', 'DAL_VALUE3', 'DAL_CODESYSTEM3', NULL, NULL, NULL);
INSERT INTO ad_datatype_value8 (id, al_value, al_code, al_codesystem, dal_code, dal_value, dal_codesystem, cty_value, cty_code, cty_codesystem) VALUES (5, 'AL_VALUE4', 'AL_CODE4', 'AL_CODESYSTEM4', 'DAL_CODE4', 'DAL_VALUE4', 'DAL_CODESYSTEM4', 'CTY_VALUE4', 'CTY_CODE4', 'CTY_CODESYSTEM4');


--
-- Data for Name: ad_datatype_value9; Type: TABLE DATA; 
--

INSERT INTO ad_datatype_value9 (id, al_value, al_code, al_codesystem, dal_code, dal_value, dal_codesystem, cty_value, cty_code, cty_codesystem) VALUES (1, 'AL_VALUE1', 'AL_CODE1', 'AL_CODESYSTEM1', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ad_datatype_value9 (id, al_value, al_code, al_codesystem, dal_code, dal_value, dal_codesystem, cty_value, cty_code, cty_codesystem) VALUES (2, 'AL_VALUE4', 'AL_CODE4', 'AL_CODESYSTEM4', 'DAL_CODE4', 'DAL_VALUE4', 'DAL_CODESYSTEM4', 'CTY_VALUE4', 'CTY_CODE4', 'CTY_CODESYSTEM4');


--
-- Data for Name: bl_datatype; Type: TABLE DATA; 
--

INSERT INTO bl_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (1, NULL, NULL, NULL);
INSERT INTO bl_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (2, '1', NULL, NULL);
INSERT INTO bl_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (3, NULL, NULL, '1');
INSERT INTO bl_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (4, NULL, 'INV', '0');


--
-- Data for Name: bl_nonnull_datatype; Type: TABLE DATA; 
--

INSERT INTO bl_nonnull_datatype (id, value1_value) VALUES (1, '0');
INSERT INTO bl_nonnull_datatype (id, value1_value) VALUES (2, '1');


--
-- Data for Name: cd_cd_datatype_value8; Type: TABLE DATA; 
--

INSERT INTO cd_cd_datatype_value8 (cd_data_type_id, cd_datatype_value8_id) VALUES (40, 1);
INSERT INTO cd_cd_datatype_value8 (cd_data_type_id, cd_datatype_value8_id) VALUES (41, 1);
INSERT INTO cd_cd_datatype_value8 (cd_data_type_id, cd_datatype_value8_id) VALUES (42, 1);
INSERT INTO cd_cd_datatype_value8 (cd_data_type_id, cd_datatype_value8_id) VALUES (43, 1);
INSERT INTO cd_cd_datatype_value8 (cd_data_type_id, cd_datatype_value8_id) VALUES (44, 1);


--
-- Data for Name: cd_datatype; Type: TABLE DATA; 
--

INSERT INTO cd_datatype (id, value1_code, value2_null_flavor, value2_code, value3_code, value4_null_flavor, value4_code, value4_code_system, value4_code_system_version, value4_code_system_name, value4_display_null_flavor, value4_display_value, value4_orig_txt_null_flavor, value4_orig_txt_value, value4_orig_txt_description, value5_null_flavor, value5_code, value5_code_system, value5_code_system_name, value5_code_system_version, value5_display_null_flavor, value5_display_value, value5_orig_txt_null_flavor, value5_orig_txt_value, value5_orig_txt_description, cd_datatype_value7_id) VALUES (1, 'CODE1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO cd_datatype (id, value1_code, value2_null_flavor, value2_code, value3_code, value4_null_flavor, value4_code, value4_code_system, value4_code_system_version, value4_code_system_name, value4_display_null_flavor, value4_display_value, value4_orig_txt_null_flavor, value4_orig_txt_value, value4_orig_txt_description, value5_null_flavor, value5_code, value5_code_system, value5_code_system_name, value5_code_system_version, value5_display_null_flavor, value5_display_value, value5_orig_txt_null_flavor, value5_orig_txt_value, value5_orig_txt_description, cd_datatype_value7_id) VALUES (2, 'CODE2', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO cd_datatype (id, value1_code, value2_null_flavor, value2_code, value3_code, value4_null_flavor, value4_code, value4_code_system, value4_code_system_version, value4_code_system_name, value4_display_null_flavor, value4_display_value, value4_orig_txt_null_flavor, value4_orig_txt_value, value4_orig_txt_description, value5_null_flavor, value5_code, value5_code_system, value5_code_system_name, value5_code_system_version, value5_display_null_flavor, value5_display_value, value5_orig_txt_null_flavor, value5_orig_txt_value, value5_orig_txt_description, cd_datatype_value7_id) VALUES (3, 'CODE3', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO cd_datatype (id, value1_code, value2_null_flavor, value2_code, value3_code, value4_null_flavor, value4_code, value4_code_system, value4_code_system_version, value4_code_system_name, value4_display_null_flavor, value4_display_value, value4_orig_txt_null_flavor, value4_orig_txt_value, value4_orig_txt_description, value5_null_flavor, value5_code, value5_code_system, value5_code_system_name, value5_code_system_version, value5_display_null_flavor, value5_display_value, value5_orig_txt_null_flavor, value5_orig_txt_value, value5_orig_txt_description, cd_datatype_value7_id) VALUES (4, 'CODE4', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO cd_datatype (id, value1_code, value2_null_flavor, value2_code, value3_code, value4_null_flavor, value4_code, value4_code_system, value4_code_system_version, value4_code_system_name, value4_display_null_flavor, value4_display_value, value4_orig_txt_null_flavor, value4_orig_txt_value, value4_orig_txt_description, value5_null_flavor, value5_code, value5_code_system, value5_code_system_name, value5_code_system_version, value5_display_null_flavor, value5_display_value, value5_orig_txt_null_flavor, value5_orig_txt_value, value5_orig_txt_description, cd_datatype_value7_id) VALUES (5, 'CODE5', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO cd_datatype (id, value1_code, value2_null_flavor, value2_code, value3_code, value4_null_flavor, value4_code, value4_code_system, value4_code_system_version, value4_code_system_name, value4_display_null_flavor, value4_display_value, value4_orig_txt_null_flavor, value4_orig_txt_value, value4_orig_txt_description, value5_null_flavor, value5_code, value5_code_system, value5_code_system_name, value5_code_system_version, value5_display_null_flavor, value5_display_value, value5_orig_txt_null_flavor, value5_orig_txt_value, value5_orig_txt_description, cd_datatype_value7_id) VALUES (6, NULL, NULL, 'CODE1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO cd_datatype (id, value1_code, value2_null_flavor, value2_code, value3_code, value4_null_flavor, value4_code, value4_code_system, value4_code_system_version, value4_code_system_name, value4_display_null_flavor, value4_display_value, value4_orig_txt_null_flavor, value4_orig_txt_value, value4_orig_txt_description, value5_null_flavor, value5_code, value5_code_system, value5_code_system_name, value5_code_system_version, value5_display_null_flavor, value5_display_value, value5_orig_txt_null_flavor, value5_orig_txt_value, value5_orig_txt_description, cd_datatype_value7_id) VALUES (7, NULL, NULL, 'CODE2', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO cd_datatype (id, value1_code, value2_null_flavor, value2_code, value3_code, value4_null_flavor, value4_code, value4_code_system, value4_code_system_version, value4_code_system_name, value4_display_null_flavor, value4_display_value, value4_orig_txt_null_flavor, value4_orig_txt_value, value4_orig_txt_description, value5_null_flavor, value5_code, value5_code_system, value5_code_system_name, value5_code_system_version, value5_display_null_flavor, value5_display_value, value5_orig_txt_null_flavor, value5_orig_txt_value, value5_orig_txt_description, cd_datatype_value7_id) VALUES (8, NULL, NULL, 'CODE3', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO cd_datatype (id, value1_code, value2_null_flavor, value2_code, value3_code, value4_null_flavor, value4_code, value4_code_system, value4_code_system_version, value4_code_system_name, value4_display_null_flavor, value4_display_value, value4_orig_txt_null_flavor, value4_orig_txt_value, value4_orig_txt_description, value5_null_flavor, value5_code, value5_code_system, value5_code_system_name, value5_code_system_version, value5_display_null_flavor, value5_display_value, value5_orig_txt_null_flavor, value5_orig_txt_value, value5_orig_txt_description, cd_datatype_value7_id) VALUES (9, NULL, 'NI', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO cd_datatype (id, value1_code, value2_null_flavor, value2_code, value3_code, value4_null_flavor, value4_code, value4_code_system, value4_code_system_version, value4_code_system_name, value4_display_null_flavor, value4_display_value, value4_orig_txt_null_flavor, value4_orig_txt_value, value4_orig_txt_description, value5_null_flavor, value5_code, value5_code_system, value5_code_system_name, value5_code_system_version, value5_display_null_flavor, value5_display_value, value5_orig_txt_null_flavor, value5_orig_txt_value, value5_orig_txt_description, cd_datatype_value7_id) VALUES (10, NULL, 'NI', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO cd_datatype (id, value1_code, value2_null_flavor, value2_code, value3_code, value4_null_flavor, value4_code, value4_code_system, value4_code_system_version, value4_code_system_name, value4_display_null_flavor, value4_display_value, value4_orig_txt_null_flavor, value4_orig_txt_value, value4_orig_txt_description, value5_null_flavor, value5_code, value5_code_system, value5_code_system_name, value5_code_system_version, value5_display_null_flavor, value5_display_value, value5_orig_txt_null_flavor, value5_orig_txt_value, value5_orig_txt_description, cd_datatype_value7_id) VALUES (11, NULL, NULL, NULL, 'CODE1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO cd_datatype (id, value1_code, value2_null_flavor, value2_code, value3_code, value4_null_flavor, value4_code, value4_code_system, value4_code_system_version, value4_code_system_name, value4_display_null_flavor, value4_display_value, value4_orig_txt_null_flavor, value4_orig_txt_value, value4_orig_txt_description, value5_null_flavor, value5_code, value5_code_system, value5_code_system_name, value5_code_system_version, value5_display_null_flavor, value5_display_value, value5_orig_txt_null_flavor, value5_orig_txt_value, value5_orig_txt_description, cd_datatype_value7_id) VALUES (12, NULL, NULL, NULL, 'CODE2', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO cd_datatype (id, value1_code, value2_null_flavor, value2_code, value3_code, value4_null_flavor, value4_code, value4_code_system, value4_code_system_version, value4_code_system_name, value4_display_null_flavor, value4_display_value, value4_orig_txt_null_flavor, value4_orig_txt_value, value4_orig_txt_description, value5_null_flavor, value5_code, value5_code_system, value5_code_system_name, value5_code_system_version, value5_display_null_flavor, value5_display_value, value5_orig_txt_null_flavor, value5_orig_txt_value, value5_orig_txt_description, cd_datatype_value7_id) VALUES (13, NULL, NULL, NULL, 'CODE3', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO cd_datatype (id, value1_code, value2_null_flavor, value2_code, value3_code, value4_null_flavor, value4_code, value4_code_system, value4_code_system_version, value4_code_system_name, value4_display_null_flavor, value4_display_value, value4_orig_txt_null_flavor, value4_orig_txt_value, value4_orig_txt_description, value5_null_flavor, value5_code, value5_code_system, value5_code_system_name, value5_code_system_version, value5_display_null_flavor, value5_display_value, value5_orig_txt_null_flavor, value5_orig_txt_value, value5_orig_txt_description, cd_datatype_value7_id) VALUES (14, NULL, NULL, NULL, 'CODE4', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO cd_datatype (id, value1_code, value2_null_flavor, value2_code, value3_code, value4_null_flavor, value4_code, value4_code_system, value4_code_system_version, value4_code_system_name, value4_display_null_flavor, value4_display_value, value4_orig_txt_null_flavor, value4_orig_txt_value, value4_orig_txt_description, value5_null_flavor, value5_code, value5_code_system, value5_code_system_name, value5_code_system_version, value5_display_null_flavor, value5_display_value, value5_orig_txt_null_flavor, value5_orig_txt_value, value5_orig_txt_description, cd_datatype_value7_id) VALUES (15, NULL, NULL, NULL, 'CODE5', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO cd_datatype (id, value1_code, value2_null_flavor, value2_code, value3_code, value4_null_flavor, value4_code, value4_code_system, value4_code_system_version, value4_code_system_name, value4_display_null_flavor, value4_display_value, value4_orig_txt_null_flavor, value4_orig_txt_value, value4_orig_txt_description, value5_null_flavor, value5_code, value5_code_system, value5_code_system_name, value5_code_system_version, value5_display_null_flavor, value5_display_value, value5_orig_txt_null_flavor, value5_orig_txt_value, value5_orig_txt_description, cd_datatype_value7_id) VALUES (16, NULL, NULL, NULL, NULL, 'NI', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO cd_datatype (id, value1_code, value2_null_flavor, value2_code, value3_code, value4_null_flavor, value4_code, value4_code_system, value4_code_system_version, value4_code_system_name, value4_display_null_flavor, value4_display_value, value4_orig_txt_null_flavor, value4_orig_txt_value, value4_orig_txt_description, value5_null_flavor, value5_code, value5_code_system, value5_code_system_name, value5_code_system_version, value5_display_null_flavor, value5_display_value, value5_orig_txt_null_flavor, value5_orig_txt_value, value5_orig_txt_description, cd_datatype_value7_id) VALUES (17, NULL, NULL, NULL, NULL, NULL, 'CODE2', NULL, NULL, NULL, NULL, 'VALUE4_DISPLAY_VALUE1', 'NI', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO cd_datatype (id, value1_code, value2_null_flavor, value2_code, value3_code, value4_null_flavor, value4_code, value4_code_system, value4_code_system_version, value4_code_system_name, value4_display_null_flavor, value4_display_value, value4_orig_txt_null_flavor, value4_orig_txt_value, value4_orig_txt_description, value5_null_flavor, value5_code, value5_code_system, value5_code_system_name, value5_code_system_version, value5_display_null_flavor, value5_display_value, value5_orig_txt_null_flavor, value5_orig_txt_value, value5_orig_txt_description, cd_datatype_value7_id) VALUES (18, NULL, NULL, NULL, NULL, NULL, 'CODE3', NULL, NULL, NULL, NULL, 'VALUE4_DISPLAY_VALUE2', NULL, NULL, 'VALUE4_ORIG_TXT_DESCRIPTION1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO cd_datatype (id, value1_code, value2_null_flavor, value2_code, value3_code, value4_null_flavor, value4_code, value4_code_system, value4_code_system_version, value4_code_system_name, value4_display_null_flavor, value4_display_value, value4_orig_txt_null_flavor, value4_orig_txt_value, value4_orig_txt_description, value5_null_flavor, value5_code, value5_code_system, value5_code_system_name, value5_code_system_version, value5_display_null_flavor, value5_display_value, value5_orig_txt_null_flavor, value5_orig_txt_value, value5_orig_txt_description, cd_datatype_value7_id) VALUES (19, NULL, NULL, NULL, NULL, NULL, 'CODE4', NULL, NULL, NULL, NULL, 'VALUE4_DISPLAY_VALUE2', NULL, 'VALUE4_ORIG_TXT_VALUE1', 'VALUE4_ORIG_TXT_DESCRIPTION2', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO cd_datatype (id, value1_code, value2_null_flavor, value2_code, value3_code, value4_null_flavor, value4_code, value4_code_system, value4_code_system_version, value4_code_system_name, value4_display_null_flavor, value4_display_value, value4_orig_txt_null_flavor, value4_orig_txt_value, value4_orig_txt_description, value5_null_flavor, value5_code, value5_code_system, value5_code_system_name, value5_code_system_version, value5_display_null_flavor, value5_display_value, value5_orig_txt_null_flavor, value5_orig_txt_value, value5_orig_txt_description, cd_datatype_value7_id) VALUES (20, NULL, NULL, NULL, NULL, NULL, 'CODE5', 'VALUE4_CODE_SYSTEM', NULL, NULL, NULL, 'VALUE4_DISPLAY_VALUE3', NULL, 'VALUE4_ORIG_TXT_VALUE2', 'VALUE4_ORIG_TXT_DESCRIPTION3', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO cd_datatype (id, value1_code, value2_null_flavor, value2_code, value3_code, value4_null_flavor, value4_code, value4_code_system, value4_code_system_version, value4_code_system_name, value4_display_null_flavor, value4_display_value, value4_orig_txt_null_flavor, value4_orig_txt_value, value4_orig_txt_description, value5_null_flavor, value5_code, value5_code_system, value5_code_system_name, value5_code_system_version, value5_display_null_flavor, value5_display_value, value5_orig_txt_null_flavor, value5_orig_txt_value, value5_orig_txt_description, cd_datatype_value7_id) VALUES (21, NULL, NULL, NULL, NULL, NULL, 'CODE6', 'VALUE4_CODE_SYSTEM', NULL, NULL, NULL, 'VALUE4_DISPLAY_VALUE4', NULL, 'VALUE4_ORIG_TXT_VALUE3', 'VALUE4_ORIG_TXT_DESCRIPTION4', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO cd_datatype (id, value1_code, value2_null_flavor, value2_code, value3_code, value4_null_flavor, value4_code, value4_code_system, value4_code_system_version, value4_code_system_name, value4_display_null_flavor, value4_display_value, value4_orig_txt_null_flavor, value4_orig_txt_value, value4_orig_txt_description, value5_null_flavor, value5_code, value5_code_system, value5_code_system_name, value5_code_system_version, value5_display_null_flavor, value5_display_value, value5_orig_txt_null_flavor, value5_orig_txt_value, value5_orig_txt_description, cd_datatype_value7_id) VALUES (22, NULL, NULL, NULL, NULL, NULL, 'CODE7', 'VALUE4_CODE_SYSTEM', 'VALUE4_CODE_SYSTEM_VERSION1', NULL, NULL, 'VALUE4_DISPLAY_VALUE4', NULL, 'VALUE4_ORIG_TXT_VALUE4', 'VALUE4_ORIG_TXT_DESCRIPTION5', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO cd_datatype (id, value1_code, value2_null_flavor, value2_code, value3_code, value4_null_flavor, value4_code, value4_code_system, value4_code_system_version, value4_code_system_name, value4_display_null_flavor, value4_display_value, value4_orig_txt_null_flavor, value4_orig_txt_value, value4_orig_txt_description, value5_null_flavor, value5_code, value5_code_system, value5_code_system_name, value5_code_system_version, value5_display_null_flavor, value5_display_value, value5_orig_txt_null_flavor, value5_orig_txt_value, value5_orig_txt_description, cd_datatype_value7_id) VALUES (23, NULL, NULL, NULL, NULL, NULL, 'CODE8', 'VALUE4_CODE_SYSTEM', 'VALUE4_CODE_SYSTEM_VERSION1', 'VALUE4_CODE_SYSTEM_NAME', NULL, 'VALUE4_DISPLAY_VALUE4', NULL, 'VALUE4_ORIG_TXT_VALUE5', 'VALUE4_ORIG_TXT_DESCRIPTION6', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO cd_datatype (id, value1_code, value2_null_flavor, value2_code, value3_code, value4_null_flavor, value4_code, value4_code_system, value4_code_system_version, value4_code_system_name, value4_display_null_flavor, value4_display_value, value4_orig_txt_null_flavor, value4_orig_txt_value, value4_orig_txt_description, value5_null_flavor, value5_code, value5_code_system, value5_code_system_name, value5_code_system_version, value5_display_null_flavor, value5_display_value, value5_orig_txt_null_flavor, value5_orig_txt_value, value5_orig_txt_description, cd_datatype_value7_id) VALUES (24, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'CODE1', 'VALUE5_CODE_SYSTEM1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO cd_datatype (id, value1_code, value2_null_flavor, value2_code, value3_code, value4_null_flavor, value4_code, value4_code_system, value4_code_system_version, value4_code_system_name, value4_display_null_flavor, value4_display_value, value4_orig_txt_null_flavor, value4_orig_txt_value, value4_orig_txt_description, value5_null_flavor, value5_code, value5_code_system, value5_code_system_name, value5_code_system_version, value5_display_null_flavor, value5_display_value, value5_orig_txt_null_flavor, value5_orig_txt_value, value5_orig_txt_description, cd_datatype_value7_id) VALUES (25, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'CODE2', 'VALUE5_CODE_SYSTEM2', NULL, NULL, NULL, 'VALUE5_DISPLAY_VALUE1', NULL, NULL, NULL, NULL);
INSERT INTO cd_datatype (id, value1_code, value2_null_flavor, value2_code, value3_code, value4_null_flavor, value4_code, value4_code_system, value4_code_system_version, value4_code_system_name, value4_display_null_flavor, value4_display_value, value4_orig_txt_null_flavor, value4_orig_txt_value, value4_orig_txt_description, value5_null_flavor, value5_code, value5_code_system, value5_code_system_name, value5_code_system_version, value5_display_null_flavor, value5_display_value, value5_orig_txt_null_flavor, value5_orig_txt_value, value5_orig_txt_description, cd_datatype_value7_id) VALUES (26, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'CODE3', NULL, 'VALUE5_CODE_SYSTEM_NAME1', NULL, NULL, 'VALUE5_DISPLAY_VALUE2', NULL, NULL, 'VALUE5_ORIG_TXT_DESCRIPTION1', NULL);
INSERT INTO cd_datatype (id, value1_code, value2_null_flavor, value2_code, value3_code, value4_null_flavor, value4_code, value4_code_system, value4_code_system_version, value4_code_system_name, value4_display_null_flavor, value4_display_value, value4_orig_txt_null_flavor, value4_orig_txt_value, value4_orig_txt_description, value5_null_flavor, value5_code, value5_code_system, value5_code_system_name, value5_code_system_version, value5_display_null_flavor, value5_display_value, value5_orig_txt_null_flavor, value5_orig_txt_value, value5_orig_txt_description, cd_datatype_value7_id) VALUES (27, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'CODE4', 'VALUE5_CODE_SYSTEM3', NULL, NULL, NULL, 'VALUE5_DISPLAY_VALUE2', NULL, 'VALUE5_ORIG_TXT_VALUE1', 'VALUE5_ORIG_TXT_DESCRIPTION2', NULL);
INSERT INTO cd_datatype (id, value1_code, value2_null_flavor, value2_code, value3_code, value4_null_flavor, value4_code, value4_code_system, value4_code_system_version, value4_code_system_name, value4_display_null_flavor, value4_display_value, value4_orig_txt_null_flavor, value4_orig_txt_value, value4_orig_txt_description, value5_null_flavor, value5_code, value5_code_system, value5_code_system_name, value5_code_system_version, value5_display_null_flavor, value5_display_value, value5_orig_txt_null_flavor, value5_orig_txt_value, value5_orig_txt_description, cd_datatype_value7_id) VALUES (28, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'CODE5', 'VALUE5_CODE_SYSTEM', 'VALUE5_CODE_SYSTEM_NAME2', NULL, NULL, 'VALUE5_DISPLAY_VALUE3', NULL, 'VALUE5_ORIG_TXT_VALUE2', 'VALUE5_ORIG_TXT_DESCRIPTION3', NULL);
INSERT INTO cd_datatype (id, value1_code, value2_null_flavor, value2_code, value3_code, value4_null_flavor, value4_code, value4_code_system, value4_code_system_version, value4_code_system_name, value4_display_null_flavor, value4_display_value, value4_orig_txt_null_flavor, value4_orig_txt_value, value4_orig_txt_description, value5_null_flavor, value5_code, value5_code_system, value5_code_system_name, value5_code_system_version, value5_display_null_flavor, value5_display_value, value5_orig_txt_null_flavor, value5_orig_txt_value, value5_orig_txt_description, cd_datatype_value7_id) VALUES (29, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'CODE6', 'VALUE5_CODE_SYSTEM', NULL, NULL, NULL, 'VALUE5_DISPLAY_VALUE5', NULL, 'VALUE5_ORIG_TXT_VALUE3', 'VALUE5_ORIG_TXT_DESCRIPTION4', NULL);
INSERT INTO cd_datatype (id, value1_code, value2_null_flavor, value2_code, value3_code, value4_null_flavor, value4_code, value4_code_system, value4_code_system_version, value4_code_system_name, value4_display_null_flavor, value4_display_value, value4_orig_txt_null_flavor, value4_orig_txt_value, value4_orig_txt_description, value5_null_flavor, value5_code, value5_code_system, value5_code_system_name, value5_code_system_version, value5_display_null_flavor, value5_display_value, value5_orig_txt_null_flavor, value5_orig_txt_value, value5_orig_txt_description, cd_datatype_value7_id) VALUES (30, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'CODE7', 'VALUE5_CODE_SYSTEM', NULL, 'VALUE5_CODE_SYSTEM_VERSION1', NULL, 'VALUE5_DISPLAY_VALUE5', NULL, 'VALUE5_ORIG_TXT_VALUE5', 'VALUE5_ORIG_TXT_DESCRIPTION5', NULL);
INSERT INTO cd_datatype (id, value1_code, value2_null_flavor, value2_code, value3_code, value4_null_flavor, value4_code, value4_code_system, value4_code_system_version, value4_code_system_name, value4_display_null_flavor, value4_display_value, value4_orig_txt_null_flavor, value4_orig_txt_value, value4_orig_txt_description, value5_null_flavor, value5_code, value5_code_system, value5_code_system_name, value5_code_system_version, value5_display_null_flavor, value5_display_value, value5_orig_txt_null_flavor, value5_orig_txt_value, value5_orig_txt_description, cd_datatype_value7_id) VALUES (31, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'CODE8', 'VALUE5_CODE_SYSTEM', 'VALUE5_CODE_SYSTEM_NAME', 'VALUE5_CODE_SYSTEM_VERSION1', NULL, 'VALUE5_DISPLAY_VALUE5', NULL, 'VALUE5_ORIG_TXT_VALUE5', 'VALUE5_ORIG_TXT_DESCRIPTION6', NULL);
INSERT INTO cd_datatype (id, value1_code, value2_null_flavor, value2_code, value3_code, value4_null_flavor, value4_code, value4_code_system, value4_code_system_version, value4_code_system_name, value4_display_null_flavor, value4_display_value, value4_orig_txt_null_flavor, value4_orig_txt_value, value4_orig_txt_description, value5_null_flavor, value5_code, value5_code_system, value5_code_system_name, value5_code_system_version, value5_display_null_flavor, value5_display_value, value5_orig_txt_null_flavor, value5_orig_txt_value, value5_orig_txt_description, cd_datatype_value7_id) VALUES (32, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'CODE1', 'VALUE5_CODE_SYSTEM1', NULL, NULL, 'NI', NULL, 'NI', NULL, NULL, 1);
INSERT INTO cd_datatype (id, value1_code, value2_null_flavor, value2_code, value3_code, value4_null_flavor, value4_code, value4_code_system, value4_code_system_version, value4_code_system_name, value4_display_null_flavor, value4_display_value, value4_orig_txt_null_flavor, value4_orig_txt_value, value4_orig_txt_description, value5_null_flavor, value5_code, value5_code_system, value5_code_system_name, value5_code_system_version, value5_display_null_flavor, value5_display_value, value5_orig_txt_null_flavor, value5_orig_txt_value, value5_orig_txt_description, cd_datatype_value7_id) VALUES (33, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'CODE2', 'VALUE5_CODE_SYSTEM2', NULL, NULL, NULL, 'VALUE5_DISPLAY_VALUE1', 'NI', NULL, NULL, 2);
INSERT INTO cd_datatype (id, value1_code, value2_null_flavor, value2_code, value3_code, value4_null_flavor, value4_code, value4_code_system, value4_code_system_version, value4_code_system_name, value4_display_null_flavor, value4_display_value, value4_orig_txt_null_flavor, value4_orig_txt_value, value4_orig_txt_description, value5_null_flavor, value5_code, value5_code_system, value5_code_system_name, value5_code_system_version, value5_display_null_flavor, value5_display_value, value5_orig_txt_null_flavor, value5_orig_txt_value, value5_orig_txt_description, cd_datatype_value7_id) VALUES (34, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'CODE3', NULL, 'VALUE5_CODE_SYSTEM_NAME1', NULL, NULL, 'VALUE5_DISPLAY_VALUE2', NULL, NULL, 'VALUE5_ORIG_TXT_DESCRIPTION1', 3);
INSERT INTO cd_datatype (id, value1_code, value2_null_flavor, value2_code, value3_code, value4_null_flavor, value4_code, value4_code_system, value4_code_system_version, value4_code_system_name, value4_display_null_flavor, value4_display_value, value4_orig_txt_null_flavor, value4_orig_txt_value, value4_orig_txt_description, value5_null_flavor, value5_code, value5_code_system, value5_code_system_name, value5_code_system_version, value5_display_null_flavor, value5_display_value, value5_orig_txt_null_flavor, value5_orig_txt_value, value5_orig_txt_description, cd_datatype_value7_id) VALUES (35, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'CODE4', 'VALUE5_CODE_SYSTEM3', NULL, NULL, NULL, 'VALUE5_DISPLAY_VALUE2', NULL, 'VALUE5_ORIG_TXT_VALUE1', 'VALUE5_ORIG_TXT_DESCRIPTION2', 4);
INSERT INTO cd_datatype (id, value1_code, value2_null_flavor, value2_code, value3_code, value4_null_flavor, value4_code, value4_code_system, value4_code_system_version, value4_code_system_name, value4_display_null_flavor, value4_display_value, value4_orig_txt_null_flavor, value4_orig_txt_value, value4_orig_txt_description, value5_null_flavor, value5_code, value5_code_system, value5_code_system_name, value5_code_system_version, value5_display_null_flavor, value5_display_value, value5_orig_txt_null_flavor, value5_orig_txt_value, value5_orig_txt_description, cd_datatype_value7_id) VALUES (36, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'CODE5', 'VALUE5_CODE_SYSTEM', 'VALUE5_CODE_SYSTEM_NAME2', NULL, NULL, 'VALUE5_DISPLAY_VALUE3', NULL, 'VALUE5_ORIG_TXT_VALUE2', 'VALUE5_ORIG_TXT_DESCRIPTION3', 5);
INSERT INTO cd_datatype (id, value1_code, value2_null_flavor, value2_code, value3_code, value4_null_flavor, value4_code, value4_code_system, value4_code_system_version, value4_code_system_name, value4_display_null_flavor, value4_display_value, value4_orig_txt_null_flavor, value4_orig_txt_value, value4_orig_txt_description, value5_null_flavor, value5_code, value5_code_system, value5_code_system_name, value5_code_system_version, value5_display_null_flavor, value5_display_value, value5_orig_txt_null_flavor, value5_orig_txt_value, value5_orig_txt_description, cd_datatype_value7_id) VALUES (37, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'CODE6', 'VALUE5_CODE_SYSTEM', NULL, NULL, NULL, 'VALUE5_DISPLAY_VALUE5', NULL, 'VALUE5_ORIG_TXT_VALUE3', 'VALUE5_ORIG_TXT_DESCRIPTION4', 6);
INSERT INTO cd_datatype (id, value1_code, value2_null_flavor, value2_code, value3_code, value4_null_flavor, value4_code, value4_code_system, value4_code_system_version, value4_code_system_name, value4_display_null_flavor, value4_display_value, value4_orig_txt_null_flavor, value4_orig_txt_value, value4_orig_txt_description, value5_null_flavor, value5_code, value5_code_system, value5_code_system_name, value5_code_system_version, value5_display_null_flavor, value5_display_value, value5_orig_txt_null_flavor, value5_orig_txt_value, value5_orig_txt_description, cd_datatype_value7_id) VALUES (38, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'CODE7', 'VALUE5_CODE_SYSTEM', NULL, 'VALUE5_CODE_SYSTEM_VERSION1', NULL, 'VALUE5_DISPLAY_VALUE5', NULL, 'VALUE5_ORIG_TXT_VALUE5', 'VALUE5_ORIG_TXT_DESCRIPTION5', 7);
INSERT INTO cd_datatype (id, value1_code, value2_null_flavor, value2_code, value3_code, value4_null_flavor, value4_code, value4_code_system, value4_code_system_version, value4_code_system_name, value4_display_null_flavor, value4_display_value, value4_orig_txt_null_flavor, value4_orig_txt_value, value4_orig_txt_description, value5_null_flavor, value5_code, value5_code_system, value5_code_system_name, value5_code_system_version, value5_display_null_flavor, value5_display_value, value5_orig_txt_null_flavor, value5_orig_txt_value, value5_orig_txt_description, cd_datatype_value7_id) VALUES (39, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'CODE8', 'VALUE5_CODE_SYSTEM', 'VALUE5_CODE_SYSTEM_NAME', 'VALUE5_CODE_SYSTEM_VERSION1', NULL, 'VALUE5_DISPLAY_VALUE5', NULL, 'VALUE5_ORIG_TXT_VALUE5', 'VALUE5_ORIG_TXT_DESCRIPTION6', 8);
INSERT INTO cd_datatype (id, value1_code, value2_null_flavor, value2_code, value3_code, value4_null_flavor, value4_code, value4_code_system, value4_code_system_version, value4_code_system_name, value4_display_null_flavor, value4_display_value, value4_orig_txt_null_flavor, value4_orig_txt_value, value4_orig_txt_description, value5_null_flavor, value5_code, value5_code_system, value5_code_system_name, value5_code_system_version, value5_display_null_flavor, value5_display_value, value5_orig_txt_null_flavor, value5_orig_txt_value, value5_orig_txt_description, cd_datatype_value7_id) VALUES (40, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO cd_datatype (id, value1_code, value2_null_flavor, value2_code, value3_code, value4_null_flavor, value4_code, value4_code_system, value4_code_system_version, value4_code_system_name, value4_display_null_flavor, value4_display_value, value4_orig_txt_null_flavor, value4_orig_txt_value, value4_orig_txt_description, value5_null_flavor, value5_code, value5_code_system, value5_code_system_name, value5_code_system_version, value5_display_null_flavor, value5_display_value, value5_orig_txt_null_flavor, value5_orig_txt_value, value5_orig_txt_description, cd_datatype_value7_id) VALUES (41, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO cd_datatype (id, value1_code, value2_null_flavor, value2_code, value3_code, value4_null_flavor, value4_code, value4_code_system, value4_code_system_version, value4_code_system_name, value4_display_null_flavor, value4_display_value, value4_orig_txt_null_flavor, value4_orig_txt_value, value4_orig_txt_description, value5_null_flavor, value5_code, value5_code_system, value5_code_system_name, value5_code_system_version, value5_display_null_flavor, value5_display_value, value5_orig_txt_null_flavor, value5_orig_txt_value, value5_orig_txt_description, cd_datatype_value7_id) VALUES (42, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO cd_datatype (id, value1_code, value2_null_flavor, value2_code, value3_code, value4_null_flavor, value4_code, value4_code_system, value4_code_system_version, value4_code_system_name, value4_display_null_flavor, value4_display_value, value4_orig_txt_null_flavor, value4_orig_txt_value, value4_orig_txt_description, value5_null_flavor, value5_code, value5_code_system, value5_code_system_name, value5_code_system_version, value5_display_null_flavor, value5_display_value, value5_orig_txt_null_flavor, value5_orig_txt_value, value5_orig_txt_description, cd_datatype_value7_id) VALUES (43, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO cd_datatype (id, value1_code, value2_null_flavor, value2_code, value3_code, value4_null_flavor, value4_code, value4_code_system, value4_code_system_version, value4_code_system_name, value4_display_null_flavor, value4_display_value, value4_orig_txt_null_flavor, value4_orig_txt_value, value4_orig_txt_description, value5_null_flavor, value5_code, value5_code_system, value5_code_system_name, value5_code_system_version, value5_display_null_flavor, value5_display_value, value5_orig_txt_null_flavor, value5_orig_txt_value, value5_orig_txt_description, cd_datatype_value7_id) VALUES (44, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO cd_datatype (id, value1_code, value2_null_flavor, value2_code, value3_code, value4_null_flavor, value4_code, value4_code_system, value4_code_system_version, value4_code_system_name, value4_display_null_flavor, value4_display_value, value4_orig_txt_null_flavor, value4_orig_txt_value, value4_orig_txt_description, value5_null_flavor, value5_code, value5_code_system, value5_code_system_name, value5_code_system_version, value5_display_null_flavor, value5_display_value, value5_orig_txt_null_flavor, value5_orig_txt_value, value5_orig_txt_description, cd_datatype_value7_id) VALUES (45, NULL, NULL, 'CODE61', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO cd_datatype (id, value1_code, value2_null_flavor, value2_code, value3_code, value4_null_flavor, value4_code, value4_code_system, value4_code_system_version, value4_code_system_name, value4_display_null_flavor, value4_display_value, value4_orig_txt_null_flavor, value4_orig_txt_value, value4_orig_txt_description, value5_null_flavor, value5_code, value5_code_system, value5_code_system_name, value5_code_system_version, value5_display_null_flavor, value5_display_value, value5_orig_txt_null_flavor, value5_orig_txt_value, value5_orig_txt_description, cd_datatype_value7_id) VALUES (46, NULL, NULL, 'CODE62', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO cd_datatype (id, value1_code, value2_null_flavor, value2_code, value3_code, value4_null_flavor, value4_code, value4_code_system, value4_code_system_version, value4_code_system_name, value4_display_null_flavor, value4_display_value, value4_orig_txt_null_flavor, value4_orig_txt_value, value4_orig_txt_description, value5_null_flavor, value5_code, value5_code_system, value5_code_system_name, value5_code_system_version, value5_display_null_flavor, value5_display_value, value5_orig_txt_null_flavor, value5_orig_txt_value, value5_orig_txt_description, cd_datatype_value7_id) VALUES (47, NULL, NULL, 'CODE63', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO cd_datatype (id, value1_code, value2_null_flavor, value2_code, value3_code, value4_null_flavor, value4_code, value4_code_system, value4_code_system_version, value4_code_system_name, value4_display_null_flavor, value4_display_value, value4_orig_txt_null_flavor, value4_orig_txt_value, value4_orig_txt_description, value5_null_flavor, value5_code, value5_code_system, value5_code_system_name, value5_code_system_version, value5_display_null_flavor, value5_display_value, value5_orig_txt_null_flavor, value5_orig_txt_value, value5_orig_txt_description, cd_datatype_value7_id) VALUES (48, NULL, NULL, 'CODE64', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO cd_datatype (id, value1_code, value2_null_flavor, value2_code, value3_code, value4_null_flavor, value4_code, value4_code_system, value4_code_system_version, value4_code_system_name, value4_display_null_flavor, value4_display_value, value4_orig_txt_null_flavor, value4_orig_txt_value, value4_orig_txt_description, value5_null_flavor, value5_code, value5_code_system, value5_code_system_name, value5_code_system_version, value5_display_null_flavor, value5_display_value, value5_orig_txt_null_flavor, value5_orig_txt_value, value5_orig_txt_description, cd_datatype_value7_id) VALUES (49, NULL, NULL, 'CODE65', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO cd_datatype (id, value1_code, value2_null_flavor, value2_code, value3_code, value4_null_flavor, value4_code, value4_code_system, value4_code_system_version, value4_code_system_name, value4_display_null_flavor, value4_display_value, value4_orig_txt_null_flavor, value4_orig_txt_value, value4_orig_txt_description, value5_null_flavor, value5_code, value5_code_system, value5_code_system_name, value5_code_system_version, value5_display_null_flavor, value5_display_value, value5_orig_txt_null_flavor, value5_orig_txt_value, value5_orig_txt_description, cd_datatype_value7_id) VALUES (50, NULL, NULL, 'CODE66', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);


--
-- Data for Name: cd_datatype_value6; Type: TABLE DATA; 
--

INSERT INTO cd_datatype_value6 (cd_datatype_id, null_flavor, code, code_system, code_system_name, code_system_version, display_null_flavor, display_value, orig_txt_null_flavor, orig_txt_value, orig_txt_description) VALUES (45, NULL, 'CODE1', NULL, NULL, NULL, NULL, 'VALUE6_DISPLAY_VALUE1', 'NI', NULL, NULL);
INSERT INTO cd_datatype_value6 (cd_datatype_id, null_flavor, code, code_system, code_system_name, code_system_version, display_null_flavor, display_value, orig_txt_null_flavor, orig_txt_value, orig_txt_description) VALUES (46, NULL, 'CODE2', NULL, NULL, NULL, NULL, 'VALUE6_DISPLAY_VALUE2', NULL, 'ORIG_TXT_VALUE1', NULL);
INSERT INTO cd_datatype_value6 (cd_datatype_id, null_flavor, code, code_system, code_system_name, code_system_version, display_null_flavor, display_value, orig_txt_null_flavor, orig_txt_value, orig_txt_description) VALUES (47, NULL, 'CODE3', NULL, NULL, NULL, NULL, 'VALUE6_DISPLAY_VALUE3', NULL, 'ORIG_TXT_VALUE2', 'ORIG_TXT_DESCRIPTION1');
INSERT INTO cd_datatype_value6 (cd_datatype_id, null_flavor, code, code_system, code_system_name, code_system_version, display_null_flavor, display_value, orig_txt_null_flavor, orig_txt_value, orig_txt_description) VALUES (48, NULL, 'CODE4', 'CODE_SYSTEM_61', NULL, NULL, NULL, 'VALUE6_DISPLAY_VALUE4', NULL, 'ORIG_TXT_VALUE3', 'ORIG_TXT_DESCRIPTION2');
INSERT INTO cd_datatype_value6 (cd_datatype_id, null_flavor, code, code_system, code_system_name, code_system_version, display_null_flavor, display_value, orig_txt_null_flavor, orig_txt_value, orig_txt_description) VALUES (49, NULL, 'CODE5', 'CODE_SYSTEM_62', 'CODE_SYSTEM_NAME_61', NULL, NULL, 'VALUE6_DISPLAY_VALUE5', NULL, 'ORIG_TXT_VALUE4', 'ORIG_TXT_DESCRIPTION3');
INSERT INTO cd_datatype_value6 (cd_datatype_id, null_flavor, code, code_system, code_system_name, code_system_version, display_null_flavor, display_value, orig_txt_null_flavor, orig_txt_value, orig_txt_description) VALUES (50, NULL, 'CODE1', 'CODE_SYSTEM_63', 'CODE_SYSTEM_NAME_62', 'CODE_SYSTEM_VERSION_61', NULL, 'VALUE6_DISPLAY_VALUE6', NULL, NULL, NULL);


--
-- TOC entry 2233 (class 0 OID 18521)
-- Dependencies: 1659
-- Data for Name: cd_datatype_value7; Type: TABLE DATA; 
--

INSERT INTO cd_datatype_value7 (id, null_flavor, code, code_system, code_system_name, code_system_version, display_null_flavor, display_value, orig_txt_null_flavor, orig_txt_value, orig_txt_description) VALUES (1, NULL, 'CODE1', NULL, NULL, NULL, NULL, 'VALUE7_DISPLAY_VALUE1', NULL, 'ORIG_TXT_VALUE1', NULL);
INSERT INTO cd_datatype_value7 (id, null_flavor, code, code_system, code_system_name, code_system_version, display_null_flavor, display_value, orig_txt_null_flavor, orig_txt_value, orig_txt_description) VALUES (2, NULL, 'CODE2', NULL, NULL, NULL, NULL, 'VALUE7_DISPLAY_VALUE2', NULL, 'ORIG_TXT_VALUE2', 'ORIG_TXT_DESCRIPTION1');
INSERT INTO cd_datatype_value7 (id, null_flavor, code, code_system, code_system_name, code_system_version, display_null_flavor, display_value, orig_txt_null_flavor, orig_txt_value, orig_txt_description) VALUES (3, NULL, 'CODE3', 'CODE_SYSTEM_1', NULL, NULL, NULL, 'VALUE7_DISPLAY_VALUE3', NULL, 'ORIG_TXT_VALUE3', 'ORIG_TXT_DESCRIPTION2');
INSERT INTO cd_datatype_value7 (id, null_flavor, code, code_system, code_system_name, code_system_version, display_null_flavor, display_value, orig_txt_null_flavor, orig_txt_value, orig_txt_description) VALUES (4, NULL, 'CODE4', 'CODE_SYSTEM_2', 'CODE_SYSTEM_NAME_1', NULL, NULL, 'VALUE7_DISPLAY_VALUE4', NULL, 'ORIG_TXT_VALUE4', 'ORIG_TXT_DESCRIPTION3');
INSERT INTO cd_datatype_value7 (id, null_flavor, code, code_system, code_system_name, code_system_version, display_null_flavor, display_value, orig_txt_null_flavor, orig_txt_value, orig_txt_description) VALUES (5, NULL, 'CODE5', 'CODE_SYSTEM_3', 'CODE_SYSTEM_NAME_2', 'CODE_SYSTEM_VERSION_1', NULL, 'VALUE7_DISPLAY_VALUE5', NULL, 'ORIG_TXT_VALUE5', 'ORIG_TXT_DESCRIPTION4');
INSERT INTO cd_datatype_value7 (id, null_flavor, code, code_system, code_system_name, code_system_version, display_null_flavor, display_value, orig_txt_null_flavor, orig_txt_value, orig_txt_description) VALUES (6, NULL, 'CODE6', NULL, NULL, NULL, NULL, 'VALUE7_DISPLAY_VALUE6', NULL, NULL, 'ORIG_TXT_DESCRIPTION5');
INSERT INTO cd_datatype_value7 (id, null_flavor, code, code_system, code_system_name, code_system_version, display_null_flavor, display_value, orig_txt_null_flavor, orig_txt_value, orig_txt_description) VALUES (7, NULL, 'CODE7', NULL, NULL, NULL, 'NI', NULL, NULL, 'ORIG_TXT_VALUE7', 'ORIG_TXT_DESCRIPTION6');
INSERT INTO cd_datatype_value7 (id, null_flavor, code, code_system, code_system_name, code_system_version, display_null_flavor, display_value, orig_txt_null_flavor, orig_txt_value, orig_txt_description) VALUES (8, NULL, 'CODE8', 'CODE_SYSTEM_4', NULL, NULL, NULL, 'VALUE7_DISPLAY_VALUE8', NULL, 'ORIG_TXT_VALUE8', 'ORIG_TXT_DESCRIPTION7');


--
-- TOC entry 2234 (class 0 OID 18527)
-- Dependencies: 1660
-- Data for Name: cd_datatype_value8; Type: TABLE DATA; 
--

INSERT INTO cd_datatype_value8 (id, null_flavor, code, code_system, code_system_name, code_system_version, display_null_flavor, display_value, orig_txt_null_flavor, orig_txt_value, orig_txt_description) VALUES (1, NULL, 'CODE1', NULL, NULL, NULL, NULL, 'VALUE8_DISPLAY_VALUE1', 'NI', NULL, NULL);


--
-- TOC entry 2235 (class 0 OID 18533)
-- Dependencies: 1661
-- Data for Name: dset_ad_datatype; Type: TABLE DATA; 
--

INSERT INTO dset_ad_datatype (id) VALUES (1);
INSERT INTO dset_ad_datatype (id) VALUES (2);
INSERT INTO dset_ad_datatype (id) VALUES (3);
INSERT INTO dset_ad_datatype (id) VALUES (4);
INSERT INTO dset_ad_datatype (id) VALUES (5);
INSERT INTO dset_ad_datatype (id) VALUES (6);
INSERT INTO dset_ad_datatype (id) VALUES (7);
INSERT INTO dset_ad_datatype (id) VALUES (8);
INSERT INTO dset_ad_datatype (id) VALUES (9);
INSERT INTO dset_ad_datatype (id) VALUES (10);
INSERT INTO dset_ad_datatype (id) VALUES (11);
INSERT INTO dset_ad_datatype (id) VALUES (12);
INSERT INTO dset_ad_datatype (id) VALUES (13);
INSERT INTO dset_ad_datatype (id) VALUES (14);
INSERT INTO dset_ad_datatype (id) VALUES (15);
INSERT INTO dset_ad_datatype (id) VALUES (16);
INSERT INTO dset_ad_datatype (id) VALUES (17);
INSERT INTO dset_ad_datatype (id) VALUES (18);
INSERT INTO dset_ad_datatype (id) VALUES (19);
INSERT INTO dset_ad_datatype (id) VALUES (20);
INSERT INTO dset_ad_datatype (id) VALUES (21);
INSERT INTO dset_ad_datatype (id) VALUES (22);
INSERT INTO dset_ad_datatype (id) VALUES (23);
INSERT INTO dset_ad_datatype (id) VALUES (24);
INSERT INTO dset_ad_datatype (id) VALUES (25);
INSERT INTO dset_ad_datatype (id) VALUES (26);
INSERT INTO dset_ad_datatype (id) VALUES (27);
INSERT INTO dset_ad_datatype (id) VALUES (28);
INSERT INTO dset_ad_datatype (id) VALUES (29);
INSERT INTO dset_ad_datatype (id) VALUES (30);
INSERT INTO dset_ad_datatype (id) VALUES (31);
INSERT INTO dset_ad_datatype (id) VALUES (32);
INSERT INTO dset_ad_datatype (id) VALUES (33);
INSERT INTO dset_ad_datatype (id) VALUES (34);
INSERT INTO dset_ad_datatype (id) VALUES (35);
INSERT INTO dset_ad_datatype (id) VALUES (36);
INSERT INTO dset_ad_datatype (id) VALUES (37);
INSERT INTO dset_ad_datatype (id) VALUES (38);
INSERT INTO dset_ad_datatype (id) VALUES (39);
INSERT INTO dset_ad_datatype (id) VALUES (40);
INSERT INTO dset_ad_datatype (id) VALUES (41);
INSERT INTO dset_ad_datatype (id) VALUES (42);
INSERT INTO dset_ad_datatype (id) VALUES (43);
INSERT INTO dset_ad_datatype (id) VALUES (44);
INSERT INTO dset_ad_datatype (id) VALUES (45);
INSERT INTO dset_ad_datatype (id) VALUES (46);
INSERT INTO dset_ad_datatype (id) VALUES (47);
INSERT INTO dset_ad_datatype (id) VALUES (48);
INSERT INTO dset_ad_datatype (id) VALUES (49);
INSERT INTO dset_ad_datatype (id) VALUES (50);
INSERT INTO dset_ad_datatype (id) VALUES (51);
INSERT INTO dset_ad_datatype (id) VALUES (52);
INSERT INTO dset_ad_datatype (id) VALUES (53);
INSERT INTO dset_ad_datatype (id) VALUES (54);
INSERT INTO dset_ad_datatype (id) VALUES (55);
INSERT INTO dset_ad_datatype (id) VALUES (56);
INSERT INTO dset_ad_datatype (id) VALUES (57);
INSERT INTO dset_ad_datatype (id) VALUES (58);


--
-- Data for Name: dset_ad_dset_ad_value8; Type: TABLE DATA; 
--

INSERT INTO dset_ad_dset_ad_value8 (dset_ad_datatype_id, dset_ad_value8_id) VALUES (48, 1);
INSERT INTO dset_ad_dset_ad_value8 (dset_ad_datatype_id, dset_ad_value8_id) VALUES (48, 2);
INSERT INTO dset_ad_dset_ad_value8 (dset_ad_datatype_id, dset_ad_value8_id) VALUES (48, 3);
INSERT INTO dset_ad_dset_ad_value8 (dset_ad_datatype_id, dset_ad_value8_id) VALUES (48, 4);
INSERT INTO dset_ad_dset_ad_value8 (dset_ad_datatype_id, dset_ad_value8_id) VALUES (48, 5);
INSERT INTO dset_ad_dset_ad_value8 (dset_ad_datatype_id, dset_ad_value8_id) VALUES (48, 6);
INSERT INTO dset_ad_dset_ad_value8 (dset_ad_datatype_id, dset_ad_value8_id) VALUES (48, 7);
INSERT INTO dset_ad_dset_ad_value8 (dset_ad_datatype_id, dset_ad_value8_id) VALUES (48, 8);
INSERT INTO dset_ad_dset_ad_value8 (dset_ad_datatype_id, dset_ad_value8_id) VALUES (48, 9);
INSERT INTO dset_ad_dset_ad_value8 (dset_ad_datatype_id, dset_ad_value8_id) VALUES (48, 10);


--
-- Data for Name: dset_ad_value1; Type: TABLE DATA; 
--

INSERT INTO dset_ad_value1 (dset_ad_datatype_id, adxp_al_value) VALUES (1, NULL);
INSERT INTO dset_ad_value1 (dset_ad_datatype_id, adxp_al_value) VALUES (2, '1 Jefferson Street');
INSERT INTO dset_ad_value1 (dset_ad_datatype_id, adxp_al_value) VALUES (3, '2 Jefferson Street');
INSERT INTO dset_ad_value1 (dset_ad_datatype_id, adxp_al_value) VALUES (4, '3 Jefferson Street');
INSERT INTO dset_ad_value1 (dset_ad_datatype_id, adxp_al_value) VALUES (5, '4 Sun Street');
INSERT INTO dset_ad_value1 (dset_ad_datatype_id, adxp_al_value) VALUES (6, '5 Sun Street');


--
-- Data for Name: dset_ad_value2; Type: TABLE DATA; 
--

INSERT INTO dset_ad_value2 (dset_ad_datatype_id, adxp_dal_value, adxp_dal_code) VALUES (7, 'Suite 100', NULL);
INSERT INTO dset_ad_value2 (dset_ad_datatype_id, adxp_dal_value, adxp_dal_code) VALUES (8, 'Suite 101', NULL);
INSERT INTO dset_ad_value2 (dset_ad_datatype_id, adxp_dal_value, adxp_dal_code) VALUES (9, NULL, 'CODE1');
INSERT INTO dset_ad_value2 (dset_ad_datatype_id, adxp_dal_value, adxp_dal_code) VALUES (10, 'Suite 103', 'CODE2');
INSERT INTO dset_ad_value2 (dset_ad_datatype_id, adxp_dal_value, adxp_dal_code) VALUES (11, 'Suite 104', 'CODE3');


--
-- Data for Name: dset_ad_value3; Type: TABLE DATA; 
--

INSERT INTO dset_ad_value3 (dset_ad_datatype_id, adxp_al_value, adxp_al_code, adxp_al_codesystem) VALUES (12, '1 Jefferson Street', NULL, NULL);
INSERT INTO dset_ad_value3 (dset_ad_datatype_id, adxp_al_value, adxp_al_code, adxp_al_codesystem) VALUES (13, '2 Jefferson Street', 'CODE1', NULL);
INSERT INTO dset_ad_value3 (dset_ad_datatype_id, adxp_al_value, adxp_al_code, adxp_al_codesystem) VALUES (14, NULL, 'CODE2', NULL);
INSERT INTO dset_ad_value3 (dset_ad_datatype_id, adxp_al_value, adxp_al_code, adxp_al_codesystem) VALUES (15, '3 Jefferson Street', 'CODE3', NULL);
INSERT INTO dset_ad_value3 (dset_ad_datatype_id, adxp_al_value, adxp_al_code, adxp_al_codesystem) VALUES (16, '4 Jefferson Street', 'CODE4', 'CODE_SYSTEM');


--
-- Data for Name: dset_ad_value4; Type: TABLE DATA; 
--

INSERT INTO dset_ad_value4 (dset_ad_datatype_id, adxp_al1_value, adxp_al2_value, adxp_al1_code, adxp_al2_code, adxp_al2_codesystem, adxp_al1_codesystem) VALUES (17, '100 Jefferson Street', NULL, NULL, NULL, NULL, NULL);
INSERT INTO dset_ad_value4 (dset_ad_datatype_id, adxp_al1_value, adxp_al2_value, adxp_al1_code, adxp_al2_code, adxp_al2_codesystem, adxp_al1_codesystem) VALUES (18, '101 Jefferson Street', NULL, 'NCI101', NULL, NULL, NULL);
INSERT INTO dset_ad_value4 (dset_ad_datatype_id, adxp_al1_value, adxp_al2_value, adxp_al1_code, adxp_al2_code, adxp_al2_codesystem, adxp_al1_codesystem) VALUES (19, '102 Jefferson Street', NULL, 'NCI102', NULL, NULL, 'ADXP_AL1_CODESYSTEM1');
INSERT INTO dset_ad_value4 (dset_ad_datatype_id, adxp_al1_value, adxp_al2_value, adxp_al1_code, adxp_al2_code, adxp_al2_codesystem, adxp_al1_codesystem) VALUES (20, NULL, '200 Executive Blvd', NULL, NULL, NULL, NULL);
INSERT INTO dset_ad_value4 (dset_ad_datatype_id, adxp_al1_value, adxp_al2_value, adxp_al1_code, adxp_al2_code, adxp_al2_codesystem, adxp_al1_codesystem) VALUES (21, NULL, '201 Executive Blvd', NULL, 'NCI201', NULL, NULL);
INSERT INTO dset_ad_value4 (dset_ad_datatype_id, adxp_al1_value, adxp_al2_value, adxp_al1_code, adxp_al2_code, adxp_al2_codesystem, adxp_al1_codesystem) VALUES (22, NULL, '202 Executive Blvd', NULL, 'NCI202', 'ADXP_AL2_CODESYSTEM1', NULL);
INSERT INTO dset_ad_value4 (dset_ad_datatype_id, adxp_al1_value, adxp_al2_value, adxp_al1_code, adxp_al2_code, adxp_al2_codesystem, adxp_al1_codesystem) VALUES (23, '100 Jefferson Street', '200 Executive Blvd', NULL, NULL, NULL, NULL);
INSERT INTO dset_ad_value4 (dset_ad_datatype_id, adxp_al1_value, adxp_al2_value, adxp_al1_code, adxp_al2_code, adxp_al2_codesystem, adxp_al1_codesystem) VALUES (24, '101 Jefferson Street', '201 Executive Blvd', 'NCI101', 'NCI201', NULL, NULL);
INSERT INTO dset_ad_value4 (dset_ad_datatype_id, adxp_al1_value, adxp_al2_value, adxp_al1_code, adxp_al2_code, adxp_al2_codesystem, adxp_al1_codesystem) VALUES (25, '102 Jefferson Street', '202 Executive Blvd', 'NCI102', 'NCI202', 'ADXP_AL2_CODESYSTEM1', 'ADXP_AL1_CODESYSTEM1');


--
-- Data for Name: dset_ad_value5; Type: TABLE DATA; 
--

INSERT INTO dset_ad_value5 (dset_ad_datatype_id, adxp_al_value, adxp_al_code, adxp_al_codesystem, adxp_dal_code, adxp_dal_codesystem, adxp_dal_value, adxp_cty_value, adxp_cty_code, adxp_cty_codesystem) VALUES (26, '100 Jefferson Street', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO dset_ad_value5 (dset_ad_datatype_id, adxp_al_value, adxp_al_code, adxp_al_codesystem, adxp_dal_code, adxp_dal_codesystem, adxp_dal_value, adxp_cty_value, adxp_cty_code, adxp_cty_codesystem) VALUES (27, '101 Jefferson Street', 'NCIAL101', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO dset_ad_value5 (dset_ad_datatype_id, adxp_al_value, adxp_al_code, adxp_al_codesystem, adxp_dal_code, adxp_dal_codesystem, adxp_dal_value, adxp_cty_value, adxp_cty_code, adxp_cty_codesystem) VALUES (28, '102 Jefferson Street', 'NCIAL102', 'ADXP_AL_CODESYSTEM1', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO dset_ad_value5 (dset_ad_datatype_id, adxp_al_value, adxp_al_code, adxp_al_codesystem, adxp_dal_code, adxp_dal_codesystem, adxp_dal_value, adxp_cty_value, adxp_cty_code, adxp_cty_codesystem) VALUES (29, '103 Jefferson Street', 'NCIAL103', 'ADXP_AL_CODESYSTEM2', 'NCIDAL100', NULL, NULL, NULL, NULL, NULL);
INSERT INTO dset_ad_value5 (dset_ad_datatype_id, adxp_al_value, adxp_al_code, adxp_al_codesystem, adxp_dal_code, adxp_dal_codesystem, adxp_dal_value, adxp_cty_value, adxp_cty_code, adxp_cty_codesystem) VALUES (30, '104 Jefferson Street', 'NCIAL104', 'ADXP_AL_CODESYSTEM3', 'NCIDAL101', 'ADXP_DAL_CODESYSTEM1', NULL, NULL, NULL, NULL);
INSERT INTO dset_ad_value5 (dset_ad_datatype_id, adxp_al_value, adxp_al_code, adxp_al_codesystem, adxp_dal_code, adxp_dal_codesystem, adxp_dal_value, adxp_cty_value, adxp_cty_code, adxp_cty_codesystem) VALUES (31, '105 Jefferson Street', 'NCIAL105', 'ADXP_AL_CODESYSTEM4', 'NCIDAL102', 'ADXP_DAL_CODESYSTEM2', 'Suite 500', NULL, NULL, NULL);
INSERT INTO dset_ad_value5 (dset_ad_datatype_id, adxp_al_value, adxp_al_code, adxp_al_codesystem, adxp_dal_code, adxp_dal_codesystem, adxp_dal_value, adxp_cty_value, adxp_cty_code, adxp_cty_codesystem) VALUES (32, '106 Jefferson Street', 'NCIAL106', 'ADXP_AL_CODESYSTEM5', 'NCIDAL103', 'ADXP_DAL_CODESYSTEM3', 'Suite 501', 'Rockville', NULL, NULL);
INSERT INTO dset_ad_value5 (dset_ad_datatype_id, adxp_al_value, adxp_al_code, adxp_al_codesystem, adxp_dal_code, adxp_dal_codesystem, adxp_dal_value, adxp_cty_value, adxp_cty_code, adxp_cty_codesystem) VALUES (33, '107 Jefferson Street', 'NCIAL107', 'ADXP_AL_CODESYSTEM6', 'NCIDAL104', 'ADXP_DAL_CODESYSTEM4', 'Suite 502', 'Rockville', 'RCK', NULL);
INSERT INTO dset_ad_value5 (dset_ad_datatype_id, adxp_al_value, adxp_al_code, adxp_al_codesystem, adxp_dal_code, adxp_dal_codesystem, adxp_dal_value, adxp_cty_value, adxp_cty_code, adxp_cty_codesystem) VALUES (34, '108 Jefferson Street', 'NCIAL108', 'ADXP_AL_CODESYSTEM7', 'NCIDAL105', 'ADXP_DAL_CODESYSTEM5', 'Suite 503', 'Rockville', 'RCK', 'RCK_CODE_SYS');
INSERT INTO dset_ad_value5 (dset_ad_datatype_id, adxp_al_value, adxp_al_code, adxp_al_codesystem, adxp_dal_code, adxp_dal_codesystem, adxp_dal_value, adxp_cty_value, adxp_cty_code, adxp_cty_codesystem) VALUES (35, '108 Jefferson Street', 'NCIAL108', 'ADXP_AL_CODESYSTEM7', 'NCIDAL105', 'ADXP_DAL_CODESYSTEM5', 'Suite 503', 'Rockville', 'RCK', 'RCK_CODE_SYS');


--
-- Data for Name: dset_ad_value6; Type: TABLE DATA; 
--

INSERT INTO dset_ad_value6 (dset_ad_datatype_id, adxp_adl_value, adxp_al_value, adxp_bnn_value, adxp_bnr_value, adxp_bns_value, adxp_car_value, adxp_cen_value, adxp_cnt_value, adxp_cpa_value, adxp_cty_value, adxp_dal_value, adxp_del_value, adxp_dinsta_value, adxp_dinstq_value, adxp_dir_value, adxp_dmod_value, adxp_dmodid_value, adxp_int_value, adxp_pob_value, adxp_pre_value, adxp_sal_value, adxp_sta_value, adxp_stb_value, adxp_str_value, adxp_sttyp_value, adxp_unid_value, adxp_unit_value, adxp_zip_value, adxp_adl_code, adxp_bns_code, adxp_bns_codesystem, adxp_dal_code, adxp_dal_codesystem, adxp_int_code, adxp_int_codesystem, adxp_stb_code, adxp_stb_codesystem, adxp_zip_codesystem, adxp_zip_code) VALUES (36, 'ADXP_ADL_VALUE1', 'ADXP_AL_VALUE1', 'ADXP_BNN_VALUE1', 'ADXP_BNR_VALUE1', 'ADXP_BNS_VALUE1', 'ADXP_CAR_VALUE1', 'ADXP_CEN_VALUE1', 'ADXP_CNT_VALUE1', 'ADXP_CPA_VALUE1', 'ADXP_CTY_VALUE1', 'ADXP_DAL_VALUE1', 'ADXP_DEL_VALUE1', 'ADXP_DINSTA_VALUE1', 'ADXP_DINSTQ_VALUE1', 'ADXP_DIR_VALUE1', 'ADXP_DMOD_VALUE1', 'ADXP_DMODID_VALUE1', 'ADXP_INT_VALUE1', 'ADXP_POB_VALUE1', 'ADXP_PRE_VALUE1', 'ADXP_SAL_VALUE1', 'ADXP_STA_VALUE1', 'ADXP_STB_VALUE1', 'ADXP_STR_VALUE1', 'ADXP_STTYP_VALUE1', 'ADXP_UNID_VALUE1', 'ADXP_UNIT_VALUE1', 'ADXP_ZIP_VALUE1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO dset_ad_value6 (dset_ad_datatype_id, adxp_adl_value, adxp_al_value, adxp_bnn_value, adxp_bnr_value, adxp_bns_value, adxp_car_value, adxp_cen_value, adxp_cnt_value, adxp_cpa_value, adxp_cty_value, adxp_dal_value, adxp_del_value, adxp_dinsta_value, adxp_dinstq_value, adxp_dir_value, adxp_dmod_value, adxp_dmodid_value, adxp_int_value, adxp_pob_value, adxp_pre_value, adxp_sal_value, adxp_sta_value, adxp_stb_value, adxp_str_value, adxp_sttyp_value, adxp_unid_value, adxp_unit_value, adxp_zip_value, adxp_adl_code, adxp_bns_code, adxp_bns_codesystem, adxp_dal_code, adxp_dal_codesystem, adxp_int_code, adxp_int_codesystem, adxp_stb_code, adxp_stb_codesystem, adxp_zip_codesystem, adxp_zip_code) VALUES (37, 'ADXP_ADL_VALUE2', 'ADXP_AL_VALUE2', 'ADXP_BNN_VALUE2', 'ADXP_BNR_VALUE2', 'ADXP_BNS_VALUE2', 'ADXP_CAR_VALUE2', 'ADXP_CEN_VALUE2', 'ADXP_CNT_VALUE2', 'ADXP_CPA_VALUE2', 'ADXP_CTY_VALUE2', 'ADXP_DAL_VALUE2', 'ADXP_DEL_VALUE2', 'ADXP_DINSTA_VALUE2', 'ADXP_DINSTQ_VALUE2', 'ADXP_DIR_VALUE2', 'ADXP_DMOD_VALUE2', 'ADXP_DMODID_VALUE2', 'ADXP_INT_VALUE2', 'ADXP_POB_VALUE2', 'ADXP_PRE_VALUE2', 'ADXP_SAL_VALUE2', 'ADXP_STA_VALUE2', 'ADXP_STB_VALUE2', 'ADXP_STR_VALUE2', 'ADXP_STTYP_VALUE2', 'ADXP_UNID_VALUE2', 'ADXP_UNIT_VALUE2', 'ADXP_ZIP_VALUE2', 'ADXP_ADL_CODE', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO dset_ad_value6 (dset_ad_datatype_id, adxp_adl_value, adxp_al_value, adxp_bnn_value, adxp_bnr_value, adxp_bns_value, adxp_car_value, adxp_cen_value, adxp_cnt_value, adxp_cpa_value, adxp_cty_value, adxp_dal_value, adxp_del_value, adxp_dinsta_value, adxp_dinstq_value, adxp_dir_value, adxp_dmod_value, adxp_dmodid_value, adxp_int_value, adxp_pob_value, adxp_pre_value, adxp_sal_value, adxp_sta_value, adxp_stb_value, adxp_str_value, adxp_sttyp_value, adxp_unid_value, adxp_unit_value, adxp_zip_value, adxp_adl_code, adxp_bns_code, adxp_bns_codesystem, adxp_dal_code, adxp_dal_codesystem, adxp_int_code, adxp_int_codesystem, adxp_stb_code, adxp_stb_codesystem, adxp_zip_codesystem, adxp_zip_code) VALUES (38, 'ADXP_ADL_VALUE3', 'ADXP_AL_VALUE3', 'ADXP_BNN_VALUE3', 'ADXP_BNR_VALUE3', 'ADXP_BNS_VALUE3', 'ADXP_CAR_VALUE3', 'ADXP_CEN_VALUE3', 'ADXP_CNT_VALUE3', 'ADXP_CPA_VALUE3', 'ADXP_CTY_VALUE3', 'ADXP_DAL_VALUE3', 'ADXP_DEL_VALUE3', 'ADXP_DINSTA_VALUE3', 'ADXP_DINSTQ_VALUE3', 'ADXP_DIR_VALUE3', 'ADXP_DMOD_VALUE3', 'ADXP_DMODID_VALUE3', 'ADXP_INT_VALUE3', 'ADXP_POB_VALUE3', 'ADXP_PRE_VALUE3', 'ADXP_SAL_VALUE3', 'ADXP_STA_VALUE3', 'ADXP_STB_VALUE3', 'ADXP_STR_VALUE3', 'ADXP_STTYP_VALUE3', 'ADXP_UNID_VALUE3', 'ADXP_UNIT_VALUE3', 'ADXP_ZIP_VALUE3', 'ADXP_ADL_CODE', 'ADXP_BNS_CODE', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO dset_ad_value6 (dset_ad_datatype_id, adxp_adl_value, adxp_al_value, adxp_bnn_value, adxp_bnr_value, adxp_bns_value, adxp_car_value, adxp_cen_value, adxp_cnt_value, adxp_cpa_value, adxp_cty_value, adxp_dal_value, adxp_del_value, adxp_dinsta_value, adxp_dinstq_value, adxp_dir_value, adxp_dmod_value, adxp_dmodid_value, adxp_int_value, adxp_pob_value, adxp_pre_value, adxp_sal_value, adxp_sta_value, adxp_stb_value, adxp_str_value, adxp_sttyp_value, adxp_unid_value, adxp_unit_value, adxp_zip_value, adxp_adl_code, adxp_bns_code, adxp_bns_codesystem, adxp_dal_code, adxp_dal_codesystem, adxp_int_code, adxp_int_codesystem, adxp_stb_code, adxp_stb_codesystem, adxp_zip_codesystem, adxp_zip_code) VALUES (39, 'ADXP_ADL_VALUE4', 'ADXP_AL_VALUE4', 'ADXP_BNN_VALUE4', 'ADXP_BNR_VALUE4', 'ADXP_BNS_VALUE4', 'ADXP_CAR_VALUE4', 'ADXP_CEN_VALUE4', 'ADXP_CNT_VALUE4', 'ADXP_CPA_VALUE4', 'ADXP_CTY_VALUE4', 'ADXP_DAL_VALUE4', 'ADXP_DEL_VALUE4', 'ADXP_DINSTA_VALUE4', 'ADXP_DINSTQ_VALUE4', 'ADXP_DIR_VALUE4', 'ADXP_DMOD_VALUE4', 'ADXP_DMODID_VALUE4', 'ADXP_INT_VALUE4', 'ADXP_POB_VALUE4', 'ADXP_PRE_VALUE4', 'ADXP_SAL_VALUE4', 'ADXP_STA_VALUE4', 'ADXP_STB_VALUE4', 'ADXP_STR_VALUE4', 'ADXP_STTYP_VALUE4', 'ADXP_UNID_VALUE4', 'ADXP_UNIT_VALUE4', 'ADXP_ZIP_VALUE4', 'ADXP_ADL_CODE', 'ADXP_BNS_CODE', 'ADXP_BNS_CODESYSTEM', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO dset_ad_value6 (dset_ad_datatype_id, adxp_adl_value, adxp_al_value, adxp_bnn_value, adxp_bnr_value, adxp_bns_value, adxp_car_value, adxp_cen_value, adxp_cnt_value, adxp_cpa_value, adxp_cty_value, adxp_dal_value, adxp_del_value, adxp_dinsta_value, adxp_dinstq_value, adxp_dir_value, adxp_dmod_value, adxp_dmodid_value, adxp_int_value, adxp_pob_value, adxp_pre_value, adxp_sal_value, adxp_sta_value, adxp_stb_value, adxp_str_value, adxp_sttyp_value, adxp_unid_value, adxp_unit_value, adxp_zip_value, adxp_adl_code, adxp_bns_code, adxp_bns_codesystem, adxp_dal_code, adxp_dal_codesystem, adxp_int_code, adxp_int_codesystem, adxp_stb_code, adxp_stb_codesystem, adxp_zip_codesystem, adxp_zip_code) VALUES (40, 'ADXP_ADL_VALUE5', 'ADXP_AL_VALUE5', 'ADXP_BNN_VALUE5', 'ADXP_BNR_VALUE5', 'ADXP_BNS_VALUE5', 'ADXP_CAR_VALUE5', 'ADXP_CEN_VALUE5', 'ADXP_CNT_VALUE5', 'ADXP_CPA_VALUE5', 'ADXP_CTY_VALUE5', 'ADXP_DAL_VALUE5', 'ADXP_DEL_VALUE5', 'ADXP_DINSTA_VALUE5', 'ADXP_DINSTQ_VALUE5', 'ADXP_DIR_VALUE5', 'ADXP_DMOD_VALUE5', 'ADXP_DMODID_VALUE5', 'ADXP_INT_VALUE5', 'ADXP_POB_VALUE5', 'ADXP_PRE_VALUE5', 'ADXP_SAL_VALUE5', 'ADXP_STA_VALUE5', 'ADXP_STB_VALUE5', 'ADXP_STR_VALUE5', 'ADXP_STTYP_VALUE5', 'ADXP_UNID_VALUE5', 'ADXP_UNIT_VALUE5', 'ADXP_ZIP_VALUE5', 'ADXP_ADL_CODE', 'ADXP_BNS_CODE', 'ADXP_BNS_CODESYSTEM', 'ADXP_DAL_CODE', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO dset_ad_value6 (dset_ad_datatype_id, adxp_adl_value, adxp_al_value, adxp_bnn_value, adxp_bnr_value, adxp_bns_value, adxp_car_value, adxp_cen_value, adxp_cnt_value, adxp_cpa_value, adxp_cty_value, adxp_dal_value, adxp_del_value, adxp_dinsta_value, adxp_dinstq_value, adxp_dir_value, adxp_dmod_value, adxp_dmodid_value, adxp_int_value, adxp_pob_value, adxp_pre_value, adxp_sal_value, adxp_sta_value, adxp_stb_value, adxp_str_value, adxp_sttyp_value, adxp_unid_value, adxp_unit_value, adxp_zip_value, adxp_adl_code, adxp_bns_code, adxp_bns_codesystem, adxp_dal_code, adxp_dal_codesystem, adxp_int_code, adxp_int_codesystem, adxp_stb_code, adxp_stb_codesystem, adxp_zip_codesystem, adxp_zip_code) VALUES (41, 'ADXP_ADL_VALUE6', 'ADXP_AL_VALUE6', 'ADXP_BNN_VALUE6', 'ADXP_BNR_VALUE6', 'ADXP_BNS_VALUE6', 'ADXP_CAR_VALUE6', 'ADXP_CEN_VALUE6', 'ADXP_CNT_VALUE6', 'ADXP_CPA_VALUE6', 'ADXP_CTY_VALUE6', 'ADXP_DAL_VALUE6', 'ADXP_DEL_VALUE6', 'ADXP_DINSTA_VALUE6', 'ADXP_DINSTQ_VALUE6', 'ADXP_DIR_VALUE6', 'ADXP_DMOD_VALUE6', 'ADXP_DMODID_VALUE6', 'ADXP_INT_VALUE6', 'ADXP_POB_VALUE6', 'ADXP_PRE_VALUE6', 'ADXP_SAL_VALUE6', 'ADXP_STA_VALUE6', 'ADXP_STB_VALUE6', 'ADXP_STR_VALUE6', 'ADXP_STTYP_VALUE6', 'ADXP_UNID_VALUE6', 'ADXP_UNIT_VALUE6', 'ADXP_ZIP_VALUE6', 'ADXP_ADL_CODE', 'ADXP_BNS_CODE', 'ADXP_BNS_CODESYSTEM', 'ADXP_DAL_CODE', 'ADXP_DAL_CODESYSTEM', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO dset_ad_value6 (dset_ad_datatype_id, adxp_adl_value, adxp_al_value, adxp_bnn_value, adxp_bnr_value, adxp_bns_value, adxp_car_value, adxp_cen_value, adxp_cnt_value, adxp_cpa_value, adxp_cty_value, adxp_dal_value, adxp_del_value, adxp_dinsta_value, adxp_dinstq_value, adxp_dir_value, adxp_dmod_value, adxp_dmodid_value, adxp_int_value, adxp_pob_value, adxp_pre_value, adxp_sal_value, adxp_sta_value, adxp_stb_value, adxp_str_value, adxp_sttyp_value, adxp_unid_value, adxp_unit_value, adxp_zip_value, adxp_adl_code, adxp_bns_code, adxp_bns_codesystem, adxp_dal_code, adxp_dal_codesystem, adxp_int_code, adxp_int_codesystem, adxp_stb_code, adxp_stb_codesystem, adxp_zip_codesystem, adxp_zip_code) VALUES (42, 'ADXP_ADL_VALUE7', 'ADXP_AL_VALUE7', 'ADXP_BNN_VALUE7', 'ADXP_BNR_VALUE7', 'ADXP_BNS_VALUE7', 'ADXP_CAR_VALUE7', 'ADXP_CEN_VALUE7', 'ADXP_CNT_VALUE7', 'ADXP_CPA_VALUE7', 'ADXP_CTY_VALUE7', 'ADXP_DAL_VALUE7', 'ADXP_DEL_VALUE7', 'ADXP_DINSTA_VALUE7', 'ADXP_DINSTQ_VALUE7', 'ADXP_DIR_VALUE7', 'ADXP_DMOD_VALUE7', 'ADXP_DMODID_VALUE7', 'ADXP_INT_VALUE7', 'ADXP_POB_VALUE7', 'ADXP_PRE_VALUE7', 'ADXP_SAL_VALUE7', 'ADXP_STA_VALUE7', 'ADXP_STB_VALUE7', 'ADXP_STR_VALUE7', 'ADXP_STTYP_VALUE7', 'ADXP_UNID_VALUE7', 'ADXP_UNIT_VALUE7', 'ADXP_ZIP_VALUE7', 'ADXP_ADL_CODE', 'ADXP_BNS_CODE', 'ADXP_BNS_CODESYSTEM', 'ADXP_DAL_CODE', 'ADXP_DAL_CODESYSTEM', 'ADXP_INT_CODE', NULL, NULL, NULL, NULL, NULL);
INSERT INTO dset_ad_value6 (dset_ad_datatype_id, adxp_adl_value, adxp_al_value, adxp_bnn_value, adxp_bnr_value, adxp_bns_value, adxp_car_value, adxp_cen_value, adxp_cnt_value, adxp_cpa_value, adxp_cty_value, adxp_dal_value, adxp_del_value, adxp_dinsta_value, adxp_dinstq_value, adxp_dir_value, adxp_dmod_value, adxp_dmodid_value, adxp_int_value, adxp_pob_value, adxp_pre_value, adxp_sal_value, adxp_sta_value, adxp_stb_value, adxp_str_value, adxp_sttyp_value, adxp_unid_value, adxp_unit_value, adxp_zip_value, adxp_adl_code, adxp_bns_code, adxp_bns_codesystem, adxp_dal_code, adxp_dal_codesystem, adxp_int_code, adxp_int_codesystem, adxp_stb_code, adxp_stb_codesystem, adxp_zip_codesystem, adxp_zip_code) VALUES (43, 'ADXP_ADL_VALUE8', 'ADXP_AL_VALUE8', 'ADXP_BNN_VALUE8', 'ADXP_BNR_VALUE8', 'ADXP_BNS_VALUE8', 'ADXP_CAR_VALUE8', 'ADXP_CEN_VALUE8', 'ADXP_CNT_VALUE8', 'ADXP_CPA_VALUE8', 'ADXP_CTY_VALUE8', 'ADXP_DAL_VALUE8', 'ADXP_DEL_VALUE8', 'ADXP_DINSTA_VALUE8', 'ADXP_DINSTQ_VALUE8', 'ADXP_DIR_VALUE8', 'ADXP_DMOD_VALUE8', 'ADXP_DMODID_VALUE8', 'ADXP_INT_VALUE8', 'ADXP_POB_VALUE8', 'ADXP_PRE_VALUE8', 'ADXP_SAL_VALUE8', 'ADXP_STA_VALUE8', 'ADXP_STB_VALUE8', 'ADXP_STR_VALUE8', 'ADXP_STTYP_VALUE8', 'ADXP_UNID_VALUE8', 'ADXP_UNIT_VALUE8', 'ADXP_ZIP_VALUE8', 'ADXP_ADL_CODE', 'ADXP_BNS_CODE', 'ADXP_BNS_CODESYSTEM', 'ADXP_DAL_CODE', 'ADXP_DAL_CODESYSTEM', 'ADXP_INT_CODE', 'ADXP_INT_CODESYSTEM', NULL, NULL, NULL, NULL);
INSERT INTO dset_ad_value6 (dset_ad_datatype_id, adxp_adl_value, adxp_al_value, adxp_bnn_value, adxp_bnr_value, adxp_bns_value, adxp_car_value, adxp_cen_value, adxp_cnt_value, adxp_cpa_value, adxp_cty_value, adxp_dal_value, adxp_del_value, adxp_dinsta_value, adxp_dinstq_value, adxp_dir_value, adxp_dmod_value, adxp_dmodid_value, adxp_int_value, adxp_pob_value, adxp_pre_value, adxp_sal_value, adxp_sta_value, adxp_stb_value, adxp_str_value, adxp_sttyp_value, adxp_unid_value, adxp_unit_value, adxp_zip_value, adxp_adl_code, adxp_bns_code, adxp_bns_codesystem, adxp_dal_code, adxp_dal_codesystem, adxp_int_code, adxp_int_codesystem, adxp_stb_code, adxp_stb_codesystem, adxp_zip_codesystem, adxp_zip_code) VALUES (44, 'ADXP_ADL_VALUE9', 'ADXP_AL_VALUE9', 'ADXP_BNN_VALUE9', 'ADXP_BNR_VALUE9', 'ADXP_BNS_VALUE9', 'ADXP_CAR_VALUE9', 'ADXP_CEN_VALUE9', 'ADXP_CNT_VALUE9', 'ADXP_CPA_VALUE9', 'ADXP_CTY_VALUE9', 'ADXP_DAL_VALUE9', 'ADXP_DEL_VALUE9', 'ADXP_DINSTA_VALUE9', 'ADXP_DINSTQ_VALUE9', 'ADXP_DIR_VALUE9', 'ADXP_DMOD_VALUE9', 'ADXP_DMODID_VALUE9', 'ADXP_INT_VALUE9', 'ADXP_POB_VALUE9', 'ADXP_PRE_VALUE9', 'ADXP_SAL_VALUE9', 'ADXP_STA_VALUE9', 'ADXP_STB_VALUE9', 'ADXP_STR_VALUE9', 'ADXP_STTYP_VALUE9', 'ADXP_UNID_VALUE9', 'ADXP_UNIT_VALUE9', 'ADXP_ZIP_VALUE9', 'ADXP_ADL_CODE', 'ADXP_BNS_CODE', 'ADXP_BNS_CODESYSTEM', 'ADXP_DAL_CODE', 'ADXP_DAL_CODESYSTEM', 'ADXP_INT_CODE', 'ADXP_INT_CODESYSTEM', 'ADXP_STB_CODE', NULL, NULL, NULL);
INSERT INTO dset_ad_value6 (dset_ad_datatype_id, adxp_adl_value, adxp_al_value, adxp_bnn_value, adxp_bnr_value, adxp_bns_value, adxp_car_value, adxp_cen_value, adxp_cnt_value, adxp_cpa_value, adxp_cty_value, adxp_dal_value, adxp_del_value, adxp_dinsta_value, adxp_dinstq_value, adxp_dir_value, adxp_dmod_value, adxp_dmodid_value, adxp_int_value, adxp_pob_value, adxp_pre_value, adxp_sal_value, adxp_sta_value, adxp_stb_value, adxp_str_value, adxp_sttyp_value, adxp_unid_value, adxp_unit_value, adxp_zip_value, adxp_adl_code, adxp_bns_code, adxp_bns_codesystem, adxp_dal_code, adxp_dal_codesystem, adxp_int_code, adxp_int_codesystem, adxp_stb_code, adxp_stb_codesystem, adxp_zip_codesystem, adxp_zip_code) VALUES (45, 'ADXP_ADL_VALUE10', 'ADXP_AL_VALUE10', 'ADXP_BNN_VALUE10', 'ADXP_BNR_VALUE10', 'ADXP_BNS_VALUE10', 'ADXP_CAR_VALUE10', 'ADXP_CEN_VALUE10', 'ADXP_CNT_VALUE10', 'ADXP_CPA_VALUE10', 'ADXP_CTY_VALUE10', 'ADXP_DAL_VALUE10', 'ADXP_DEL_VALUE10', 'ADXP_DINSTA_VALUE10', 'ADXP_DINSTQ_VALUE10', 'ADXP_DIR_VALUE10', 'ADXP_DMOD_VALUE10', 'ADXP_DMODID_VALUE10', 'ADXP_INT_VALUE10', 'ADXP_POB_VALUE10', 'ADXP_PRE_VALUE10', 'ADXP_SAL_VALUE10', 'ADXP_STA_VALUE10', 'ADXP_STB_VALUE10', 'ADXP_STR_VALUE10', 'ADXP_STTYP_VALUE10', 'ADXP_UNID_VALUE10', 'ADXP_UNIT_VALUE10', 'ADXP_ZIP_VALUE10', 'ADXP_ADL_CODE', 'ADXP_BNS_CODE', 'ADXP_BNS_CODESYSTEM', 'ADXP_DAL_CODE', 'ADXP_DAL_CODESYSTEM', 'ADXP_INT_CODE', 'ADXP_INT_CODESYSTEM', 'ADXP_STB_CODE', 'ADXP_STB_CODESYSTEM', NULL, NULL);
INSERT INTO dset_ad_value6 (dset_ad_datatype_id, adxp_adl_value, adxp_al_value, adxp_bnn_value, adxp_bnr_value, adxp_bns_value, adxp_car_value, adxp_cen_value, adxp_cnt_value, adxp_cpa_value, adxp_cty_value, adxp_dal_value, adxp_del_value, adxp_dinsta_value, adxp_dinstq_value, adxp_dir_value, adxp_dmod_value, adxp_dmodid_value, adxp_int_value, adxp_pob_value, adxp_pre_value, adxp_sal_value, adxp_sta_value, adxp_stb_value, adxp_str_value, adxp_sttyp_value, adxp_unid_value, adxp_unit_value, adxp_zip_value, adxp_adl_code, adxp_bns_code, adxp_bns_codesystem, adxp_dal_code, adxp_dal_codesystem, adxp_int_code, adxp_int_codesystem, adxp_stb_code, adxp_stb_codesystem, adxp_zip_codesystem, adxp_zip_code) VALUES (46, 'ADXP_ADL_VALUE11', 'ADXP_AL_VALUE11', 'ADXP_BNN_VALUE11', 'ADXP_BNR_VALUE11', 'ADXP_BNS_VALUE11', 'ADXP_CAR_VALUE11', 'ADXP_CEN_VALUE11', 'ADXP_CNT_VALUE11', 'ADXP_CPA_VALUE11', 'ADXP_CTY_VALUE11', 'ADXP_DAL_VALUE11', 'ADXP_DEL_VALUE11', 'ADXP_DINSTA_VALUE11', 'ADXP_DINSTQ_VALUE11', 'ADXP_DIR_VALUE11', 'ADXP_DMOD_VALUE11', 'ADXP_DMODID_VALUE11', 'ADXP_INT_VALUE11', 'ADXP_POB_VALUE11', 'ADXP_PRE_VALUE11', 'ADXP_SAL_VALUE11', 'ADXP_STA_VALUE11', 'ADXP_STB_VALUE11', 'ADXP_STR_VALUE11', 'ADXP_STTYP_VALUE11', 'ADXP_UNID_VALUE11', 'ADXP_UNIT_VALUE11', 'ADXP_ZIP_VALUE11', 'ADXP_ADL_CODE', 'ADXP_BNS_CODE', 'ADXP_BNS_CODESYSTEM', 'ADXP_DAL_CODE', 'ADXP_DAL_CODESYSTEM', 'ADXP_INT_CODE', 'ADXP_INT_CODESYSTEM', 'ADXP_STB_CODE', 'ADXP_STB_CODESYSTEM', 'ADXP_ZIP_CODESYSTEM', NULL);
INSERT INTO dset_ad_value6 (dset_ad_datatype_id, adxp_adl_value, adxp_al_value, adxp_bnn_value, adxp_bnr_value, adxp_bns_value, adxp_car_value, adxp_cen_value, adxp_cnt_value, adxp_cpa_value, adxp_cty_value, adxp_dal_value, adxp_del_value, adxp_dinsta_value, adxp_dinstq_value, adxp_dir_value, adxp_dmod_value, adxp_dmodid_value, adxp_int_value, adxp_pob_value, adxp_pre_value, adxp_sal_value, adxp_sta_value, adxp_stb_value, adxp_str_value, adxp_sttyp_value, adxp_unid_value, adxp_unit_value, adxp_zip_value, adxp_adl_code, adxp_bns_code, adxp_bns_codesystem, adxp_dal_code, adxp_dal_codesystem, adxp_int_code, adxp_int_codesystem, adxp_stb_code, adxp_stb_codesystem, adxp_zip_codesystem, adxp_zip_code) VALUES (47, 'ADXP_ADL_VALUE12', 'ADXP_AL_VALUE12', 'ADXP_BNN_VALUE12', 'ADXP_BNR_VALUE12', 'ADXP_BNS_VALUE12', 'ADXP_CAR_VALUE12', 'ADXP_CEN_VALUE12', 'ADXP_CNT_VALUE12', 'ADXP_CPA_VALUE12', 'ADXP_CTY_VALUE12', 'ADXP_DAL_VALUE12', 'ADXP_DEL_VALUE12', 'ADXP_DINSTA_VALUE12', 'ADXP_DINSTQ_VALUE12', 'ADXP_DIR_VALUE12', 'ADXP_DMOD_VALUE12', 'ADXP_DMODID_VALUE12', 'ADXP_INT_VALUE12', 'ADXP_POB_VALUE12', 'ADXP_PRE_VALUE12', 'ADXP_SAL_VALUE12', 'ADXP_STA_VALUE12', 'ADXP_STB_VALUE12', 'ADXP_STR_VALUE12', 'ADXP_STTYP_VALUE12', 'ADXP_UNID_VALUE12', 'ADXP_UNIT_VALUE12', 'ADXP_ZIP_VALUE12', 'ADXP_ADL_CODE', 'ADXP_BNS_CODE', 'ADXP_BNS_CODESYSTEM', 'ADXP_DAL_CODE', 'ADXP_DAL_CODESYSTEM', 'ADXP_INT_CODE', 'ADXP_INT_CODESYSTEM', 'ADXP_STB_CODE', 'ADXP_STB_CODESYSTEM', 'ADXP_ZIP_CODESYSTEM', 'ADXP_ZIP_CODE');


--
-- TOC entry 2243 (class 0 OID 18560)
-- Dependencies: 1669
-- Data for Name: dset_ad_value7; Type: TABLE DATA; 
--

INSERT INTO dset_ad_value7 (id, dset_ad_datatype_id, adxp_al_value, adxp_al_code, adxp_al_codesystem, adxp_dal_code, adxp_dal_codesystem, adxp_dal_value, adxp_cty_value, adxp_cty_code, adxp_cty_codesystem) VALUES (1, 49, '100 Jefferson Street', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO dset_ad_value7 (id, dset_ad_datatype_id, adxp_al_value, adxp_al_code, adxp_al_codesystem, adxp_dal_code, adxp_dal_codesystem, adxp_dal_value, adxp_cty_value, adxp_cty_code, adxp_cty_codesystem) VALUES (2, 50, '101 Jefferson Street', 'NCIAL101', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO dset_ad_value7 (id, dset_ad_datatype_id, adxp_al_value, adxp_al_code, adxp_al_codesystem, adxp_dal_code, adxp_dal_codesystem, adxp_dal_value, adxp_cty_value, adxp_cty_code, adxp_cty_codesystem) VALUES (3, 51, '102 Jefferson Street', 'NCIAL102', 'ADXP_AL_CODESYSTEM1', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO dset_ad_value7 (id, dset_ad_datatype_id, adxp_al_value, adxp_al_code, adxp_al_codesystem, adxp_dal_code, adxp_dal_codesystem, adxp_dal_value, adxp_cty_value, adxp_cty_code, adxp_cty_codesystem) VALUES (4, 52, '103 Jefferson Street', 'NCIAL103', 'ADXP_AL_CODESYSTEM2', 'NCIDAL101', NULL, NULL, NULL, NULL, NULL);
INSERT INTO dset_ad_value7 (id, dset_ad_datatype_id, adxp_al_value, adxp_al_code, adxp_al_codesystem, adxp_dal_code, adxp_dal_codesystem, adxp_dal_value, adxp_cty_value, adxp_cty_code, adxp_cty_codesystem) VALUES (5, 53, '104 Jefferson Street', 'NCIAL104', 'ADXP_AL_CODESYSTEM3', 'NCIDAL102', 'ADXP_DAL_CODESYSTEM5', NULL, NULL, NULL, NULL);
INSERT INTO dset_ad_value7 (id, dset_ad_datatype_id, adxp_al_value, adxp_al_code, adxp_al_codesystem, adxp_dal_code, adxp_dal_codesystem, adxp_dal_value, adxp_cty_value, adxp_cty_code, adxp_cty_codesystem) VALUES (6, 54, '105 Jefferson Street', 'NCIAL105', 'ADXP_AL_CODESYSTEM4', 'NCIDAL103', 'ADXP_DAL_CODESYSTEM5', 'Suite 501', NULL, NULL, NULL);
INSERT INTO dset_ad_value7 (id, dset_ad_datatype_id, adxp_al_value, adxp_al_code, adxp_al_codesystem, adxp_dal_code, adxp_dal_codesystem, adxp_dal_value, adxp_cty_value, adxp_cty_code, adxp_cty_codesystem) VALUES (7, 55, '106 Jefferson Street', 'NCIAL106', 'ADXP_AL_CODESYSTEM5', 'NCIDAL104', 'ADXP_DAL_CODESYSTEM5', 'Suite 502', 'Rockville', NULL, NULL);
INSERT INTO dset_ad_value7 (id, dset_ad_datatype_id, adxp_al_value, adxp_al_code, adxp_al_codesystem, adxp_dal_code, adxp_dal_codesystem, adxp_dal_value, adxp_cty_value, adxp_cty_code, adxp_cty_codesystem) VALUES (8, 56, '107 Jefferson Street', 'NCIAL107', 'ADXP_AL_CODESYSTEM6', 'NCIDAL105', 'ADXP_DAL_CODESYSTEM5', 'Suite 503', 'Rockville', 'RCK', NULL);
INSERT INTO dset_ad_value7 (id, dset_ad_datatype_id, adxp_al_value, adxp_al_code, adxp_al_codesystem, adxp_dal_code, adxp_dal_codesystem, adxp_dal_value, adxp_cty_value, adxp_cty_code, adxp_cty_codesystem) VALUES (9, 57, '108 Jefferson Street', 'NCIAL108', 'ADXP_AL_CODESYSTEM7', 'NCIDAL106', 'ADXP_DAL_CODESYSTEM5', 'Suite 504', 'Rockville', 'RCK', 'RCK_CODE_SYS');
INSERT INTO dset_ad_value7 (id, dset_ad_datatype_id, adxp_al_value, adxp_al_code, adxp_al_codesystem, adxp_dal_code, adxp_dal_codesystem, adxp_dal_value, adxp_cty_value, adxp_cty_code, adxp_cty_codesystem) VALUES (10, 58, '109 Jefferson Street', 'NCIAL108', 'ADXP_AL_CODESYSTEM8', 'NCIDAL107', 'ADXP_DAL_CODESYSTEM5', 'Suite 505', 'Rockville', 'RCK', 'RCK_CODE_SYS');


--
-- TOC entry 2244 (class 0 OID 18563)
-- Dependencies: 1670
-- Data for Name: dset_ad_value8; Type: TABLE DATA; 
--

INSERT INTO dset_ad_value8 (id, adxp_al_value, adxp_al_code, adxp_al_codesystem, adxp_dal_code, adxp_dal_codesystem, adxp_dal_value, adxp_cty_value, adxp_cty_code, adxp_cty_codesystem) VALUES (1, '100 Jefferson Street', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO dset_ad_value8 (id, adxp_al_value, adxp_al_code, adxp_al_codesystem, adxp_dal_code, adxp_dal_codesystem, adxp_dal_value, adxp_cty_value, adxp_cty_code, adxp_cty_codesystem) VALUES (2, '101 Jefferson Street', 'NCIAL101', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO dset_ad_value8 (id, adxp_al_value, adxp_al_code, adxp_al_codesystem, adxp_dal_code, adxp_dal_codesystem, adxp_dal_value, adxp_cty_value, adxp_cty_code, adxp_cty_codesystem) VALUES (3, '102 Jefferson Street', 'NCIAL102', 'ADXP_AL_CODESYSTEM1', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO dset_ad_value8 (id, adxp_al_value, adxp_al_code, adxp_al_codesystem, adxp_dal_code, adxp_dal_codesystem, adxp_dal_value, adxp_cty_value, adxp_cty_code, adxp_cty_codesystem) VALUES (4, '103 Jefferson Street', 'NCIAL103', 'ADXP_AL_CODESYSTEM2', 'NCIDAL100', NULL, NULL, NULL, NULL, NULL);
INSERT INTO dset_ad_value8 (id, adxp_al_value, adxp_al_code, adxp_al_codesystem, adxp_dal_code, adxp_dal_codesystem, adxp_dal_value, adxp_cty_value, adxp_cty_code, adxp_cty_codesystem) VALUES (5, '104 Jefferson Street', 'NCIAL104', 'ADXP_AL_CODESYSTEM3', 'NCIDAL101', 'ADXP_DAL_CODESYSTEM1', NULL, NULL, NULL, NULL);
INSERT INTO dset_ad_value8 (id, adxp_al_value, adxp_al_code, adxp_al_codesystem, adxp_dal_code, adxp_dal_codesystem, adxp_dal_value, adxp_cty_value, adxp_cty_code, adxp_cty_codesystem) VALUES (6, '105 Jefferson Street', 'NCIAL105', 'ADXP_AL_CODESYSTEM4', 'NCIDAL102', 'ADXP_DAL_CODESYSTEM2', 'Suite 500', NULL, NULL, NULL);
INSERT INTO dset_ad_value8 (id, adxp_al_value, adxp_al_code, adxp_al_codesystem, adxp_dal_code, adxp_dal_codesystem, adxp_dal_value, adxp_cty_value, adxp_cty_code, adxp_cty_codesystem) VALUES (7, '106 Jefferson Street', 'NCIAL106', 'ADXP_AL_CODESYSTEM5', 'NCIDAL103', 'ADXP_DAL_CODESYSTEM3', 'Suite 501', 'Rockville', NULL, NULL);
INSERT INTO dset_ad_value8 (id, adxp_al_value, adxp_al_code, adxp_al_codesystem, adxp_dal_code, adxp_dal_codesystem, adxp_dal_value, adxp_cty_value, adxp_cty_code, adxp_cty_codesystem) VALUES (8, '107 Jefferson Street', 'NCIAL107', 'ADXP_AL_CODESYSTEM6', 'NCIDAL104', 'ADXP_DAL_CODESYSTEM4', 'Suite 502', 'Rockville', 'RCK', NULL);
INSERT INTO dset_ad_value8 (id, adxp_al_value, adxp_al_code, adxp_al_codesystem, adxp_dal_code, adxp_dal_codesystem, adxp_dal_value, adxp_cty_value, adxp_cty_code, adxp_cty_codesystem) VALUES (9, '108 Jefferson Street', 'NCIAL108', 'ADXP_AL_CODESYSTEM7', 'NCIDAL105', 'ADXP_DAL_CODESYSTEM5', 'Suite 503', 'Rockville', 'RCK', 'RCK_CODE_SYS');
INSERT INTO dset_ad_value8 (id, adxp_al_value, adxp_al_code, adxp_al_codesystem, adxp_dal_code, adxp_dal_codesystem, adxp_dal_value, adxp_cty_value, adxp_cty_code, adxp_cty_codesystem) VALUES (10, '108 Jefferson Street', 'NCIAL108', 'ADXP_AL_CODESYSTEM7', 'NCIDAL105', 'ADXP_DAL_CODESYSTEM5', 'Suite 503', 'Rockville', 'RCK', 'RCK_CODE_SYS');


--
-- TOC entry 2245 (class 0 OID 18566)
-- Dependencies: 1671
-- Data for Name: dset_cd; Type: TABLE DATA; 
--

INSERT INTO dset_cd (id) VALUES (1);
INSERT INTO dset_cd (id) VALUES (2);
INSERT INTO dset_cd (id) VALUES (3);
INSERT INTO dset_cd (id) VALUES (4);
INSERT INTO dset_cd (id) VALUES (5);
INSERT INTO dset_cd (id) VALUES (6);
INSERT INTO dset_cd (id) VALUES (7);
INSERT INTO dset_cd (id) VALUES (8);
INSERT INTO dset_cd (id) VALUES (9);
INSERT INTO dset_cd (id) VALUES (10);
INSERT INTO dset_cd (id) VALUES (11);
INSERT INTO dset_cd (id) VALUES (12);
INSERT INTO dset_cd (id) VALUES (13);
INSERT INTO dset_cd (id) VALUES (14);
INSERT INTO dset_cd (id) VALUES (15);
INSERT INTO dset_cd (id) VALUES (16);
INSERT INTO dset_cd (id) VALUES (17);
INSERT INTO dset_cd (id) VALUES (18);
INSERT INTO dset_cd (id) VALUES (19);
INSERT INTO dset_cd (id) VALUES (20);
INSERT INTO dset_cd (id) VALUES (21);
INSERT INTO dset_cd (id) VALUES (22);
INSERT INTO dset_cd (id) VALUES (23);
INSERT INTO dset_cd (id) VALUES (24);
INSERT INTO dset_cd (id) VALUES (25);
INSERT INTO dset_cd (id) VALUES (26);
INSERT INTO dset_cd (id) VALUES (27);
INSERT INTO dset_cd (id) VALUES (28);
INSERT INTO dset_cd (id) VALUES (29);
INSERT INTO dset_cd (id) VALUES (30);
INSERT INTO dset_cd (id) VALUES (31);
INSERT INTO dset_cd (id) VALUES (32);
INSERT INTO dset_cd (id) VALUES (33);
INSERT INTO dset_cd (id) VALUES (34);
INSERT INTO dset_cd (id) VALUES (35);


--
-- TOC entry 2246 (class 0 OID 18569)
-- Dependencies: 1672
-- Data for Name: dset_cd_cd_value7; Type: TABLE DATA; 
--

INSERT INTO dset_cd_cd_value7 (dset_cd_id, dset_cd_value7_id) VALUES (31, 1);
INSERT INTO dset_cd_cd_value7 (dset_cd_id, dset_cd_value7_id) VALUES (32, 2);
INSERT INTO dset_cd_cd_value7 (dset_cd_id, dset_cd_value7_id) VALUES (33, 3);
INSERT INTO dset_cd_cd_value7 (dset_cd_id, dset_cd_value7_id) VALUES (34, 4);
INSERT INTO dset_cd_cd_value7 (dset_cd_id, dset_cd_value7_id) VALUES (35, 5);


--
-- TOC entry 2247 (class 0 OID 18572)
-- Dependencies: 1673
-- Data for Name: dset_cd_value1; Type: TABLE DATA; 
--

INSERT INTO dset_cd_value1 (dset_cd_id, code) VALUES (1, 'CODE1');
INSERT INTO dset_cd_value1 (dset_cd_id, code) VALUES (2, 'CODE2');
INSERT INTO dset_cd_value1 (dset_cd_id, code) VALUES (3, 'CODE3');
INSERT INTO dset_cd_value1 (dset_cd_id, code) VALUES (4, 'CODE4');
INSERT INTO dset_cd_value1 (dset_cd_id, code) VALUES (5, 'CODE5');


--
-- TOC entry 2248 (class 0 OID 18575)
-- Dependencies: 1674
-- Data for Name: dset_cd_value2; Type: TABLE DATA; 
--

INSERT INTO dset_cd_value2 (dset_cd_id, code, null_flavor) VALUES (6, 'CODE1', NULL);
INSERT INTO dset_cd_value2 (dset_cd_id, code, null_flavor) VALUES (7, 'CODE2', NULL);
INSERT INTO dset_cd_value2 (dset_cd_id, code, null_flavor) VALUES (8, 'CODE3', NULL);
INSERT INTO dset_cd_value2 (dset_cd_id, code, null_flavor) VALUES (9, NULL, 'NI');
INSERT INTO dset_cd_value2 (dset_cd_id, code, null_flavor) VALUES (10, NULL, 'NI');


--
-- TOC entry 2249 (class 0 OID 18578)
-- Dependencies: 1675
-- Data for Name: dset_cd_value3; Type: TABLE DATA; 
--

INSERT INTO dset_cd_value3 (dset_cd_id, code, code_system, code_system_name) VALUES (11, 'CODE1', 'CODE_SYSTEM1', 'CODE_SYSTEM_NAME1');
INSERT INTO dset_cd_value3 (dset_cd_id, code, code_system, code_system_name) VALUES (12, 'CODE2', 'CODE_SYSTEM2', 'CODE_SYSTEM_NAME2');
INSERT INTO dset_cd_value3 (dset_cd_id, code, code_system, code_system_name) VALUES (13, 'CODE3', 'CODE_SYSTEM3', 'CODE_SYSTEM_NAME3');
INSERT INTO dset_cd_value3 (dset_cd_id, code, code_system, code_system_name) VALUES (14, 'CODE4', 'CODE_SYSTEM4', 'CODE_SYSTEM_NAME4');
INSERT INTO dset_cd_value3 (dset_cd_id, code, code_system, code_system_name) VALUES (15, 'CODE5', 'CODE_SYSTEM5', 'CODE_SYSTEM_NAME5');


--
-- TOC entry 2250 (class 0 OID 18581)
-- Dependencies: 1676
-- Data for Name: dset_cd_value4; Type: TABLE DATA; 
--

INSERT INTO dset_cd_value4 (dset_cd_id, code, code_system, code_system_name, code_system_version, displayable_value, originaltext_value, originaltext_desc) VALUES (16, 'CODE1', 'CODE_SYSTEM1', 'CODE_SYSTEM_NAME1', 'CODE_SYSTEM_VERSION1', 'CODE1', 'OrgTextVal1', 'OrgTextDesc1');
INSERT INTO dset_cd_value4 (dset_cd_id, code, code_system, code_system_name, code_system_version, displayable_value, originaltext_value, originaltext_desc) VALUES (17, 'CODE2', 'CODE_SYSTEM2', 'CODE_SYSTEM_NAME2', 'CODE_SYSTEM_VERSION2', 'CODE2', 'OrgTextVal2', 'OrgTextDesc2');
INSERT INTO dset_cd_value4 (dset_cd_id, code, code_system, code_system_name, code_system_version, displayable_value, originaltext_value, originaltext_desc) VALUES (18, 'CODE3', 'CODE_SYSTEM3', 'CODE_SYSTEM_NAME3', 'CODE_SYSTEM_VERSION3', 'CODE3', 'OrgTextVal3', 'OrgTextDesc3');
INSERT INTO dset_cd_value4 (dset_cd_id, code, code_system, code_system_name, code_system_version, displayable_value, originaltext_value, originaltext_desc) VALUES (19, 'CODE4', 'CODE_SYSTEM4', 'CODE_SYSTEM_NAME4', 'CODE_SYSTEM_VERSION4', 'CODE4', 'OrgTextVal4', 'OrgTextDesc4');
INSERT INTO dset_cd_value4 (dset_cd_id, code, code_system, code_system_name, code_system_version, displayable_value, originaltext_value, originaltext_desc) VALUES (20, 'CODE5', 'CODE_SYSTEM5', 'CODE_SYSTEM_NAME5', 'CODE_SYSTEM_VERSION5', 'CODE5', 'OrgTextVal5', 'OrgTextDesc5');


--
-- TOC entry 2251 (class 0 OID 18584)
-- Dependencies: 1677
-- Data for Name: dset_cd_value5; Type: TABLE DATA; 
--

INSERT INTO dset_cd_value5 (dset_cd_id, code, code_system, code_system_name, code_system_version, displayable_value, originaltext_value, originaltext_desc) VALUES (21, 'CODE1', 'CODE_SYSTEM1', 'CODE_SYSTEM_NAME1', 'CODE_SYSTEM_VERSION1', 'CODE1', 'OrgTextVal1', 'OrgTextDesc1');
INSERT INTO dset_cd_value5 (dset_cd_id, code, code_system, code_system_name, code_system_version, displayable_value, originaltext_value, originaltext_desc) VALUES (22, 'CODE2', 'CODE_SYSTEM2', 'CODE_SYSTEM_NAME2', 'CODE_SYSTEM_VERSION2', 'CODE2', 'OrgTextVal2', 'OrgTextDesc2');
INSERT INTO dset_cd_value5 (dset_cd_id, code, code_system, code_system_name, code_system_version, displayable_value, originaltext_value, originaltext_desc) VALUES (23, 'CODE3', 'CODE_SYSTEM3', 'CODE_SYSTEM_NAME3', 'CODE_SYSTEM_VERSION3', 'CODE3', 'OrgTextVal3', 'OrgTextDesc3');
INSERT INTO dset_cd_value5 (dset_cd_id, code, code_system, code_system_name, code_system_version, displayable_value, originaltext_value, originaltext_desc) VALUES (24, 'CODE4', 'CODE_SYSTEM4', 'CODE_SYSTEM_NAME4', 'CODE_SYSTEM_VERSION4', 'CODE4', 'OrgTextVal4', 'OrgTextDesc4');
INSERT INTO dset_cd_value5 (dset_cd_id, code, code_system, code_system_name, code_system_version, displayable_value, originaltext_value, originaltext_desc) VALUES (25, 'CODE5', 'CODE_SYSTEM5', 'CODE_SYSTEM_NAME5', 'CODE_SYSTEM_VERSION5', 'CODE5', 'OrgTextVal5', 'OrgTextDesc5');


--
-- TOC entry 2252 (class 0 OID 18587)
-- Dependencies: 1678
-- Data for Name: dset_cd_value6; Type: TABLE DATA; 
--

INSERT INTO dset_cd_value6 (id, dset_cd_id, code) VALUES (1, 26, 'CODE1');
INSERT INTO dset_cd_value6 (id, dset_cd_id, code) VALUES (2, 27, 'CODE2');
INSERT INTO dset_cd_value6 (id, dset_cd_id, code) VALUES (3, 28, 'CODE3');
INSERT INTO dset_cd_value6 (id, dset_cd_id, code) VALUES (4, 29, 'CODE4');
INSERT INTO dset_cd_value6 (id, dset_cd_id, code) VALUES (5, 30, 'CODE5');


--
-- TOC entry 2253 (class 0 OID 18590)
-- Dependencies: 1679
-- Data for Name: dset_cd_value7; Type: TABLE DATA; 
--

INSERT INTO dset_cd_value7 (id, code) VALUES (1, 'CODE1');
INSERT INTO dset_cd_value7 (id, code) VALUES (2, 'CODE2');
INSERT INTO dset_cd_value7 (id, code) VALUES (3, 'CODE3');
INSERT INTO dset_cd_value7 (id, code) VALUES (4, 'CODE4');
INSERT INTO dset_cd_value7 (id, code) VALUES (5, 'CODE5');


--
-- TOC entry 2254 (class 0 OID 18593)
-- Dependencies: 1680
-- Data for Name: dset_en; Type: TABLE DATA; 
--



--
-- TOC entry 2255 (class 0 OID 18596)
-- Dependencies: 1681
-- Data for Name: dset_en_value1; Type: TABLE DATA; 
--



--
-- TOC entry 2256 (class 0 OID 18599)
-- Dependencies: 1682
-- Data for Name: dset_en_value2; Type: TABLE DATA; 
--



--
-- TOC entry 2257 (class 0 OID 18602)
-- Dependencies: 1683
-- Data for Name: dset_en_value3; Type: TABLE DATA; 
--



--
-- TOC entry 2258 (class 0 OID 18605)
-- Dependencies: 1684
-- Data for Name: dset_en_value4; Type: TABLE DATA; 
--



--
-- TOC entry 2259 (class 0 OID 18608)
-- Dependencies: 1685
-- Data for Name: dset_en_value5; Type: TABLE DATA; 
--



--
-- TOC entry 2260 (class 0 OID 18611)
-- Dependencies: 1686
-- Data for Name: dset_en_value6; Type: TABLE DATA; 
--



--
-- TOC entry 2261 (class 0 OID 18614)
-- Dependencies: 1687
-- Data for Name: dset_en_value7; Type: TABLE DATA; 
--



--
-- TOC entry 2262 (class 0 OID 18617)
-- Dependencies: 1688
-- Data for Name: dset_ii; Type: TABLE DATA; 
--

INSERT INTO dset_ii (id) VALUES (1);
INSERT INTO dset_ii (id) VALUES (2);
INSERT INTO dset_ii (id) VALUES (3);
INSERT INTO dset_ii (id) VALUES (4);
INSERT INTO dset_ii (id) VALUES (5);
INSERT INTO dset_ii (id) VALUES (6);
INSERT INTO dset_ii (id) VALUES (7);
INSERT INTO dset_ii (id) VALUES (8);
INSERT INTO dset_ii (id) VALUES (9);
INSERT INTO dset_ii (id) VALUES (10);
INSERT INTO dset_ii (id) VALUES (11);
INSERT INTO dset_ii (id) VALUES (12);
INSERT INTO dset_ii (id) VALUES (13);
INSERT INTO dset_ii (id) VALUES (14);
INSERT INTO dset_ii (id) VALUES (15);
INSERT INTO dset_ii (id) VALUES (16);
INSERT INTO dset_ii (id) VALUES (17);
INSERT INTO dset_ii (id) VALUES (18);
INSERT INTO dset_ii (id) VALUES (19);
INSERT INTO dset_ii (id) VALUES (20);
INSERT INTO dset_ii (id) VALUES (21);
INSERT INTO dset_ii (id) VALUES (22);
INSERT INTO dset_ii (id) VALUES (23);
INSERT INTO dset_ii (id) VALUES (24);
INSERT INTO dset_ii (id) VALUES (25);
INSERT INTO dset_ii (id) VALUES (26);
INSERT INTO dset_ii (id) VALUES (27);
INSERT INTO dset_ii (id) VALUES (28);
INSERT INTO dset_ii (id) VALUES (29);


--
-- TOC entry 2263 (class 0 OID 18620)
-- Dependencies: 1689
-- Data for Name: dset_ii_ii_value6; Type: TABLE DATA; 
--

INSERT INTO dset_ii_ii_value6 (dset_ii_id, dset_ii_value6_id) VALUES (26, 1);
INSERT INTO dset_ii_ii_value6 (dset_ii_id, dset_ii_value6_id) VALUES (27, 1);
INSERT INTO dset_ii_ii_value6 (dset_ii_id, dset_ii_value6_id) VALUES (28, 1);
INSERT INTO dset_ii_ii_value6 (dset_ii_id, dset_ii_value6_id) VALUES (29, 2);
INSERT INTO dset_ii_ii_value6 (dset_ii_id, dset_ii_value6_id) VALUES (29, 3);


--
-- TOC entry 2264 (class 0 OID 18623)
-- Dependencies: 1690
-- Data for Name: dset_ii_value1; Type: TABLE DATA; 
--

INSERT INTO dset_ii_value1 (dset_ii_id, extension) VALUES (1, 'Extension1');
INSERT INTO dset_ii_value1 (dset_ii_id, extension) VALUES (2, 'Extension2');
INSERT INTO dset_ii_value1 (dset_ii_id, extension) VALUES (3, 'Extension3');
INSERT INTO dset_ii_value1 (dset_ii_id, extension) VALUES (4, 'Extension4');
INSERT INTO dset_ii_value1 (dset_ii_id, extension) VALUES (5, 'Extension5');


--
-- TOC entry 2265 (class 0 OID 18626)
-- Dependencies: 1691
-- Data for Name: dset_ii_value2; Type: TABLE DATA; 
--

INSERT INTO dset_ii_value2 (dset_ii_id, extension, root, null_flavor) VALUES (6, NULL, NULL, 'NI');
INSERT INTO dset_ii_value2 (dset_ii_id, extension, root, null_flavor) VALUES (7, 'Extension2', 'ROOT2', NULL);
INSERT INTO dset_ii_value2 (dset_ii_id, extension, root, null_flavor) VALUES (8, 'Extension3', 'ROOT3', NULL);
INSERT INTO dset_ii_value2 (dset_ii_id, extension, root, null_flavor) VALUES (9, 'Extension4', 'ROOT4', NULL);
INSERT INTO dset_ii_value2 (dset_ii_id, extension, root, null_flavor) VALUES (10, 'Extension5', 'ROOT5', NULL);


--
-- TOC entry 2266 (class 0 OID 18629)
-- Dependencies: 1692
-- Data for Name: dset_ii_value3; Type: TABLE DATA; 
--

INSERT INTO dset_ii_value3 (dset_ii_id, extension, identifier_name, displayable_value, reliability, scope) VALUES (11, 'Extension1', NULL, NULL, NULL, NULL);
INSERT INTO dset_ii_value3 (dset_ii_id, extension, identifier_name, displayable_value, reliability, scope) VALUES (12, NULL, 'IDENTIFIER_NAME2', NULL, NULL, NULL);
INSERT INTO dset_ii_value3 (dset_ii_id, extension, identifier_name, displayable_value, reliability, scope) VALUES (13, 'Extension3', 'IDENTIFIER_NAME3', NULL, NULL, NULL);
INSERT INTO dset_ii_value3 (dset_ii_id, extension, identifier_name, displayable_value, reliability, scope) VALUES (14, 'Extension4', 'IDENTIFIER_NAME4', NULL, NULL, NULL);
INSERT INTO dset_ii_value3 (dset_ii_id, extension, identifier_name, displayable_value, reliability, scope) VALUES (15, 'Extension5', 'IDENTIFIER_NAME5', NULL, NULL, NULL);


--
-- TOC entry 2267 (class 0 OID 18632)
-- Dependencies: 1693
-- Data for Name: dset_ii_value4; Type: TABLE DATA; 
--

INSERT INTO dset_ii_value4 (dset_ii_id, root, extension, identifier_name, reliability, scope, displayable_value, null_flavor) VALUES (16, 'Root1', 'Extension1', NULL, NULL, NULL, '1', NULL);
INSERT INTO dset_ii_value4 (dset_ii_id, root, extension, identifier_name, reliability, scope, displayable_value, null_flavor) VALUES (17, 'Root2', 'Extension2', 'IDENTIFIER_NAME2', NULL, NULL, '1', NULL);
INSERT INTO dset_ii_value4 (dset_ii_id, root, extension, identifier_name, reliability, scope, displayable_value, null_flavor) VALUES (18, 'Root3', NULL, 'IDENTIFIER_NAME3', 'ISS', NULL, '1', NULL);
INSERT INTO dset_ii_value4 (dset_ii_id, root, extension, identifier_name, reliability, scope, displayable_value, null_flavor) VALUES (19, 'Root4', 'Extension4', 'IDENTIFIER_NAME4', 'ISS', 'BUSN', '0', NULL);
INSERT INTO dset_ii_value4 (dset_ii_id, root, extension, identifier_name, reliability, scope, displayable_value, null_flavor) VALUES (20, 'Root5', 'Extension5', 'IDENTIFIER_NAME5', 'ISS', 'BUSN', '1', NULL);


--
-- TOC entry 2268 (class 0 OID 18635)
-- Dependencies: 1694
-- Data for Name: dset_ii_value5; Type: TABLE DATA; 
--

INSERT INTO dset_ii_value5 (id, dset_ii_id, extension) VALUES (1, 21, 'Extension1');
INSERT INTO dset_ii_value5 (id, dset_ii_id, extension) VALUES (2, 22, 'Extension2');
INSERT INTO dset_ii_value5 (id, dset_ii_id, extension) VALUES (3, 23, 'Extension3');
INSERT INTO dset_ii_value5 (id, dset_ii_id, extension) VALUES (4, 24, 'Extension4');
INSERT INTO dset_ii_value5 (id, dset_ii_id, extension) VALUES (5, 25, 'Extension5');


--
-- TOC entry 2269 (class 0 OID 18638)
-- Dependencies: 1695
-- Data for Name: dset_ii_value6; Type: TABLE DATA; 
--

INSERT INTO dset_ii_value6 (id, extension) VALUES (1, 'Extension1');
INSERT INTO dset_ii_value6 (id, extension) VALUES (2, 'Extension4');
INSERT INTO dset_ii_value6 (id, extension) VALUES (3, 'Extension5');


--
-- TOC entry 2270 (class 0 OID 18641)
-- Dependencies: 1696
-- Data for Name: dset_tel; Type: TABLE DATA; 
--

INSERT INTO dset_tel (id, null_flavor) VALUES (0, NULL);
INSERT INTO dset_tel (id, null_flavor) VALUES (1, NULL);
INSERT INTO dset_tel (id, null_flavor) VALUES (2, NULL);
INSERT INTO dset_tel (id, null_flavor) VALUES (3, NULL);
INSERT INTO dset_tel (id, null_flavor) VALUES (4, NULL);
INSERT INTO dset_tel (id, null_flavor) VALUES (5, NULL);
INSERT INTO dset_tel (id, null_flavor) VALUES (6, NULL);
INSERT INTO dset_tel (id, null_flavor) VALUES (7, NULL);
INSERT INTO dset_tel (id, null_flavor) VALUES (8, NULL);
INSERT INTO dset_tel (id, null_flavor) VALUES (9, NULL);
INSERT INTO dset_tel (id, null_flavor) VALUES (10, NULL);
INSERT INTO dset_tel (id, null_flavor) VALUES (11, NULL);
INSERT INTO dset_tel (id, null_flavor) VALUES (12, NULL);
INSERT INTO dset_tel (id, null_flavor) VALUES (13, NULL);
INSERT INTO dset_tel (id, null_flavor) VALUES (14, NULL);


--
-- TOC entry 2271 (class 0 OID 18644)
-- Dependencies: 1697
-- Data for Name: dset_tel_email; Type: TABLE DATA; 
--

INSERT INTO dset_tel_email (id, null_flavor) VALUES (0, NULL);
INSERT INTO dset_tel_email (id, null_flavor) VALUES (1, NULL);
INSERT INTO dset_tel_email (id, null_flavor) VALUES (2, NULL);
INSERT INTO dset_tel_email (id, null_flavor) VALUES (3, NULL);
INSERT INTO dset_tel_email (id, null_flavor) VALUES (4, NULL);
INSERT INTO dset_tel_email (id, null_flavor) VALUES (5, NULL);
INSERT INTO dset_tel_email (id, null_flavor) VALUES (6, NULL);
INSERT INTO dset_tel_email (id, null_flavor) VALUES (7, NULL);
INSERT INTO dset_tel_email (id, null_flavor) VALUES (8, NULL);
INSERT INTO dset_tel_email (id, null_flavor) VALUES (9, NULL);


--
-- TOC entry 2272 (class 0 OID 18647)
-- Dependencies: 1698
-- Data for Name: dset_tel_email_value1; Type: TABLE DATA; 
--

INSERT INTO dset_tel_email_value1 (dset_tel_email_id, tel_email_value) VALUES (0, 'TEL_EMAIL_VALUE1');
INSERT INTO dset_tel_email_value1 (dset_tel_email_id, tel_email_value) VALUES (1, 'TEL_EMAIL_VALUE2');
INSERT INTO dset_tel_email_value1 (dset_tel_email_id, tel_email_value) VALUES (2, 'TEL_EMAIL_VALUE3');
INSERT INTO dset_tel_email_value1 (dset_tel_email_id, tel_email_value) VALUES (3, 'TEL_EMAIL_VALUE4');
INSERT INTO dset_tel_email_value1 (dset_tel_email_id, tel_email_value) VALUES (4, 'TEL_EMAIL_VALUE5');
INSERT INTO dset_tel_email_value1 (dset_tel_email_id, tel_email_value) VALUES (5, 'TEL_EMAIL_VALUE1');
INSERT INTO dset_tel_email_value1 (dset_tel_email_id, tel_email_value) VALUES (6, 'TEL_EMAIL_VALUE2');
INSERT INTO dset_tel_email_value1 (dset_tel_email_id, tel_email_value) VALUES (7, 'TEL_EMAIL_VALUE3');
INSERT INTO dset_tel_email_value1 (dset_tel_email_id, tel_email_value) VALUES (8, 'TEL_EMAIL_VALUE4');
INSERT INTO dset_tel_email_value1 (dset_tel_email_id, tel_email_value) VALUES (9, 'TEL_EMAIL_VALUE5');


--
-- TOC entry 2273 (class 0 OID 18650)
-- Dependencies: 1699
-- Data for Name: dset_tel_email_value2; Type: TABLE DATA; 
--



--
-- TOC entry 2274 (class 0 OID 18653)
-- Dependencies: 1700
-- Data for Name: dset_tel_person; Type: TABLE DATA; 
--

INSERT INTO dset_tel_person (id, null_flavor) VALUES (0, NULL);
INSERT INTO dset_tel_person (id, null_flavor) VALUES (1, NULL);
INSERT INTO dset_tel_person (id, null_flavor) VALUES (2, NULL);
INSERT INTO dset_tel_person (id, null_flavor) VALUES (3, NULL);
INSERT INTO dset_tel_person (id, null_flavor) VALUES (4, NULL);


--
-- TOC entry 2275 (class 0 OID 18656)
-- Dependencies: 1701
-- Data for Name: dset_tel_person_value1; Type: TABLE DATA; 
--

INSERT INTO dset_tel_person_value1 (dset_tel_person_id, tel_person_value) VALUES (0, 'TEL_PERSON_VALUE1');
INSERT INTO dset_tel_person_value1 (dset_tel_person_id, tel_person_value) VALUES (1, 'TEL_PERSON_VALUE2');
INSERT INTO dset_tel_person_value1 (dset_tel_person_id, tel_person_value) VALUES (2, 'TEL_PERSON_VALUE3');
INSERT INTO dset_tel_person_value1 (dset_tel_person_id, tel_person_value) VALUES (3, 'TEL_PERSON_VALUE4');
INSERT INTO dset_tel_person_value1 (dset_tel_person_id, tel_person_value) VALUES (4, 'TEL_PERSON_VALUE5');


--
-- TOC entry 2276 (class 0 OID 18659)
-- Dependencies: 1702
-- Data for Name: dset_tel_phone; Type: TABLE DATA; 
--

INSERT INTO dset_tel_phone (id, null_flavor) VALUES (0, NULL);
INSERT INTO dset_tel_phone (id, null_flavor) VALUES (1, NULL);
INSERT INTO dset_tel_phone (id, null_flavor) VALUES (2, NULL);
INSERT INTO dset_tel_phone (id, null_flavor) VALUES (3, NULL);
INSERT INTO dset_tel_phone (id, null_flavor) VALUES (4, NULL);


--
-- TOC entry 2277 (class 0 OID 18662)
-- Dependencies: 1703
-- Data for Name: dset_tel_phone_value1; Type: TABLE DATA; 
--

INSERT INTO dset_tel_phone_value1 (dset_tel_phone_id, tel_phone_value) VALUES (0, 'TEL_PHONE_VALUE1');
INSERT INTO dset_tel_phone_value1 (dset_tel_phone_id, tel_phone_value) VALUES (1, 'TEL_PHONE_VALUE2');
INSERT INTO dset_tel_phone_value1 (dset_tel_phone_id, tel_phone_value) VALUES (2, 'TEL_PHONE_VALUE3');
INSERT INTO dset_tel_phone_value1 (dset_tel_phone_id, tel_phone_value) VALUES (3, 'TEL_PHONE_VALUE4');
INSERT INTO dset_tel_phone_value1 (dset_tel_phone_id, tel_phone_value) VALUES (4, 'TEL_PHONE_VALUE5');


--
-- TOC entry 2278 (class 0 OID 18665)
-- Dependencies: 1704
-- Data for Name: dset_tel_tel_value_3; Type: TABLE DATA; 
--

INSERT INTO dset_tel_tel_value_3 (dset_tel_id, dset_tel_value3_id) VALUES (10, 1);
INSERT INTO dset_tel_tel_value_3 (dset_tel_id, dset_tel_value3_id) VALUES (11, 1);
INSERT INTO dset_tel_tel_value_3 (dset_tel_id, dset_tel_value3_id) VALUES (12, 1);
INSERT INTO dset_tel_tel_value_3 (dset_tel_id, dset_tel_value3_id) VALUES (13, 1);
INSERT INTO dset_tel_tel_value_3 (dset_tel_id, dset_tel_value3_id) VALUES (14, 1);


--
-- TOC entry 2279 (class 0 OID 18668)
-- Dependencies: 1705
-- Data for Name: dset_tel_url; Type: TABLE DATA; 
--

INSERT INTO dset_tel_url (id, null_flavor) VALUES (0, NULL);
INSERT INTO dset_tel_url (id, null_flavor) VALUES (1, NULL);
INSERT INTO dset_tel_url (id, null_flavor) VALUES (2, NULL);
INSERT INTO dset_tel_url (id, null_flavor) VALUES (3, NULL);
INSERT INTO dset_tel_url (id, null_flavor) VALUES (4, NULL);


--
-- TOC entry 2280 (class 0 OID 18671)
-- Dependencies: 1706
-- Data for Name: dset_tel_url_value1; Type: TABLE DATA; 
--

INSERT INTO dset_tel_url_value1 (dset_tel_url_id, tel_url_value) VALUES (0, 'TEL_URL_VALUE1');
INSERT INTO dset_tel_url_value1 (dset_tel_url_id, tel_url_value) VALUES (1, 'TEL_URL_VALUE2');
INSERT INTO dset_tel_url_value1 (dset_tel_url_id, tel_url_value) VALUES (2, 'TEL_URL_VALUE3');
INSERT INTO dset_tel_url_value1 (dset_tel_url_id, tel_url_value) VALUES (3, 'TEL_URL_VALUE4');
INSERT INTO dset_tel_url_value1 (dset_tel_url_id, tel_url_value) VALUES (4, 'TEL_URL_VALUE5');


--
-- TOC entry 2281 (class 0 OID 18674)
-- Dependencies: 1707
-- Data for Name: dset_tel_value1; Type: TABLE DATA; 
--

INSERT INTO dset_tel_value1 (dset_tel_id, tel_value) VALUES (0, 'tel://123-456-7891');
INSERT INTO dset_tel_value1 (dset_tel_id, tel_value) VALUES (1, 'tel://123-456-7892');
INSERT INTO dset_tel_value1 (dset_tel_id, tel_value) VALUES (2, 'tel://123-456-7893');
INSERT INTO dset_tel_value1 (dset_tel_id, tel_value) VALUES (3, 'tel://123-456-7894');
INSERT INTO dset_tel_value1 (dset_tel_id, tel_value) VALUES (4, 'tel://123-456-7895');


--
-- TOC entry 2282 (class 0 OID 18677)
-- Dependencies: 1708
-- Data for Name: dset_tel_value2; Type: TABLE DATA; 
--

INSERT INTO dset_tel_value2 (id, dset_tel_id, tel_value) VALUES (1, 5, 'tel://123-456-7891');
INSERT INTO dset_tel_value2 (id, dset_tel_id, tel_value) VALUES (2, 6, 'tel://123-456-7892');
INSERT INTO dset_tel_value2 (id, dset_tel_id, tel_value) VALUES (3, 7, 'tel://123-456-7893');
INSERT INTO dset_tel_value2 (id, dset_tel_id, tel_value) VALUES (4, 8, 'tel://123-456-7894');
INSERT INTO dset_tel_value2 (id, dset_tel_id, tel_value) VALUES (5, 9, 'tel://123-456-7895');


--
-- TOC entry 2283 (class 0 OID 18680)
-- Dependencies: 1709
-- Data for Name: dset_tel_value3; Type: TABLE DATA; 
--

INSERT INTO dset_tel_value3 (id, tel_value) VALUES (1, 'tel://123-456-7892');


--
-- TOC entry 2284 (class 0 OID 18683)
-- Dependencies: 1710
-- Data for Name: ed_datatype; Type: TABLE DATA; 
--

INSERT INTO ed_datatype (id, value1_data, value2_null_flavor, value2_data, value2_compression, value3_null_flavor, value3_data, value3_compression, value3_description, value3_value) VALUES (1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ed_datatype (id, value1_data, value2_null_flavor, value2_data, value2_compression, value3_null_flavor, value3_data, value3_compression, value3_description, value3_value) VALUES (2, '0110101010010', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ed_datatype (id, value1_data, value2_null_flavor, value2_data, value2_compression, value3_null_flavor, value3_data, value3_compression, value3_description, value3_value) VALUES (3, '0100101010011', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ed_datatype (id, value1_data, value2_null_flavor, value2_data, value2_compression, value3_null_flavor, value3_data, value3_compression, value3_description, value3_value) VALUES (4, '0100001010010', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ed_datatype (id, value1_data, value2_null_flavor, value2_data, value2_compression, value3_null_flavor, value3_data, value3_compression, value3_description, value3_value) VALUES (5, '0110001010011', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ed_datatype (id, value1_data, value2_null_flavor, value2_data, value2_compression, value3_null_flavor, value3_data, value3_compression, value3_description, value3_value) VALUES (6, '0110001011110', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ed_datatype (id, value1_data, value2_null_flavor, value2_data, value2_compression, value3_null_flavor, value3_data, value3_compression, value3_description, value3_value) VALUES (7, NULL, 'NI', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ed_datatype (id, value1_data, value2_null_flavor, value2_data, value2_compression, value3_null_flavor, value3_data, value3_compression, value3_description, value3_value) VALUES (8, NULL, NULL, '0110001010010', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ed_datatype (id, value1_data, value2_null_flavor, value2_data, value2_compression, value3_null_flavor, value3_data, value3_compression, value3_description, value3_value) VALUES (9, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ed_datatype (id, value1_data, value2_null_flavor, value2_data, value2_compression, value3_null_flavor, value3_data, value3_compression, value3_description, value3_value) VALUES (10, NULL, NULL, NULL, 'GZ', NULL, NULL, NULL, NULL, NULL);
INSERT INTO ed_datatype (id, value1_data, value2_null_flavor, value2_data, value2_compression, value3_null_flavor, value3_data, value3_compression, value3_description, value3_value) VALUES (11, NULL, NULL, '0110001010010', 'GZ', NULL, NULL, NULL, NULL, NULL);
INSERT INTO ed_datatype (id, value1_data, value2_null_flavor, value2_data, value2_compression, value3_null_flavor, value3_data, value3_compression, value3_description, value3_value) VALUES (12, NULL, NULL, NULL, NULL, NULL, NULL, 'GZ', NULL, NULL);
INSERT INTO ed_datatype (id, value1_data, value2_null_flavor, value2_data, value2_compression, value3_null_flavor, value3_data, value3_compression, value3_description, value3_value) VALUES (13, NULL, NULL, NULL, NULL, 'NI', NULL, NULL, NULL, NULL);
INSERT INTO ed_datatype (id, value1_data, value2_null_flavor, value2_data, value2_compression, value3_null_flavor, value3_data, value3_compression, value3_description, value3_value) VALUES (14, NULL, NULL, NULL, NULL, NULL, NULL, 'GZ', NULL, NULL);
INSERT INTO ed_datatype (id, value1_data, value2_null_flavor, value2_data, value2_compression, value3_null_flavor, value3_data, value3_compression, value3_description, value3_value) VALUES (15, NULL, NULL, NULL, NULL, NULL, NULL, 'GZ', 'DESCRIPTION', NULL);
INSERT INTO ed_datatype (id, value1_data, value2_null_flavor, value2_data, value2_compression, value3_null_flavor, value3_data, value3_compression, value3_description, value3_value) VALUES (16, NULL, NULL, NULL, NULL, NULL, '0110001010010', 'GZ', 'DESCRIPTION', 'VALUE3_VALUE_A');


--
-- TOC entry 2285 (class 0 OID 18689)
-- Dependencies: 1711
-- Data for Name: ed_text_datatype; Type: TABLE DATA; 
--

INSERT INTO ed_text_datatype (id, value1_value, value2_null_flavor, value2_value, value3_null_flavor, value3_data, value3_compression, value3_description, value3_value) VALUES (17, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ed_text_datatype (id, value1_value, value2_null_flavor, value2_value, value3_null_flavor, value3_data, value3_compression, value3_description, value3_value) VALUES (18, 'ED_TEXT_VALUE1_VALUE1', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ed_text_datatype (id, value1_value, value2_null_flavor, value2_value, value3_null_flavor, value3_data, value3_compression, value3_description, value3_value) VALUES (19, 'ED_TEXT_VALUE1_VALUE2', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ed_text_datatype (id, value1_value, value2_null_flavor, value2_value, value3_null_flavor, value3_data, value3_compression, value3_description, value3_value) VALUES (20, 'ED_TEXT_VALUE1_VALUE3', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ed_text_datatype (id, value1_value, value2_null_flavor, value2_value, value3_null_flavor, value3_data, value3_compression, value3_description, value3_value) VALUES (21, 'ED_TEXT_VALUE1_VALUE4', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ed_text_datatype (id, value1_value, value2_null_flavor, value2_value, value3_null_flavor, value3_data, value3_compression, value3_description, value3_value) VALUES (22, 'ED_TEXT_VALUE1_VALUE5', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ed_text_datatype (id, value1_value, value2_null_flavor, value2_value, value3_null_flavor, value3_data, value3_compression, value3_description, value3_value) VALUES (23, NULL, 'NI', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ed_text_datatype (id, value1_value, value2_null_flavor, value2_value, value3_null_flavor, value3_data, value3_compression, value3_description, value3_value) VALUES (24, NULL, NULL, 'ED_TEXT_VALUE2_VALUE1', NULL, NULL, NULL, NULL, NULL);
INSERT INTO ed_text_datatype (id, value1_value, value2_null_flavor, value2_value, value3_null_flavor, value3_data, value3_compression, value3_description, value3_value) VALUES (25, NULL, NULL, 'ED_TEXT_VALUE2_VALUE2', NULL, NULL, NULL, NULL, NULL);
INSERT INTO ed_text_datatype (id, value1_value, value2_null_flavor, value2_value, value3_null_flavor, value3_data, value3_compression, value3_description, value3_value) VALUES (26, NULL, NULL, 'ED_TEXT_VALUE2_VALUE3', NULL, NULL, NULL, NULL, NULL);
INSERT INTO ed_text_datatype (id, value1_value, value2_null_flavor, value2_value, value3_null_flavor, value3_data, value3_compression, value3_description, value3_value) VALUES (27, NULL, NULL, 'ED_TEXT_VALUE2_VALUE4', NULL, NULL, NULL, NULL, NULL);
INSERT INTO ed_text_datatype (id, value1_value, value2_null_flavor, value2_value, value3_null_flavor, value3_data, value3_compression, value3_description, value3_value) VALUES (28, NULL, NULL, NULL, NULL, NULL, 'GZ', NULL, NULL);
INSERT INTO ed_text_datatype (id, value1_value, value2_null_flavor, value2_value, value3_null_flavor, value3_data, value3_compression, value3_description, value3_value) VALUES (29, NULL, NULL, NULL, 'NI', NULL, NULL, NULL, NULL);
INSERT INTO ed_text_datatype (id, value1_value, value2_null_flavor, value2_value, value3_null_flavor, value3_data, value3_compression, value3_description, value3_value) VALUES (30, NULL, NULL, NULL, NULL, '0110001011010', 'GZ', NULL, NULL);
INSERT INTO ed_text_datatype (id, value1_value, value2_null_flavor, value2_value, value3_null_flavor, value3_data, value3_compression, value3_description, value3_value) VALUES (31, NULL, NULL, NULL, NULL, '0110111010011', 'GZ', 'DESCRIPTION', NULL);
INSERT INTO ed_text_datatype (id, value1_value, value2_null_flavor, value2_value, value3_null_flavor, value3_data, value3_compression, value3_description, value3_value) VALUES (32, NULL, NULL, NULL, NULL, '0110001010010', 'GZ', 'DESCRIPTION', 'VALUE3_VALUE_A');


--
-- TOC entry 2286 (class 0 OID 18695)
-- Dependencies: 1712
-- Data for Name: en_datatype; Type: TABLE DATA; 
--

INSERT INTO en_datatype (id, value1_pn_value, value2_pn_value, value2_pn_code, value3_pn_value, value3_pn_code, value3_pn_code_system, value3_pn_code_system_version, value4_pn_value, value4_pn_enpq, value5_pn_value, value5_pn2_value, value5_pn_code, value5_pn2_code, value5_pn_code_system, value5_pn2_code_system, value5_pn_code_system_version, value5_pn2_code_system_version, value6_pn_value, value6_on_value, value6_pn_code, value6_on_code, value6_pn_code_system, value6_on_code_system, value6_pn_code_system_version, value6_on_code_system_version) VALUES (1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO en_datatype (id, value1_pn_value, value2_pn_value, value2_pn_code, value3_pn_value, value3_pn_code, value3_pn_code_system, value3_pn_code_system_version, value4_pn_value, value4_pn_enpq, value5_pn_value, value5_pn2_value, value5_pn_code, value5_pn2_code, value5_pn_code_system, value5_pn2_code_system, value5_pn_code_system_version, value5_pn2_code_system_version, value6_pn_value, value6_on_value, value6_pn_code, value6_on_code, value6_pn_code_system, value6_on_code_system, value6_pn_code_system_version, value6_on_code_system_version) VALUES (2, 'Mr. John Doe Jr.', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO en_datatype (id, value1_pn_value, value2_pn_value, value2_pn_code, value3_pn_value, value3_pn_code, value3_pn_code_system, value3_pn_code_system_version, value4_pn_value, value4_pn_enpq, value5_pn_value, value5_pn2_value, value5_pn_code, value5_pn2_code, value5_pn_code_system, value5_pn2_code_system, value5_pn_code_system_version, value5_pn2_code_system_version, value6_pn_value, value6_on_value, value6_pn_code, value6_on_code, value6_pn_code_system, value6_on_code_system, value6_pn_code_system_version, value6_on_code_system_version) VALUES (3, 'Mr. John Doe Double Jr.', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO en_datatype (id, value1_pn_value, value2_pn_value, value2_pn_code, value3_pn_value, value3_pn_code, value3_pn_code_system, value3_pn_code_system_version, value4_pn_value, value4_pn_enpq, value5_pn_value, value5_pn2_value, value5_pn_code, value5_pn2_code, value5_pn_code_system, value5_pn2_code_system, value5_pn_code_system_version, value5_pn2_code_system_version, value6_pn_value, value6_on_value, value6_pn_code, value6_on_code, value6_pn_code_system, value6_on_code_system, value6_pn_code_system_version, value6_on_code_system_version) VALUES (4, 'Mr. John Doe Sr.', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO en_datatype (id, value1_pn_value, value2_pn_value, value2_pn_code, value3_pn_value, value3_pn_code, value3_pn_code_system, value3_pn_code_system_version, value4_pn_value, value4_pn_enpq, value5_pn_value, value5_pn2_value, value5_pn_code, value5_pn2_code, value5_pn_code_system, value5_pn2_code_system, value5_pn_code_system_version, value5_pn2_code_system_version, value6_pn_value, value6_on_value, value6_pn_code, value6_on_code, value6_pn_code_system, value6_on_code_system, value6_pn_code_system_version, value6_on_code_system_version) VALUES (5, 'Mr. John Doe Super Sr.', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO en_datatype (id, value1_pn_value, value2_pn_value, value2_pn_code, value3_pn_value, value3_pn_code, value3_pn_code_system, value3_pn_code_system_version, value4_pn_value, value4_pn_enpq, value5_pn_value, value5_pn2_value, value5_pn_code, value5_pn2_code, value5_pn_code_system, value5_pn2_code_system, value5_pn_code_system_version, value5_pn2_code_system_version, value6_pn_value, value6_on_value, value6_pn_code, value6_on_code, value6_pn_code_system, value6_on_code_system, value6_pn_code_system_version, value6_on_code_system_version) VALUES (6, 'Mr. John Doe II', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO en_datatype (id, value1_pn_value, value2_pn_value, value2_pn_code, value3_pn_value, value3_pn_code, value3_pn_code_system, value3_pn_code_system_version, value4_pn_value, value4_pn_enpq, value5_pn_value, value5_pn2_value, value5_pn_code, value5_pn2_code, value5_pn_code_system, value5_pn2_code_system, value5_pn_code_system_version, value5_pn2_code_system_version, value6_pn_value, value6_on_value, value6_pn_code, value6_on_code, value6_pn_code_system, value6_on_code_system, value6_pn_code_system_version, value6_on_code_system_version) VALUES (7, NULL, 'Mr. John Doe Jr1.', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO en_datatype (id, value1_pn_value, value2_pn_value, value2_pn_code, value3_pn_value, value3_pn_code, value3_pn_code_system, value3_pn_code_system_version, value4_pn_value, value4_pn_enpq, value5_pn_value, value5_pn2_value, value5_pn_code, value5_pn2_code, value5_pn_code_system, value5_pn2_code_system, value5_pn_code_system_version, value5_pn2_code_system_version, value6_pn_value, value6_on_value, value6_pn_code, value6_on_code, value6_pn_code_system, value6_on_code_system, value6_pn_code_system_version, value6_on_code_system_version) VALUES (8, NULL, 'Mr. John Doe Jr2.', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO en_datatype (id, value1_pn_value, value2_pn_value, value2_pn_code, value3_pn_value, value3_pn_code, value3_pn_code_system, value3_pn_code_system_version, value4_pn_value, value4_pn_enpq, value5_pn_value, value5_pn2_value, value5_pn_code, value5_pn2_code, value5_pn_code_system, value5_pn2_code_system, value5_pn_code_system_version, value5_pn2_code_system_version, value6_pn_value, value6_on_value, value6_pn_code, value6_on_code, value6_pn_code_system, value6_on_code_system, value6_pn_code_system_version, value6_on_code_system_version) VALUES (9, NULL, 'Mr. John Doe Jr3.', 'JDJ3', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO en_datatype (id, value1_pn_value, value2_pn_value, value2_pn_code, value3_pn_value, value3_pn_code, value3_pn_code_system, value3_pn_code_system_version, value4_pn_value, value4_pn_enpq, value5_pn_value, value5_pn2_value, value5_pn_code, value5_pn2_code, value5_pn_code_system, value5_pn2_code_system, value5_pn_code_system_version, value5_pn2_code_system_version, value6_pn_value, value6_on_value, value6_pn_code, value6_on_code, value6_pn_code_system, value6_on_code_system, value6_pn_code_system_version, value6_on_code_system_version) VALUES (10, NULL, 'Mr. John Doe Jr4.', 'JDJ4', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO en_datatype (id, value1_pn_value, value2_pn_value, value2_pn_code, value3_pn_value, value3_pn_code, value3_pn_code_system, value3_pn_code_system_version, value4_pn_value, value4_pn_enpq, value5_pn_value, value5_pn2_value, value5_pn_code, value5_pn2_code, value5_pn_code_system, value5_pn2_code_system, value5_pn_code_system_version, value5_pn2_code_system_version, value6_pn_value, value6_on_value, value6_pn_code, value6_on_code, value6_pn_code_system, value6_on_code_system, value6_pn_code_system_version, value6_on_code_system_version) VALUES (11, NULL, 'Mr. John Doe Jr5.', 'JDJ5', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO en_datatype (id, value1_pn_value, value2_pn_value, value2_pn_code, value3_pn_value, value3_pn_code, value3_pn_code_system, value3_pn_code_system_version, value4_pn_value, value4_pn_enpq, value5_pn_value, value5_pn2_value, value5_pn_code, value5_pn2_code, value5_pn_code_system, value5_pn2_code_system, value5_pn_code_system_version, value5_pn2_code_system_version, value6_pn_value, value6_on_value, value6_pn_code, value6_on_code, value6_pn_code_system, value6_on_code_system, value6_pn_code_system_version, value6_on_code_system_version) VALUES (12, NULL, NULL, NULL, 'Mrs. Sarah Doe I', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO en_datatype (id, value1_pn_value, value2_pn_value, value2_pn_code, value3_pn_value, value3_pn_code, value3_pn_code_system, value3_pn_code_system_version, value4_pn_value, value4_pn_enpq, value5_pn_value, value5_pn2_value, value5_pn_code, value5_pn2_code, value5_pn_code_system, value5_pn2_code_system, value5_pn_code_system_version, value5_pn2_code_system_version, value6_pn_value, value6_on_value, value6_pn_code, value6_on_code, value6_pn_code_system, value6_on_code_system, value6_pn_code_system_version, value6_on_code_system_version) VALUES (13, NULL, NULL, NULL, 'Mrs. Sarah Doe II', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO en_datatype (id, value1_pn_value, value2_pn_value, value2_pn_code, value3_pn_value, value3_pn_code, value3_pn_code_system, value3_pn_code_system_version, value4_pn_value, value4_pn_enpq, value5_pn_value, value5_pn2_value, value5_pn_code, value5_pn2_code, value5_pn_code_system, value5_pn2_code_system, value5_pn_code_system_version, value5_pn2_code_system_version, value6_pn_value, value6_on_value, value6_pn_code, value6_on_code, value6_pn_code_system, value6_on_code_system, value6_pn_code_system_version, value6_on_code_system_version) VALUES (14, NULL, NULL, NULL, 'Mrs. Sarah Doe III', 'MSD1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO en_datatype (id, value1_pn_value, value2_pn_value, value2_pn_code, value3_pn_value, value3_pn_code, value3_pn_code_system, value3_pn_code_system_version, value4_pn_value, value4_pn_enpq, value5_pn_value, value5_pn2_value, value5_pn_code, value5_pn2_code, value5_pn_code_system, value5_pn2_code_system, value5_pn_code_system_version, value5_pn2_code_system_version, value6_pn_value, value6_on_value, value6_pn_code, value6_on_code, value6_pn_code_system, value6_on_code_system, value6_pn_code_system_version, value6_on_code_system_version) VALUES (15, NULL, NULL, NULL, 'Mrs. Sarah Doe IV', 'MSD2', 'VALUE3_PN_CODE_SYSTEM1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO en_datatype (id, value1_pn_value, value2_pn_value, value2_pn_code, value3_pn_value, value3_pn_code, value3_pn_code_system, value3_pn_code_system_version, value4_pn_value, value4_pn_enpq, value5_pn_value, value5_pn2_value, value5_pn_code, value5_pn2_code, value5_pn_code_system, value5_pn2_code_system, value5_pn_code_system_version, value5_pn2_code_system_version, value6_pn_value, value6_on_value, value6_pn_code, value6_on_code, value6_pn_code_system, value6_on_code_system, value6_pn_code_system_version, value6_on_code_system_version) VALUES (16, NULL, NULL, NULL, 'Mrs. Sarah Doe V', 'MSD3', 'VALUE3_PN_CODE_SYSTEM2', '1.3', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO en_datatype (id, value1_pn_value, value2_pn_value, value2_pn_code, value3_pn_value, value3_pn_code, value3_pn_code_system, value3_pn_code_system_version, value4_pn_value, value4_pn_enpq, value5_pn_value, value5_pn2_value, value5_pn_code, value5_pn2_code, value5_pn_code_system, value5_pn2_code_system, value5_pn_code_system_version, value5_pn2_code_system_version, value6_pn_value, value6_on_value, value6_pn_code, value6_on_code, value6_pn_code_system, value6_on_code_system, value6_pn_code_system_version, value6_on_code_system_version) VALUES (17, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'VALUE4_PN_VALUE1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO en_datatype (id, value1_pn_value, value2_pn_value, value2_pn_code, value3_pn_value, value3_pn_code, value3_pn_code_system, value3_pn_code_system_version, value4_pn_value, value4_pn_enpq, value5_pn_value, value5_pn2_value, value5_pn_code, value5_pn2_code, value5_pn_code_system, value5_pn2_code_system, value5_pn_code_system_version, value5_pn2_code_system_version, value6_pn_value, value6_on_value, value6_pn_code, value6_on_code, value6_pn_code_system, value6_on_code_system, value6_pn_code_system_version, value6_on_code_system_version) VALUES (18, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'BR', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO en_datatype (id, value1_pn_value, value2_pn_value, value2_pn_code, value3_pn_value, value3_pn_code, value3_pn_code_system, value3_pn_code_system_version, value4_pn_value, value4_pn_enpq, value5_pn_value, value5_pn2_value, value5_pn_code, value5_pn2_code, value5_pn_code_system, value5_pn2_code_system, value5_pn_code_system_version, value5_pn2_code_system_version, value6_pn_value, value6_on_value, value6_pn_code, value6_on_code, value6_pn_code_system, value6_on_code_system, value6_pn_code_system_version, value6_on_code_system_version) VALUES (19, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'VALUE4_PN_VALUE3', 'BR', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO en_datatype (id, value1_pn_value, value2_pn_value, value2_pn_code, value3_pn_value, value3_pn_code, value3_pn_code_system, value3_pn_code_system_version, value4_pn_value, value4_pn_enpq, value5_pn_value, value5_pn2_value, value5_pn_code, value5_pn2_code, value5_pn_code_system, value5_pn2_code_system, value5_pn_code_system_version, value5_pn2_code_system_version, value6_pn_value, value6_on_value, value6_pn_code, value6_on_code, value6_pn_code_system, value6_on_code_system, value6_pn_code_system_version, value6_on_code_system_version) VALUES (20, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'VALUE4_PN_VALUE4', 'BR', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO en_datatype (id, value1_pn_value, value2_pn_value, value2_pn_code, value3_pn_value, value3_pn_code, value3_pn_code_system, value3_pn_code_system_version, value4_pn_value, value4_pn_enpq, value5_pn_value, value5_pn2_value, value5_pn_code, value5_pn2_code, value5_pn_code_system, value5_pn2_code_system, value5_pn_code_system_version, value5_pn2_code_system_version, value6_pn_value, value6_on_value, value6_pn_code, value6_on_code, value6_pn_code_system, value6_on_code_system, value6_pn_code_system_version, value6_on_code_system_version) VALUES (21, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'VALUE4_PN_VALUE1', 'SP,BR,NB', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO en_datatype (id, value1_pn_value, value2_pn_value, value2_pn_code, value3_pn_value, value3_pn_code, value3_pn_code_system, value3_pn_code_system_version, value4_pn_value, value4_pn_enpq, value5_pn_value, value5_pn2_value, value5_pn_code, value5_pn2_code, value5_pn_code_system, value5_pn2_code_system, value5_pn_code_system_version, value5_pn2_code_system_version, value6_pn_value, value6_on_value, value6_pn_code, value6_on_code, value6_pn_code_system, value6_on_code_system, value6_pn_code_system_version, value6_on_code_system_version) VALUES (22, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'Mr. John Doe I', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO en_datatype (id, value1_pn_value, value2_pn_value, value2_pn_code, value3_pn_value, value3_pn_code, value3_pn_code_system, value3_pn_code_system_version, value4_pn_value, value4_pn_enpq, value5_pn_value, value5_pn2_value, value5_pn_code, value5_pn2_code, value5_pn_code_system, value5_pn2_code_system, value5_pn_code_system_version, value5_pn2_code_system_version, value6_pn_value, value6_on_value, value6_pn_code, value6_on_code, value6_pn_code_system, value6_on_code_system, value6_pn_code_system_version, value6_on_code_system_version) VALUES (23, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'Mr. John Doe II', NULL, 'JDJ1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO en_datatype (id, value1_pn_value, value2_pn_value, value2_pn_code, value3_pn_value, value3_pn_code, value3_pn_code_system, value3_pn_code_system_version, value4_pn_value, value4_pn_enpq, value5_pn_value, value5_pn2_value, value5_pn_code, value5_pn2_code, value5_pn_code_system, value5_pn2_code_system, value5_pn_code_system_version, value5_pn2_code_system_version, value6_pn_value, value6_on_value, value6_pn_code, value6_on_code, value6_pn_code_system, value6_on_code_system, value6_pn_code_system_version, value6_on_code_system_version) VALUES (24, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'Mr. John Doe III', NULL, 'JDJ1', NULL, 'VALUE5_PN_CODE_SYSTEM1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO en_datatype (id, value1_pn_value, value2_pn_value, value2_pn_code, value3_pn_value, value3_pn_code, value3_pn_code_system, value3_pn_code_system_version, value4_pn_value, value4_pn_enpq, value5_pn_value, value5_pn2_value, value5_pn_code, value5_pn2_code, value5_pn_code_system, value5_pn2_code_system, value5_pn_code_system_version, value5_pn2_code_system_version, value6_pn_value, value6_on_value, value6_pn_code, value6_on_code, value6_pn_code_system, value6_on_code_system, value6_pn_code_system_version, value6_on_code_system_version) VALUES (25, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'Mr. John Doe IV', NULL, 'JDJ1', NULL, 'VALUE5_PN_CODE_SYSTEM1', NULL, '2.1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO en_datatype (id, value1_pn_value, value2_pn_value, value2_pn_code, value3_pn_value, value3_pn_code, value3_pn_code_system, value3_pn_code_system_version, value4_pn_value, value4_pn_enpq, value5_pn_value, value5_pn2_value, value5_pn_code, value5_pn2_code, value5_pn_code_system, value5_pn2_code_system, value5_pn_code_system_version, value5_pn2_code_system_version, value6_pn_value, value6_on_value, value6_pn_code, value6_on_code, value6_pn_code_system, value6_on_code_system, value6_pn_code_system_version, value6_on_code_system_version) VALUES (26, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'Mr. John Doe V', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO en_datatype (id, value1_pn_value, value2_pn_value, value2_pn_code, value3_pn_value, value3_pn_code, value3_pn_code_system, value3_pn_code_system_version, value4_pn_value, value4_pn_enpq, value5_pn_value, value5_pn2_value, value5_pn_code, value5_pn2_code, value5_pn_code_system, value5_pn2_code_system, value5_pn_code_system_version, value5_pn2_code_system_version, value6_pn_value, value6_on_value, value6_pn_code, value6_on_code, value6_pn_code_system, value6_on_code_system, value6_pn_code_system_version, value6_on_code_system_version) VALUES (27, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'Mr. John Doe VI', NULL, 'VALUE5_PN2_CODE1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO en_datatype (id, value1_pn_value, value2_pn_value, value2_pn_code, value3_pn_value, value3_pn_code, value3_pn_code_system, value3_pn_code_system_version, value4_pn_value, value4_pn_enpq, value5_pn_value, value5_pn2_value, value5_pn_code, value5_pn2_code, value5_pn_code_system, value5_pn2_code_system, value5_pn_code_system_version, value5_pn2_code_system_version, value6_pn_value, value6_on_value, value6_pn_code, value6_on_code, value6_pn_code_system, value6_on_code_system, value6_pn_code_system_version, value6_on_code_system_version) VALUES (28, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'Mr. John Doe VII', NULL, 'VALUE5_PN2_CODE2', NULL, 'VALUE5_PN2_CODE_SYSTEM1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO en_datatype (id, value1_pn_value, value2_pn_value, value2_pn_code, value3_pn_value, value3_pn_code, value3_pn_code_system, value3_pn_code_system_version, value4_pn_value, value4_pn_enpq, value5_pn_value, value5_pn2_value, value5_pn_code, value5_pn2_code, value5_pn_code_system, value5_pn2_code_system, value5_pn_code_system_version, value5_pn2_code_system_version, value6_pn_value, value6_on_value, value6_pn_code, value6_on_code, value6_pn_code_system, value6_on_code_system, value6_pn_code_system_version, value6_on_code_system_version) VALUES (29, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'Mr. John Doe VIII', NULL, 'VALUE5_PN2_CODE3', NULL, 'VALUE5_PN2_CODE_SYSTEM2', NULL, '2.2', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO en_datatype (id, value1_pn_value, value2_pn_value, value2_pn_code, value3_pn_value, value3_pn_code, value3_pn_code_system, value3_pn_code_system_version, value4_pn_value, value4_pn_enpq, value5_pn_value, value5_pn2_value, value5_pn_code, value5_pn2_code, value5_pn_code_system, value5_pn2_code_system, value5_pn_code_system_version, value5_pn2_code_system_version, value6_pn_value, value6_on_value, value6_pn_code, value6_on_code, value6_pn_code_system, value6_on_code_system, value6_pn_code_system_version, value6_on_code_system_version) VALUES (30, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'Mr. John Doe I', 'Mr. John Doe V', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO en_datatype (id, value1_pn_value, value2_pn_value, value2_pn_code, value3_pn_value, value3_pn_code, value3_pn_code_system, value3_pn_code_system_version, value4_pn_value, value4_pn_enpq, value5_pn_value, value5_pn2_value, value5_pn_code, value5_pn2_code, value5_pn_code_system, value5_pn2_code_system, value5_pn_code_system_version, value5_pn2_code_system_version, value6_pn_value, value6_on_value, value6_pn_code, value6_on_code, value6_pn_code_system, value6_on_code_system, value6_pn_code_system_version, value6_on_code_system_version) VALUES (31, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'Mr. John Doe II', 'Mr. John Doe VI', 'VALUE5_PN_CODE1', 'VALUE5_PN2_CODE1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO en_datatype (id, value1_pn_value, value2_pn_value, value2_pn_code, value3_pn_value, value3_pn_code, value3_pn_code_system, value3_pn_code_system_version, value4_pn_value, value4_pn_enpq, value5_pn_value, value5_pn2_value, value5_pn_code, value5_pn2_code, value5_pn_code_system, value5_pn2_code_system, value5_pn_code_system_version, value5_pn2_code_system_version, value6_pn_value, value6_on_value, value6_pn_code, value6_on_code, value6_pn_code_system, value6_on_code_system, value6_pn_code_system_version, value6_on_code_system_version) VALUES (32, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'Mr. John Doe III', 'Mr. John Doe VII', 'VALUE5_PN_CODE2', 'VALUE5_PN2_CODE2', 'VALUE5_PN_CODE_SYSTEM1', 'VALUE5_PN2_CODE_SYSTEM1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO en_datatype (id, value1_pn_value, value2_pn_value, value2_pn_code, value3_pn_value, value3_pn_code, value3_pn_code_system, value3_pn_code_system_version, value4_pn_value, value4_pn_enpq, value5_pn_value, value5_pn2_value, value5_pn_code, value5_pn2_code, value5_pn_code_system, value5_pn2_code_system, value5_pn_code_system_version, value5_pn2_code_system_version, value6_pn_value, value6_on_value, value6_pn_code, value6_on_code, value6_pn_code_system, value6_on_code_system, value6_pn_code_system_version, value6_on_code_system_version) VALUES (33, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'Mr. John Doe VI', 'Mr. John Doe VIII', 'VALUE5_PN_CODE3', 'VALUE5_PN2_CODE3', 'VALUE5_PN_CODE_SYSTEM2', 'VALUE5_PN2_CODE_SYSTEM2', '2.1', '2.2', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO en_datatype (id, value1_pn_value, value2_pn_value, value2_pn_code, value3_pn_value, value3_pn_code, value3_pn_code_system, value3_pn_code_system_version, value4_pn_value, value4_pn_enpq, value5_pn_value, value5_pn2_value, value5_pn_code, value5_pn2_code, value5_pn_code_system, value5_pn2_code_system, value5_pn_code_system_version, value5_pn2_code_system_version, value6_pn_value, value6_on_value, value6_pn_code, value6_on_code, value6_pn_code_system, value6_on_code_system, value6_pn_code_system_version, value6_on_code_system_version) VALUES (34, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'Mr. John Doe I', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO en_datatype (id, value1_pn_value, value2_pn_value, value2_pn_code, value3_pn_value, value3_pn_code, value3_pn_code_system, value3_pn_code_system_version, value4_pn_value, value4_pn_enpq, value5_pn_value, value5_pn2_value, value5_pn_code, value5_pn2_code, value5_pn_code_system, value5_pn2_code_system, value5_pn_code_system_version, value5_pn2_code_system_version, value6_pn_value, value6_on_value, value6_pn_code, value6_on_code, value6_pn_code_system, value6_on_code_system, value6_pn_code_system_version, value6_on_code_system_version) VALUES (35, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'Mr. John Doe II', NULL, 'VALUE6_PN_CODE1', NULL, NULL, NULL, NULL, NULL);
INSERT INTO en_datatype (id, value1_pn_value, value2_pn_value, value2_pn_code, value3_pn_value, value3_pn_code, value3_pn_code_system, value3_pn_code_system_version, value4_pn_value, value4_pn_enpq, value5_pn_value, value5_pn2_value, value5_pn_code, value5_pn2_code, value5_pn_code_system, value5_pn2_code_system, value5_pn_code_system_version, value5_pn2_code_system_version, value6_pn_value, value6_on_value, value6_pn_code, value6_on_code, value6_pn_code_system, value6_on_code_system, value6_pn_code_system_version, value6_on_code_system_version) VALUES (36, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'Mr. John Doe III', NULL, 'VALUE6_PN_CODE2', NULL, 'VALUE6_PN_CODE_SYSTEM1', NULL, NULL, NULL);
INSERT INTO en_datatype (id, value1_pn_value, value2_pn_value, value2_pn_code, value3_pn_value, value3_pn_code, value3_pn_code_system, value3_pn_code_system_version, value4_pn_value, value4_pn_enpq, value5_pn_value, value5_pn2_value, value5_pn_code, value5_pn2_code, value5_pn_code_system, value5_pn2_code_system, value5_pn_code_system_version, value5_pn2_code_system_version, value6_pn_value, value6_on_value, value6_pn_code, value6_on_code, value6_pn_code_system, value6_on_code_system, value6_pn_code_system_version, value6_on_code_system_version) VALUES (37, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'Mr. John Doe IV', NULL, 'VALUE6_PN_CODE3', NULL, 'VALUE6_PN_CODE_SYSTEM2', NULL, '3.1', NULL);
INSERT INTO en_datatype (id, value1_pn_value, value2_pn_value, value2_pn_code, value3_pn_value, value3_pn_code, value3_pn_code_system, value3_pn_code_system_version, value4_pn_value, value4_pn_enpq, value5_pn_value, value5_pn2_value, value5_pn_code, value5_pn2_code, value5_pn_code_system, value5_pn2_code_system, value5_pn_code_system_version, value5_pn2_code_system_version, value6_pn_value, value6_on_value, value6_pn_code, value6_on_code, value6_pn_code_system, value6_on_code_system, value6_pn_code_system_version, value6_on_code_system_version) VALUES (38, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'Mr. John Doe V', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO en_datatype (id, value1_pn_value, value2_pn_value, value2_pn_code, value3_pn_value, value3_pn_code, value3_pn_code_system, value3_pn_code_system_version, value4_pn_value, value4_pn_enpq, value5_pn_value, value5_pn2_value, value5_pn_code, value5_pn2_code, value5_pn_code_system, value5_pn2_code_system, value5_pn_code_system_version, value5_pn2_code_system_version, value6_pn_value, value6_on_value, value6_pn_code, value6_on_code, value6_pn_code_system, value6_on_code_system, value6_pn_code_system_version, value6_on_code_system_version) VALUES (39, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'Mr. John Doe VI', NULL, 'VALUE6_ON_CODE1', NULL, NULL, NULL, NULL);
INSERT INTO en_datatype (id, value1_pn_value, value2_pn_value, value2_pn_code, value3_pn_value, value3_pn_code, value3_pn_code_system, value3_pn_code_system_version, value4_pn_value, value4_pn_enpq, value5_pn_value, value5_pn2_value, value5_pn_code, value5_pn2_code, value5_pn_code_system, value5_pn2_code_system, value5_pn_code_system_version, value5_pn2_code_system_version, value6_pn_value, value6_on_value, value6_pn_code, value6_on_code, value6_pn_code_system, value6_on_code_system, value6_pn_code_system_version, value6_on_code_system_version) VALUES (40, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'Mr. John Doe VII', NULL, 'VALUE6_ON_CODE1', NULL, 'VALUE6_ON_CODE_SYSTEM1', NULL, NULL);
INSERT INTO en_datatype (id, value1_pn_value, value2_pn_value, value2_pn_code, value3_pn_value, value3_pn_code, value3_pn_code_system, value3_pn_code_system_version, value4_pn_value, value4_pn_enpq, value5_pn_value, value5_pn2_value, value5_pn_code, value5_pn2_code, value5_pn_code_system, value5_pn2_code_system, value5_pn_code_system_version, value5_pn2_code_system_version, value6_pn_value, value6_on_value, value6_pn_code, value6_on_code, value6_pn_code_system, value6_on_code_system, value6_pn_code_system_version, value6_on_code_system_version) VALUES (41, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'Mr. John Doe VIII', NULL, 'VALUE6_ON_CODE2', NULL, 'VALUE6_ON_CODE_SYSTEM2', NULL, '1.1');
INSERT INTO en_datatype (id, value1_pn_value, value2_pn_value, value2_pn_code, value3_pn_value, value3_pn_code, value3_pn_code_system, value3_pn_code_system_version, value4_pn_value, value4_pn_enpq, value5_pn_value, value5_pn2_value, value5_pn_code, value5_pn2_code, value5_pn_code_system, value5_pn2_code_system, value5_pn_code_system_version, value5_pn2_code_system_version, value6_pn_value, value6_on_value, value6_pn_code, value6_on_code, value6_pn_code_system, value6_on_code_system, value6_pn_code_system_version, value6_on_code_system_version) VALUES (42, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'Mr. John Doe I', 'Mr. John Doe V', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO en_datatype (id, value1_pn_value, value2_pn_value, value2_pn_code, value3_pn_value, value3_pn_code, value3_pn_code_system, value3_pn_code_system_version, value4_pn_value, value4_pn_enpq, value5_pn_value, value5_pn2_value, value5_pn_code, value5_pn2_code, value5_pn_code_system, value5_pn2_code_system, value5_pn_code_system_version, value5_pn2_code_system_version, value6_pn_value, value6_on_value, value6_pn_code, value6_on_code, value6_pn_code_system, value6_on_code_system, value6_pn_code_system_version, value6_on_code_system_version) VALUES (43, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'Mr. John Doe II', 'Mr. John Doe VI', 'VALUE6_PN_CODE1', 'VALUE6_ON_CODE1', NULL, NULL, NULL, NULL);
INSERT INTO en_datatype (id, value1_pn_value, value2_pn_value, value2_pn_code, value3_pn_value, value3_pn_code, value3_pn_code_system, value3_pn_code_system_version, value4_pn_value, value4_pn_enpq, value5_pn_value, value5_pn2_value, value5_pn_code, value5_pn2_code, value5_pn_code_system, value5_pn2_code_system, value5_pn_code_system_version, value5_pn2_code_system_version, value6_pn_value, value6_on_value, value6_pn_code, value6_on_code, value6_pn_code_system, value6_on_code_system, value6_pn_code_system_version, value6_on_code_system_version) VALUES (44, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'Mr. John Doe III', 'Mr. John Doe VII', 'VALUE6_PN_CODE2', 'VALUE6_ON_CODE2', 'VALUE6_PN_CODE_SYSTEM2', 'VALUE6_ON_CODE_SYSTEM2', NULL, NULL);
INSERT INTO en_datatype (id, value1_pn_value, value2_pn_value, value2_pn_code, value3_pn_value, value3_pn_code, value3_pn_code_system, value3_pn_code_system_version, value4_pn_value, value4_pn_enpq, value5_pn_value, value5_pn2_value, value5_pn_code, value5_pn2_code, value5_pn_code_system, value5_pn2_code_system, value5_pn_code_system_version, value5_pn2_code_system_version, value6_pn_value, value6_on_value, value6_pn_code, value6_on_code, value6_pn_code_system, value6_on_code_system, value6_pn_code_system_version, value6_on_code_system_version) VALUES (45, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'Mr. John Doe IV', 'Mr. John Doe VIII', 'VALUE6_PN_CODE3', 'VALUE6_ON_CODE3', 'VALUE6_PN_CODE_SYSTEM3', 'VALUE6_ON_CODE_SYSTEM3', '2.1', '1.1');


--
-- TOC entry 2287 (class 0 OID 18701)
-- Dependencies: 1713
-- Data for Name: en_on_datatype; Type: TABLE DATA; 
--

INSERT INTO en_on_datatype (id, value1_on_value) VALUES (1, NULL);
INSERT INTO en_on_datatype (id, value1_on_value) VALUES (2, 'NCI1');
INSERT INTO en_on_datatype (id, value1_on_value) VALUES (3, 'NCI2');
INSERT INTO en_on_datatype (id, value1_on_value) VALUES (4, 'NCI3');
INSERT INTO en_on_datatype (id, value1_on_value) VALUES (5, 'NCI4');
INSERT INTO en_on_datatype (id, value1_on_value) VALUES (6, 'NCI5');


--
-- TOC entry 2288 (class 0 OID 18704)
-- Dependencies: 1714
-- Data for Name: en_pn_datatype; Type: TABLE DATA; 
--

INSERT INTO en_pn_datatype (id, value1_pn_value) VALUES (1, NULL);
INSERT INTO en_pn_datatype (id, value1_pn_value) VALUES (2, 'Mr. John Doe');
INSERT INTO en_pn_datatype (id, value1_pn_value) VALUES (3, 'Mr. John Doe II');
INSERT INTO en_pn_datatype (id, value1_pn_value) VALUES (4, 'Mr. John Doe III');
INSERT INTO en_pn_datatype (id, value1_pn_value) VALUES (5, 'Mr. John Doe IV');
INSERT INTO en_pn_datatype (id, value1_pn_value) VALUES (6, 'Mr. John Doe V');


--
-- TOC entry 2289 (class 0 OID 18707)
-- Dependencies: 1715
-- Data for Name: ii_datatype; Type: TABLE DATA; 
--

INSERT INTO ii_datatype (id, value1_extension, value2_null_flavor, value2_root, value2_extension, value3_null_flavor, value3_extension, value3_identifier_name, value3_reliability, value3_scope, value3_displayable, value4_null_flavor, value4_root, value4_extension, value4_identifier_name, value4_reliability, value4_scope, value4_displayable) VALUES (1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ii_datatype (id, value1_extension, value2_null_flavor, value2_root, value2_extension, value3_null_flavor, value3_extension, value3_identifier_name, value3_reliability, value3_scope, value3_displayable, value4_null_flavor, value4_root, value4_extension, value4_identifier_name, value4_reliability, value4_scope, value4_displayable) VALUES (2, 'II_Extension', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ii_datatype (id, value1_extension, value2_null_flavor, value2_root, value2_extension, value3_null_flavor, value3_extension, value3_identifier_name, value3_reliability, value3_scope, value3_displayable, value4_null_flavor, value4_root, value4_extension, value4_identifier_name, value4_reliability, value4_scope, value4_displayable) VALUES (3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ii_datatype (id, value1_extension, value2_null_flavor, value2_root, value2_extension, value3_null_flavor, value3_extension, value3_identifier_name, value3_reliability, value3_scope, value3_displayable, value4_null_flavor, value4_root, value4_extension, value4_identifier_name, value4_reliability, value4_scope, value4_displayable) VALUES (4, NULL, 'NI', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ii_datatype (id, value1_extension, value2_null_flavor, value2_root, value2_extension, value3_null_flavor, value3_extension, value3_identifier_name, value3_reliability, value3_scope, value3_displayable, value4_null_flavor, value4_root, value4_extension, value4_identifier_name, value4_reliability, value4_scope, value4_displayable) VALUES (5, NULL, NULL, 'II_VALUE2_ROOT', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ii_datatype (id, value1_extension, value2_null_flavor, value2_root, value2_extension, value3_null_flavor, value3_extension, value3_identifier_name, value3_reliability, value3_scope, value3_displayable, value4_null_flavor, value4_root, value4_extension, value4_identifier_name, value4_reliability, value4_scope, value4_displayable) VALUES (6, NULL, NULL, 'II_VALUE2_ROOT', 'II_VALUE2_EXTENSION', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ii_datatype (id, value1_extension, value2_null_flavor, value2_root, value2_extension, value3_null_flavor, value3_extension, value3_identifier_name, value3_reliability, value3_scope, value3_displayable, value4_null_flavor, value4_root, value4_extension, value4_identifier_name, value4_reliability, value4_scope, value4_displayable) VALUES (7, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ii_datatype (id, value1_extension, value2_null_flavor, value2_root, value2_extension, value3_null_flavor, value3_extension, value3_identifier_name, value3_reliability, value3_scope, value3_displayable, value4_null_flavor, value4_root, value4_extension, value4_identifier_name, value4_reliability, value4_scope, value4_displayable) VALUES (8, NULL, NULL, NULL, NULL, 'UNK', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ii_datatype (id, value1_extension, value2_null_flavor, value2_root, value2_extension, value3_null_flavor, value3_extension, value3_identifier_name, value3_reliability, value3_scope, value3_displayable, value4_null_flavor, value4_root, value4_extension, value4_identifier_name, value4_reliability, value4_scope, value4_displayable) VALUES (9, NULL, NULL, NULL, NULL, NULL, 'II_VALUE3_EXTENSION', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ii_datatype (id, value1_extension, value2_null_flavor, value2_root, value2_extension, value3_null_flavor, value3_extension, value3_identifier_name, value3_reliability, value3_scope, value3_displayable, value4_null_flavor, value4_root, value4_extension, value4_identifier_name, value4_reliability, value4_scope, value4_displayable) VALUES (10, NULL, NULL, NULL, NULL, NULL, 'II_VALUE3_EXTENSION', 'II_VALUE3_IDENTIFIER_NAME', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ii_datatype (id, value1_extension, value2_null_flavor, value2_root, value2_extension, value3_null_flavor, value3_extension, value3_identifier_name, value3_reliability, value3_scope, value3_displayable, value4_null_flavor, value4_root, value4_extension, value4_identifier_name, value4_reliability, value4_scope, value4_displayable) VALUES (11, NULL, NULL, NULL, NULL, NULL, 'II_VALUE3_EXTENSION', 'II_VALUE3_IDENTIFIER_NAME', 'ISS', 'BUSN', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ii_datatype (id, value1_extension, value2_null_flavor, value2_root, value2_extension, value3_null_flavor, value3_extension, value3_identifier_name, value3_reliability, value3_scope, value3_displayable, value4_null_flavor, value4_root, value4_extension, value4_identifier_name, value4_reliability, value4_scope, value4_displayable) VALUES (12, NULL, NULL, NULL, NULL, NULL, 'II_VALUE3_EXTENSION', 'II_VALUE3_IDENTIFIER_NAME', 'VRF', 'BUSN', '0', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ii_datatype (id, value1_extension, value2_null_flavor, value2_root, value2_extension, value3_null_flavor, value3_extension, value3_identifier_name, value3_reliability, value3_scope, value3_displayable, value4_null_flavor, value4_root, value4_extension, value4_identifier_name, value4_reliability, value4_scope, value4_displayable) VALUES (13, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ii_datatype (id, value1_extension, value2_null_flavor, value2_root, value2_extension, value3_null_flavor, value3_extension, value3_identifier_name, value3_reliability, value3_scope, value3_displayable, value4_null_flavor, value4_root, value4_extension, value4_identifier_name, value4_reliability, value4_scope, value4_displayable) VALUES (14, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'INV', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ii_datatype (id, value1_extension, value2_null_flavor, value2_root, value2_extension, value3_null_flavor, value3_extension, value3_identifier_name, value3_reliability, value3_scope, value3_displayable, value4_null_flavor, value4_root, value4_extension, value4_identifier_name, value4_reliability, value4_scope, value4_displayable) VALUES (15, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'II_VALUE4_ROOT', 'II_VALUE4_EXTENSION', NULL, NULL, NULL, NULL);
INSERT INTO ii_datatype (id, value1_extension, value2_null_flavor, value2_root, value2_extension, value3_null_flavor, value3_extension, value3_identifier_name, value3_reliability, value3_scope, value3_displayable, value4_null_flavor, value4_root, value4_extension, value4_identifier_name, value4_reliability, value4_scope, value4_displayable) VALUES (16, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'II_VALUE4_ROOT', 'II_VALUE4_EXTENSION', 'II_VALUE4_IDENTIFIER_NAME', NULL, NULL, NULL);
INSERT INTO ii_datatype (id, value1_extension, value2_null_flavor, value2_root, value2_extension, value3_null_flavor, value3_extension, value3_identifier_name, value3_reliability, value3_scope, value3_displayable, value4_null_flavor, value4_root, value4_extension, value4_identifier_name, value4_reliability, value4_scope, value4_displayable) VALUES (17, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'II_VALUE4_ROOT', 'II_VALUE4_EXTENSION', 'II_VALUE4_IDENTIFIER_NAME', 'UNV', NULL, NULL);
INSERT INTO ii_datatype (id, value1_extension, value2_null_flavor, value2_root, value2_extension, value3_null_flavor, value3_extension, value3_identifier_name, value3_reliability, value3_scope, value3_displayable, value4_null_flavor, value4_root, value4_extension, value4_identifier_name, value4_reliability, value4_scope, value4_displayable) VALUES (18, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'II_VALUE4_ROOT', 'II_VALUE4_EXTENSION', 'II_VALUE4_IDENTIFIER_NAME', 'ISS', 'BUSN', NULL);
INSERT INTO ii_datatype (id, value1_extension, value2_null_flavor, value2_root, value2_extension, value3_null_flavor, value3_extension, value3_identifier_name, value3_reliability, value3_scope, value3_displayable, value4_null_flavor, value4_root, value4_extension, value4_identifier_name, value4_reliability, value4_scope, value4_displayable) VALUES (19, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'II_VALUE4_ROOT', 'II_VALUE4_EXTENSION', 'II_VALUE4_IDENTIFIER_NAME', 'VRF', 'BUSN', '0');


--
-- TOC entry 2290 (class 0 OID 18713)
-- Dependencies: 1716
-- Data for Name: int_datatype; Type: TABLE DATA; 
--

INSERT INTO int_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (1, NULL, NULL, NULL);
INSERT INTO int_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (2, 1.0, NULL, NULL);
INSERT INTO int_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (3, 2.0, NULL, NULL);
INSERT INTO int_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (4, 3.0, NULL, NULL);
INSERT INTO int_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (5, 4.0, NULL, NULL);
INSERT INTO int_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (6, 5.0, NULL, NULL);
INSERT INTO int_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (7, NULL, NULL, 6.0);
INSERT INTO int_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (8, NULL, NULL, 7.0);
INSERT INTO int_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (9, NULL, NULL, 8.0);
INSERT INTO int_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (10, NULL, 'NI', NULL);
INSERT INTO int_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (11, NULL, 'NI', NULL);
INSERT INTO int_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (12, NULL, 'NI', NULL);


--
-- TOC entry 2291 (class 0 OID 18719)
-- Dependencies: 1717
-- Data for Name: ivl_int; Type: TABLE DATA; 
--

INSERT INTO ivl_int (id, value1_low_value, value1_high_value, value2_high_value, value2_lowclosed, value3_any_value, value3_low_value, value3_high_closed, value4_low_value, value4_high_value, value4_width_value, value4_null_flavor) VALUES (1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ivl_int (id, value1_low_value, value1_high_value, value2_high_value, value2_lowclosed, value3_any_value, value3_low_value, value3_high_closed, value4_low_value, value4_high_value, value4_width_value, value4_null_flavor) VALUES (2, 1.0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ivl_int (id, value1_low_value, value1_high_value, value2_high_value, value2_lowclosed, value3_any_value, value3_low_value, value3_high_closed, value4_low_value, value4_high_value, value4_width_value, value4_null_flavor) VALUES (3, NULL, 10.0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ivl_int (id, value1_low_value, value1_high_value, value2_high_value, value2_lowclosed, value3_any_value, value3_low_value, value3_high_closed, value4_low_value, value4_high_value, value4_width_value, value4_null_flavor) VALUES (4, 1.0, 10.0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ivl_int (id, value1_low_value, value1_high_value, value2_high_value, value2_lowclosed, value3_any_value, value3_low_value, value3_high_closed, value4_low_value, value4_high_value, value4_width_value, value4_null_flavor) VALUES (5, 1.0, 1.0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ivl_int (id, value1_low_value, value1_high_value, value2_high_value, value2_lowclosed, value3_any_value, value3_low_value, value3_high_closed, value4_low_value, value4_high_value, value4_width_value, value4_null_flavor) VALUES (6, 2.0, 1.0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ivl_int (id, value1_low_value, value1_high_value, value2_high_value, value2_lowclosed, value3_any_value, value3_low_value, value3_high_closed, value4_low_value, value4_high_value, value4_width_value, value4_null_flavor) VALUES (7, NULL, NULL, 10.0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ivl_int (id, value1_low_value, value1_high_value, value2_high_value, value2_lowclosed, value3_any_value, value3_low_value, value3_high_closed, value4_low_value, value4_high_value, value4_width_value, value4_null_flavor) VALUES (8, NULL, NULL, NULL, '1', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ivl_int (id, value1_low_value, value1_high_value, value2_high_value, value2_lowclosed, value3_any_value, value3_low_value, value3_high_closed, value4_low_value, value4_high_value, value4_width_value, value4_null_flavor) VALUES (9, NULL, NULL, 10.0, '1', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ivl_int (id, value1_low_value, value1_high_value, value2_high_value, value2_lowclosed, value3_any_value, value3_low_value, value3_high_closed, value4_low_value, value4_high_value, value4_width_value, value4_null_flavor) VALUES (10, NULL, NULL, 1.0, '0', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ivl_int (id, value1_low_value, value1_high_value, value2_high_value, value2_lowclosed, value3_any_value, value3_low_value, value3_high_closed, value4_low_value, value4_high_value, value4_width_value, value4_null_flavor) VALUES (11, NULL, NULL, 1.0, '1', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ivl_int (id, value1_low_value, value1_high_value, value2_high_value, value2_lowclosed, value3_any_value, value3_low_value, value3_high_closed, value4_low_value, value4_high_value, value4_width_value, value4_null_flavor) VALUES (12, NULL, NULL, NULL, NULL, null, 11.0, '1', NULL, NULL, NULL, NULL);
INSERT INTO ivl_int (id, value1_low_value, value1_high_value, value2_high_value, value2_lowclosed, value3_any_value, value3_low_value, value3_high_closed, value4_low_value, value4_high_value, value4_width_value, value4_null_flavor) VALUES (13, NULL, NULL, NULL, NULL, null, 12.0, '1', NULL, NULL, NULL, NULL);
INSERT INTO ivl_int (id, value1_low_value, value1_high_value, value2_high_value, value2_lowclosed, value3_any_value, value3_low_value, value3_high_closed, value4_low_value, value4_high_value, value4_width_value, value4_null_flavor) VALUES (14, NULL, NULL, NULL, NULL, null, 13.0, '1', NULL, NULL, NULL, NULL);
INSERT INTO ivl_int (id, value1_low_value, value1_high_value, value2_high_value, value2_lowclosed, value3_any_value, value3_low_value, value3_high_closed, value4_low_value, value4_high_value, value4_width_value, value4_null_flavor) VALUES (15, NULL, NULL, NULL, NULL, 8.0, null, '1', NULL, NULL, NULL, NULL);
INSERT INTO ivl_int (id, value1_low_value, value1_high_value, value2_high_value, value2_lowclosed, value3_any_value, value3_low_value, value3_high_closed, value4_low_value, value4_high_value, value4_width_value, value4_null_flavor) VALUES (16, NULL, NULL, NULL, NULL, 9.0, null, '1', NULL, NULL, NULL, NULL);
INSERT INTO ivl_int (id, value1_low_value, value1_high_value, value2_high_value, value2_lowclosed, value3_any_value, value3_low_value, value3_high_closed, value4_low_value, value4_high_value, value4_width_value, value4_null_flavor) VALUES (17, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 5.0, 11.0, 5, 'NI');
INSERT INTO ivl_int (id, value1_low_value, value1_high_value, value2_high_value, value2_lowclosed, value3_any_value, value3_low_value, value3_high_closed, value4_low_value, value4_high_value, value4_width_value, value4_null_flavor) VALUES (18, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 6.0, 12.0, 4, 'NI');
INSERT INTO ivl_int (id, value1_low_value, value1_high_value, value2_high_value, value2_lowclosed, value3_any_value, value3_low_value, value3_high_closed, value4_low_value, value4_high_value, value4_width_value, value4_null_flavor) VALUES (19, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 7.0, 13.0, 3, 'NI');
INSERT INTO ivl_int (id, value1_low_value, value1_high_value, value2_high_value, value2_lowclosed, value3_any_value, value3_low_value, value3_high_closed, value4_low_value, value4_high_value, value4_width_value, value4_null_flavor) VALUES (20, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 8.0, 14.0, NULL, NULL);
INSERT INTO ivl_int (id, value1_low_value, value1_high_value, value2_high_value, value2_lowclosed, value3_any_value, value3_low_value, value3_high_closed, value4_low_value, value4_high_value, value4_width_value, value4_null_flavor) VALUES (21, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 9.0, NULL, NULL, 'NI');


--
-- TOC entry 2292 (class 0 OID 18725)
-- Dependencies: 1718
-- Data for Name: ivl_pq; Type: TABLE DATA; 
--

INSERT INTO ivl_pq (id, value1_low_value, value2_low_value, value2_low_precision, value2_low_unit, value3_low_value, value3_low_precision, value3_low_unit, value3_null_flavor, value4_high_value, value4_high_precision, value4_high_unit, value4_high_closed, value4_high_null_flavor, value4_low_value, value4_low_precision, value4_low_unit, value4_low_null_flavor, value4_low_closed, value4_width_value, value4_width_precision, value4_width_unit, value4_width_null_flavor, value4_null_flavor) VALUES (1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ivl_pq (id, value1_low_value, value2_low_value, value2_low_precision, value2_low_unit, value3_low_value, value3_low_precision, value3_low_unit, value3_null_flavor, value4_high_value, value4_high_precision, value4_high_unit, value4_high_closed, value4_high_null_flavor, value4_low_value, value4_low_precision, value4_low_unit, value4_low_null_flavor, value4_low_closed, value4_width_value, value4_width_precision, value4_width_unit, value4_width_null_flavor, value4_null_flavor) VALUES (2, 1.1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ivl_pq (id, value1_low_value, value2_low_value, value2_low_precision, value2_low_unit, value3_low_value, value3_low_precision, value3_low_unit, value3_null_flavor, value4_high_value, value4_high_precision, value4_high_unit, value4_high_closed, value4_high_null_flavor, value4_low_value, value4_low_precision, value4_low_unit, value4_low_null_flavor, value4_low_closed, value4_width_value, value4_width_precision, value4_width_unit, value4_width_null_flavor, value4_null_flavor) VALUES (3, 2.1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ivl_pq (id, value1_low_value, value2_low_value, value2_low_precision, value2_low_unit, value3_low_value, value3_low_precision, value3_low_unit, value3_null_flavor, value4_high_value, value4_high_precision, value4_high_unit, value4_high_closed, value4_high_null_flavor, value4_low_value, value4_low_precision, value4_low_unit, value4_low_null_flavor, value4_low_closed, value4_width_value, value4_width_precision, value4_width_unit, value4_width_null_flavor, value4_null_flavor) VALUES (4, 3.1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ivl_pq (id, value1_low_value, value2_low_value, value2_low_precision, value2_low_unit, value3_low_value, value3_low_precision, value3_low_unit, value3_null_flavor, value4_high_value, value4_high_precision, value4_high_unit, value4_high_closed, value4_high_null_flavor, value4_low_value, value4_low_precision, value4_low_unit, value4_low_null_flavor, value4_low_closed, value4_width_value, value4_width_precision, value4_width_unit, value4_width_null_flavor, value4_null_flavor) VALUES (5, 4.1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ivl_pq (id, value1_low_value, value2_low_value, value2_low_precision, value2_low_unit, value3_low_value, value3_low_precision, value3_low_unit, value3_null_flavor, value4_high_value, value4_high_precision, value4_high_unit, value4_high_closed, value4_high_null_flavor, value4_low_value, value4_low_precision, value4_low_unit, value4_low_null_flavor, value4_low_closed, value4_width_value, value4_width_precision, value4_width_unit, value4_width_null_flavor, value4_null_flavor) VALUES (6, 5.1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ivl_pq (id, value1_low_value, value2_low_value, value2_low_precision, value2_low_unit, value3_low_value, value3_low_precision, value3_low_unit, value3_null_flavor, value4_high_value, value4_high_precision, value4_high_unit, value4_high_closed, value4_high_null_flavor, value4_low_value, value4_low_precision, value4_low_unit, value4_low_null_flavor, value4_low_closed, value4_width_value, value4_width_precision, value4_width_unit, value4_width_null_flavor, value4_null_flavor) VALUES (7, NULL, 221.1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ivl_pq (id, value1_low_value, value2_low_value, value2_low_precision, value2_low_unit, value3_low_value, value3_low_precision, value3_low_unit, value3_null_flavor, value4_high_value, value4_high_precision, value4_high_unit, value4_high_closed, value4_high_null_flavor, value4_low_value, value4_low_precision, value4_low_unit, value4_low_null_flavor, value4_low_closed, value4_width_value, value4_width_precision, value4_width_unit, value4_width_null_flavor, value4_null_flavor) VALUES (8, NULL, 222.1, 2.0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ivl_pq (id, value1_low_value, value2_low_value, value2_low_precision, value2_low_unit, value3_low_value, value3_low_precision, value3_low_unit, value3_null_flavor, value4_high_value, value4_high_precision, value4_high_unit, value4_high_closed, value4_high_null_flavor, value4_low_value, value4_low_precision, value4_low_unit, value4_low_null_flavor, value4_low_closed, value4_width_value, value4_width_precision, value4_width_unit, value4_width_null_flavor, value4_null_flavor) VALUES (9, NULL, 221.1, 2.0, 'Unit', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ivl_pq (id, value1_low_value, value2_low_value, value2_low_precision, value2_low_unit, value3_low_value, value3_low_precision, value3_low_unit, value3_null_flavor, value4_high_value, value4_high_precision, value4_high_unit, value4_high_closed, value4_high_null_flavor, value4_low_value, value4_low_precision, value4_low_unit, value4_low_null_flavor, value4_low_closed, value4_width_value, value4_width_precision, value4_width_unit, value4_width_null_flavor, value4_null_flavor) VALUES (10, NULL, 222.1, NULL, 'Unit', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ivl_pq (id, value1_low_value, value2_low_value, value2_low_precision, value2_low_unit, value3_low_value, value3_low_precision, value3_low_unit, value3_null_flavor, value4_high_value, value4_high_precision, value4_high_unit, value4_high_closed, value4_high_null_flavor, value4_low_value, value4_low_precision, value4_low_unit, value4_low_null_flavor, value4_low_closed, value4_width_value, value4_width_precision, value4_width_unit, value4_width_null_flavor, value4_null_flavor) VALUES (11, NULL, 223.1, 2.0, 'Unit', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ivl_pq (id, value1_low_value, value2_low_value, value2_low_precision, value2_low_unit, value3_low_value, value3_low_precision, value3_low_unit, value3_null_flavor, value4_high_value, value4_high_precision, value4_high_unit, value4_high_closed, value4_high_null_flavor, value4_low_value, value4_low_precision, value4_low_unit, value4_low_null_flavor, value4_low_closed, value4_width_value, value4_width_precision, value4_width_unit, value4_width_null_flavor, value4_null_flavor) VALUES (12, NULL, 224.1, 2.0, 'Unit', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ivl_pq (id, value1_low_value, value2_low_value, value2_low_precision, value2_low_unit, value3_low_value, value3_low_precision, value3_low_unit, value3_null_flavor, value4_high_value, value4_high_precision, value4_high_unit, value4_high_closed, value4_high_null_flavor, value4_low_value, value4_low_precision, value4_low_unit, value4_low_null_flavor, value4_low_closed, value4_width_value, value4_width_precision, value4_width_unit, value4_width_null_flavor, value4_null_flavor) VALUES (13, NULL, NULL, NULL, NULL, 224.1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ivl_pq (id, value1_low_value, value2_low_value, value2_low_precision, value2_low_unit, value3_low_value, value3_low_precision, value3_low_unit, value3_null_flavor, value4_high_value, value4_high_precision, value4_high_unit, value4_high_closed, value4_high_null_flavor, value4_low_value, value4_low_precision, value4_low_unit, value4_low_null_flavor, value4_low_closed, value4_width_value, value4_width_precision, value4_width_unit, value4_width_null_flavor, value4_null_flavor) VALUES (14, NULL, NULL, NULL, NULL, 224.1, 2.0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ivl_pq (id, value1_low_value, value2_low_value, value2_low_precision, value2_low_unit, value3_low_value, value3_low_precision, value3_low_unit, value3_null_flavor, value4_high_value, value4_high_precision, value4_high_unit, value4_high_closed, value4_high_null_flavor, value4_low_value, value4_low_precision, value4_low_unit, value4_low_null_flavor, value4_low_closed, value4_width_value, value4_width_precision, value4_width_unit, value4_width_null_flavor, value4_null_flavor) VALUES (15, NULL, NULL, NULL, NULL, NULL, 2.0, 'Unit', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ivl_pq (id, value1_low_value, value2_low_value, value2_low_precision, value2_low_unit, value3_low_value, value3_low_precision, value3_low_unit, value3_null_flavor, value4_high_value, value4_high_precision, value4_high_unit, value4_high_closed, value4_high_null_flavor, value4_low_value, value4_low_precision, value4_low_unit, value4_low_null_flavor, value4_low_closed, value4_width_value, value4_width_precision, value4_width_unit, value4_width_null_flavor, value4_null_flavor) VALUES (16, NULL, NULL, NULL, NULL, 224.1, 2.0, 'Unit', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ivl_pq (id, value1_low_value, value2_low_value, value2_low_precision, value2_low_unit, value3_low_value, value3_low_precision, value3_low_unit, value3_null_flavor, value4_high_value, value4_high_precision, value4_high_unit, value4_high_closed, value4_high_null_flavor, value4_low_value, value4_low_precision, value4_low_unit, value4_low_null_flavor, value4_low_closed, value4_width_value, value4_width_precision, value4_width_unit, value4_width_null_flavor, value4_null_flavor) VALUES (17, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'NI', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ivl_pq (id, value1_low_value, value2_low_value, value2_low_precision, value2_low_unit, value3_low_value, value3_low_precision, value3_low_unit, value3_null_flavor, value4_high_value, value4_high_precision, value4_high_unit, value4_high_closed, value4_high_null_flavor, value4_low_value, value4_low_precision, value4_low_unit, value4_low_null_flavor, value4_low_closed, value4_width_value, value4_width_precision, value4_width_unit, value4_width_null_flavor, value4_null_flavor) VALUES (18, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 5.1, 2.0, 'VALUE4_HIGH_UNIT1', '1', NULL, 1.1, 2.0, 'VALUE4_LOW_UNIT', NULL, '1', 5.1, 2.0, 'VALUE4_WIDTH_UNIT1', NULL, NULL);
INSERT INTO ivl_pq (id, value1_low_value, value2_low_value, value2_low_precision, value2_low_unit, value3_low_value, value3_low_precision, value3_low_unit, value3_null_flavor, value4_high_value, value4_high_precision, value4_high_unit, value4_high_closed, value4_high_null_flavor, value4_low_value, value4_low_precision, value4_low_unit, value4_low_null_flavor, value4_low_closed, value4_width_value, value4_width_precision, value4_width_unit, value4_width_null_flavor, value4_null_flavor) VALUES (19, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 6.1, 2.0, 'VALUE4_HIGH_UNIT2', '1', NULL, 1.1, 2.0, 'VALUE4_LOW_UNIT', NULL, '1', 5.1, 2.0, 'VALUE4_WIDTH_UNIT2', NULL, NULL);
INSERT INTO ivl_pq (id, value1_low_value, value2_low_value, value2_low_precision, value2_low_unit, value3_low_value, value3_low_precision, value3_low_unit, value3_null_flavor, value4_high_value, value4_high_precision, value4_high_unit, value4_high_closed, value4_high_null_flavor, value4_low_value, value4_low_precision, value4_low_unit, value4_low_null_flavor, value4_low_closed, value4_width_value, value4_width_precision, value4_width_unit, value4_width_null_flavor, value4_null_flavor) VALUES (20, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'NI', NULL, NULL, NULL, 'NI', NULL, NULL, NULL, NULL, 'NI', 'NA');
INSERT INTO ivl_pq (id, value1_low_value, value2_low_value, value2_low_precision, value2_low_unit, value3_low_value, value3_low_precision, value3_low_unit, value3_null_flavor, value4_high_value, value4_high_precision, value4_high_unit, value4_high_closed, value4_high_null_flavor, value4_low_value, value4_low_precision, value4_low_unit, value4_low_null_flavor, value4_low_closed, value4_width_value, value4_width_precision, value4_width_unit, value4_width_null_flavor, value4_null_flavor) VALUES (21, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 8.1, 2.0, 'VALUE4_HIGH_UNIT4', '1', NULL, NULL, NULL, NULL, 'NI', '1', 5.0, 2.0, 'VALUE4_WIDTH_UNIT4', NULL, NULL);
INSERT INTO ivl_pq (id, value1_low_value, value2_low_value, value2_low_precision, value2_low_unit, value3_low_value, value3_low_precision, value3_low_unit, value3_null_flavor, value4_high_value, value4_high_precision, value4_high_unit, value4_high_closed, value4_high_null_flavor, value4_low_value, value4_low_precision, value4_low_unit, value4_low_null_flavor, value4_low_closed, value4_width_value, value4_width_precision, value4_width_unit, value4_width_null_flavor, value4_null_flavor) VALUES (22, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 9.1, 2.0, 'VALUE4_HIGH_UNIT5', '1', NULL, 1.1, 2.0, NULL, NULL, '1', NULL, NULL, NULL, 'NI', NULL);


--
-- TOC entry 2293 (class 0 OID 18731)
-- Dependencies: 1719
-- Data for Name: ivl_pqv; Type: TABLE DATA; 
--

INSERT INTO ivl_pqv (id, value1_low_value, value1_high_value, value2_low_value, value2_low_precision, value2_high_closed, value3_low_value, value3_low_precision, value3_high_value, value3_high_precision, value3_high_null_flavor, value4_high_value, value4_high_precision, value4_high_null_flavor, value4_low_value, value4_low_precision, value4_low_null_flavor, value4_width_value, value4_width_precision, value4_width_null_flavor) VALUES (1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ivl_pqv (id, value1_low_value, value1_high_value, value2_low_value, value2_low_precision, value2_high_closed, value3_low_value, value3_low_precision, value3_high_value, value3_high_precision, value3_high_null_flavor, value4_high_value, value4_high_precision, value4_high_null_flavor, value4_low_value, value4_low_precision, value4_low_null_flavor, value4_width_value, value4_width_precision, value4_width_null_flavor) VALUES (2, 1.1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ivl_pqv (id, value1_low_value, value1_high_value, value2_low_value, value2_low_precision, value2_high_closed, value3_low_value, value3_low_precision, value3_high_value, value3_high_precision, value3_high_null_flavor, value4_high_value, value4_high_precision, value4_high_null_flavor, value4_low_value, value4_low_precision, value4_low_null_flavor, value4_width_value, value4_width_precision, value4_width_null_flavor) VALUES (3, 2.1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ivl_pqv (id, value1_low_value, value1_high_value, value2_low_value, value2_low_precision, value2_high_closed, value3_low_value, value3_low_precision, value3_high_value, value3_high_precision, value3_high_null_flavor, value4_high_value, value4_high_precision, value4_high_null_flavor, value4_low_value, value4_low_precision, value4_low_null_flavor, value4_width_value, value4_width_precision, value4_width_null_flavor) VALUES (4, 3.1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ivl_pqv (id, value1_low_value, value1_high_value, value2_low_value, value2_low_precision, value2_high_closed, value3_low_value, value3_low_precision, value3_high_value, value3_high_precision, value3_high_null_flavor, value4_high_value, value4_high_precision, value4_high_null_flavor, value4_low_value, value4_low_precision, value4_low_null_flavor, value4_width_value, value4_width_precision, value4_width_null_flavor) VALUES (5, 4.1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ivl_pqv (id, value1_low_value, value1_high_value, value2_low_value, value2_low_precision, value2_high_closed, value3_low_value, value3_low_precision, value3_high_value, value3_high_precision, value3_high_null_flavor, value4_high_value, value4_high_precision, value4_high_null_flavor, value4_low_value, value4_low_precision, value4_low_null_flavor, value4_width_value, value4_width_precision, value4_width_null_flavor) VALUES (6, 5.1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ivl_pqv (id, value1_low_value, value1_high_value, value2_low_value, value2_low_precision, value2_high_closed, value3_low_value, value3_low_precision, value3_high_value, value3_high_precision, value3_high_null_flavor, value4_high_value, value4_high_precision, value4_high_null_flavor, value4_low_value, value4_low_precision, value4_low_null_flavor, value4_width_value, value4_width_precision, value4_width_null_flavor) VALUES (7, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ivl_pqv (id, value1_low_value, value1_high_value, value2_low_value, value2_low_precision, value2_high_closed, value3_low_value, value3_low_precision, value3_high_value, value3_high_precision, value3_high_null_flavor, value4_high_value, value4_high_precision, value4_high_null_flavor, value4_low_value, value4_low_precision, value4_low_null_flavor, value4_width_value, value4_width_precision, value4_width_null_flavor) VALUES (8, NULL, NULL, 1.1, 2.0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ivl_pqv (id, value1_low_value, value1_high_value, value2_low_value, value2_low_precision, value2_high_closed, value3_low_value, value3_low_precision, value3_high_value, value3_high_precision, value3_high_null_flavor, value4_high_value, value4_high_precision, value4_high_null_flavor, value4_low_value, value4_low_precision, value4_low_null_flavor, value4_width_value, value4_width_precision, value4_width_null_flavor) VALUES (9, NULL, NULL, 2.1, 2.0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ivl_pqv (id, value1_low_value, value1_high_value, value2_low_value, value2_low_precision, value2_high_closed, value3_low_value, value3_low_precision, value3_high_value, value3_high_precision, value3_high_null_flavor, value4_high_value, value4_high_precision, value4_high_null_flavor, value4_low_value, value4_low_precision, value4_low_null_flavor, value4_width_value, value4_width_precision, value4_width_null_flavor) VALUES (10, NULL, NULL, 3.1, 2.0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ivl_pqv (id, value1_low_value, value1_high_value, value2_low_value, value2_low_precision, value2_high_closed, value3_low_value, value3_low_precision, value3_high_value, value3_high_precision, value3_high_null_flavor, value4_high_value, value4_high_precision, value4_high_null_flavor, value4_low_value, value4_low_precision, value4_low_null_flavor, value4_width_value, value4_width_precision, value4_width_null_flavor) VALUES (11, NULL, NULL, 4.1, 2.0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ivl_pqv (id, value1_low_value, value1_high_value, value2_low_value, value2_low_precision, value2_high_closed, value3_low_value, value3_low_precision, value3_high_value, value3_high_precision, value3_high_null_flavor, value4_high_value, value4_high_precision, value4_high_null_flavor, value4_low_value, value4_low_precision, value4_low_null_flavor, value4_width_value, value4_width_precision, value4_width_null_flavor) VALUES (12, NULL, NULL, 5.1, 2.0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ivl_pqv (id, value1_low_value, value1_high_value, value2_low_value, value2_low_precision, value2_high_closed, value3_low_value, value3_low_precision, value3_high_value, value3_high_precision, value3_high_null_flavor, value4_high_value, value4_high_precision, value4_high_null_flavor, value4_low_value, value4_low_precision, value4_low_null_flavor, value4_width_value, value4_width_precision, value4_width_null_flavor) VALUES (13, NULL, NULL, 1.1, 2.0, '1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ivl_pqv (id, value1_low_value, value1_high_value, value2_low_value, value2_low_precision, value2_high_closed, value3_low_value, value3_low_precision, value3_high_value, value3_high_precision, value3_high_null_flavor, value4_high_value, value4_high_precision, value4_high_null_flavor, value4_low_value, value4_low_precision, value4_low_null_flavor, value4_width_value, value4_width_precision, value4_width_null_flavor) VALUES (14, NULL, NULL, 2.1, 2.0, '1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ivl_pqv (id, value1_low_value, value1_high_value, value2_low_value, value2_low_precision, value2_high_closed, value3_low_value, value3_low_precision, value3_high_value, value3_high_precision, value3_high_null_flavor, value4_high_value, value4_high_precision, value4_high_null_flavor, value4_low_value, value4_low_precision, value4_low_null_flavor, value4_width_value, value4_width_precision, value4_width_null_flavor) VALUES (15, NULL, NULL, 3.1, 2.0, '1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ivl_pqv (id, value1_low_value, value1_high_value, value2_low_value, value2_low_precision, value2_high_closed, value3_low_value, value3_low_precision, value3_high_value, value3_high_precision, value3_high_null_flavor, value4_high_value, value4_high_precision, value4_high_null_flavor, value4_low_value, value4_low_precision, value4_low_null_flavor, value4_width_value, value4_width_precision, value4_width_null_flavor) VALUES (16, NULL, NULL, 4.1, 2.0, '1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ivl_pqv (id, value1_low_value, value1_high_value, value2_low_value, value2_low_precision, value2_high_closed, value3_low_value, value3_low_precision, value3_high_value, value3_high_precision, value3_high_null_flavor, value4_high_value, value4_high_precision, value4_high_null_flavor, value4_low_value, value4_low_precision, value4_low_null_flavor, value4_width_value, value4_width_precision, value4_width_null_flavor) VALUES (17, NULL, NULL, 5.1, 2.0, '1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ivl_pqv (id, value1_low_value, value1_high_value, value2_low_value, value2_low_precision, value2_high_closed, value3_low_value, value3_low_precision, value3_high_value, value3_high_precision, value3_high_null_flavor, value4_high_value, value4_high_precision, value4_high_null_flavor, value4_low_value, value4_low_precision, value4_low_null_flavor, value4_width_value, value4_width_precision, value4_width_null_flavor) VALUES (18, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ivl_pqv (id, value1_low_value, value1_high_value, value2_low_value, value2_low_precision, value2_high_closed, value3_low_value, value3_low_precision, value3_high_value, value3_high_precision, value3_high_null_flavor, value4_high_value, value4_high_precision, value4_high_null_flavor, value4_low_value, value4_low_precision, value4_low_null_flavor, value4_width_value, value4_width_precision, value4_width_null_flavor) VALUES (19, NULL, NULL, NULL, NULL, NULL, 1.1, 2.0, 610.1, 2.0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ivl_pqv (id, value1_low_value, value1_high_value, value2_low_value, value2_low_precision, value2_high_closed, value3_low_value, value3_low_precision, value3_high_value, value3_high_precision, value3_high_null_flavor, value4_high_value, value4_high_precision, value4_high_null_flavor, value4_low_value, value4_low_precision, value4_low_null_flavor, value4_width_value, value4_width_precision, value4_width_null_flavor) VALUES (20, NULL, NULL, NULL, NULL, NULL, 2.1, 2.0, 620.1, 2.0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ivl_pqv (id, value1_low_value, value1_high_value, value2_low_value, value2_low_precision, value2_high_closed, value3_low_value, value3_low_precision, value3_high_value, value3_high_precision, value3_high_null_flavor, value4_high_value, value4_high_precision, value4_high_null_flavor, value4_low_value, value4_low_precision, value4_low_null_flavor, value4_width_value, value4_width_precision, value4_width_null_flavor) VALUES (21, NULL, NULL, NULL, NULL, NULL, 3.1, 2.0, 630.1, 2.0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ivl_pqv (id, value1_low_value, value1_high_value, value2_low_value, value2_low_precision, value2_high_closed, value3_low_value, value3_low_precision, value3_high_value, value3_high_precision, value3_high_null_flavor, value4_high_value, value4_high_precision, value4_high_null_flavor, value4_low_value, value4_low_precision, value4_low_null_flavor, value4_width_value, value4_width_precision, value4_width_null_flavor) VALUES (22, NULL, NULL, NULL, NULL, NULL, 4.1, 2.0, 640.1, 2.0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ivl_pqv (id, value1_low_value, value1_high_value, value2_low_value, value2_low_precision, value2_high_closed, value3_low_value, value3_low_precision, value3_high_value, value3_high_precision, value3_high_null_flavor, value4_high_value, value4_high_precision, value4_high_null_flavor, value4_low_value, value4_low_precision, value4_low_null_flavor, value4_width_value, value4_width_precision, value4_width_null_flavor) VALUES (23, NULL, NULL, NULL, NULL, NULL, 5.1, 2.0, 650.1, 2.0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ivl_pqv (id, value1_low_value, value1_high_value, value2_low_value, value2_low_precision, value2_high_closed, value3_low_value, value3_low_precision, value3_high_value, value3_high_precision, value3_high_null_flavor, value4_high_value, value4_high_precision, value4_high_null_flavor, value4_low_value, value4_low_precision, value4_low_null_flavor, value4_width_value, value4_width_precision, value4_width_null_flavor) VALUES (24, NULL, NULL, NULL, NULL, NULL, 1.1, 2.0, 610.1, 2.0, 'NI', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ivl_pqv (id, value1_low_value, value1_high_value, value2_low_value, value2_low_precision, value2_high_closed, value3_low_value, value3_low_precision, value3_high_value, value3_high_precision, value3_high_null_flavor, value4_high_value, value4_high_precision, value4_high_null_flavor, value4_low_value, value4_low_precision, value4_low_null_flavor, value4_width_value, value4_width_precision, value4_width_null_flavor) VALUES (25, NULL, NULL, NULL, NULL, NULL, 2.1, 2.0, 620.1, 2.0, 'NI', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ivl_pqv (id, value1_low_value, value1_high_value, value2_low_value, value2_low_precision, value2_high_closed, value3_low_value, value3_low_precision, value3_high_value, value3_high_precision, value3_high_null_flavor, value4_high_value, value4_high_precision, value4_high_null_flavor, value4_low_value, value4_low_precision, value4_low_null_flavor, value4_width_value, value4_width_precision, value4_width_null_flavor) VALUES (26, NULL, NULL, NULL, NULL, NULL, 3.1, 2.0, 630.1, 2.0, 'NI', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ivl_pqv (id, value1_low_value, value1_high_value, value2_low_value, value2_low_precision, value2_high_closed, value3_low_value, value3_low_precision, value3_high_value, value3_high_precision, value3_high_null_flavor, value4_high_value, value4_high_precision, value4_high_null_flavor, value4_low_value, value4_low_precision, value4_low_null_flavor, value4_width_value, value4_width_precision, value4_width_null_flavor) VALUES (27, NULL, NULL, NULL, NULL, NULL, 4.1, 2.0, 640.1, 2.0, 'NI', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ivl_pqv (id, value1_low_value, value1_high_value, value2_low_value, value2_low_precision, value2_high_closed, value3_low_value, value3_low_precision, value3_high_value, value3_high_precision, value3_high_null_flavor, value4_high_value, value4_high_precision, value4_high_null_flavor, value4_low_value, value4_low_precision, value4_low_null_flavor, value4_width_value, value4_width_precision, value4_width_null_flavor) VALUES (28, NULL, NULL, NULL, NULL, NULL, 5.1, 2.0, 650.1, 2.0, 'NI', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ivl_pqv (id, value1_low_value, value1_high_value, value2_low_value, value2_low_precision, value2_high_closed, value3_low_value, value3_low_precision, value3_high_value, value3_high_precision, value3_high_null_flavor, value4_high_value, value4_high_precision, value4_high_null_flavor, value4_low_value, value4_low_precision, value4_low_null_flavor, value4_width_value, value4_width_precision, value4_width_null_flavor) VALUES (29, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ivl_pqv (id, value1_low_value, value1_high_value, value2_low_value, value2_low_precision, value2_high_closed, value3_low_value, value3_low_precision, value3_high_value, value3_high_precision, value3_high_null_flavor, value4_high_value, value4_high_precision, value4_high_null_flavor, value4_low_value, value4_low_precision, value4_low_null_flavor, value4_width_value, value4_width_precision, value4_width_null_flavor) VALUES (30, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 710.1, 2.0, 'NI', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ivl_pqv (id, value1_low_value, value1_high_value, value2_low_value, value2_low_precision, value2_high_closed, value3_low_value, value3_low_precision, value3_high_value, value3_high_precision, value3_high_null_flavor, value4_high_value, value4_high_precision, value4_high_null_flavor, value4_low_value, value4_low_precision, value4_low_null_flavor, value4_width_value, value4_width_precision, value4_width_null_flavor) VALUES (31, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 720.1, 2.0, 'NI', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ivl_pqv (id, value1_low_value, value1_high_value, value2_low_value, value2_low_precision, value2_high_closed, value3_low_value, value3_low_precision, value3_high_value, value3_high_precision, value3_high_null_flavor, value4_high_value, value4_high_precision, value4_high_null_flavor, value4_low_value, value4_low_precision, value4_low_null_flavor, value4_width_value, value4_width_precision, value4_width_null_flavor) VALUES (32, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 730.1, 2.0, 'NI', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ivl_pqv (id, value1_low_value, value1_high_value, value2_low_value, value2_low_precision, value2_high_closed, value3_low_value, value3_low_precision, value3_high_value, value3_high_precision, value3_high_null_flavor, value4_high_value, value4_high_precision, value4_high_null_flavor, value4_low_value, value4_low_precision, value4_low_null_flavor, value4_width_value, value4_width_precision, value4_width_null_flavor) VALUES (33, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 740.1, 2.0, 'NI', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ivl_pqv (id, value1_low_value, value1_high_value, value2_low_value, value2_low_precision, value2_high_closed, value3_low_value, value3_low_precision, value3_high_value, value3_high_precision, value3_high_null_flavor, value4_high_value, value4_high_precision, value4_high_null_flavor, value4_low_value, value4_low_precision, value4_low_null_flavor, value4_width_value, value4_width_precision, value4_width_null_flavor) VALUES (34, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 750.1, 2.0, 'NI', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ivl_pqv (id, value1_low_value, value1_high_value, value2_low_value, value2_low_precision, value2_high_closed, value3_low_value, value3_low_precision, value3_high_value, value3_high_precision, value3_high_null_flavor, value4_high_value, value4_high_precision, value4_high_null_flavor, value4_low_value, value4_low_precision, value4_low_null_flavor, value4_width_value, value4_width_precision, value4_width_null_flavor) VALUES (35, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 11.1, 2.0, 'NI', 100.1, 2.0, 'NI', NULL, NULL, NULL);
INSERT INTO ivl_pqv (id, value1_low_value, value1_high_value, value2_low_value, value2_low_precision, value2_high_closed, value3_low_value, value3_low_precision, value3_high_value, value3_high_precision, value3_high_null_flavor, value4_high_value, value4_high_precision, value4_high_null_flavor, value4_low_value, value4_low_precision, value4_low_null_flavor, value4_width_value, value4_width_precision, value4_width_null_flavor) VALUES (36, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 12.1, 2.0, 'NI', 100.1, 2.0, 'NI', NULL, NULL, NULL);
INSERT INTO ivl_pqv (id, value1_low_value, value1_high_value, value2_low_value, value2_low_precision, value2_high_closed, value3_low_value, value3_low_precision, value3_high_value, value3_high_precision, value3_high_null_flavor, value4_high_value, value4_high_precision, value4_high_null_flavor, value4_low_value, value4_low_precision, value4_low_null_flavor, value4_width_value, value4_width_precision, value4_width_null_flavor) VALUES (37, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 13.1, 2.0, 'NI', 100.1, 2.0, 'NI', NULL, NULL, NULL);
INSERT INTO ivl_pqv (id, value1_low_value, value1_high_value, value2_low_value, value2_low_precision, value2_high_closed, value3_low_value, value3_low_precision, value3_high_value, value3_high_precision, value3_high_null_flavor, value4_high_value, value4_high_precision, value4_high_null_flavor, value4_low_value, value4_low_precision, value4_low_null_flavor, value4_width_value, value4_width_precision, value4_width_null_flavor) VALUES (38, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 14.1, 2.0, 'NI', 100.1, 2.0, 'NI', NULL, NULL, NULL);
INSERT INTO ivl_pqv (id, value1_low_value, value1_high_value, value2_low_value, value2_low_precision, value2_high_closed, value3_low_value, value3_low_precision, value3_high_value, value3_high_precision, value3_high_null_flavor, value4_high_value, value4_high_precision, value4_high_null_flavor, value4_low_value, value4_low_precision, value4_low_null_flavor, value4_width_value, value4_width_precision, value4_width_null_flavor) VALUES (39, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 15.1, 2.0, 'NI', 100.1, 2.0, 'NI', NULL, NULL, NULL);
INSERT INTO ivl_pqv (id, value1_low_value, value1_high_value, value2_low_value, value2_low_precision, value2_high_closed, value3_low_value, value3_low_precision, value3_high_value, value3_high_precision, value3_high_null_flavor, value4_high_value, value4_high_precision, value4_high_null_flavor, value4_low_value, value4_low_precision, value4_low_null_flavor, value4_width_value, value4_width_precision, value4_width_null_flavor) VALUES (40, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 210.1, 2.0, 'NI', 100.1, 2.0, 'NI', 5.0, 2.0, 'NI');
INSERT INTO ivl_pqv (id, value1_low_value, value1_high_value, value2_low_value, value2_low_precision, value2_high_closed, value3_low_value, value3_low_precision, value3_high_value, value3_high_precision, value3_high_null_flavor, value4_high_value, value4_high_precision, value4_high_null_flavor, value4_low_value, value4_low_precision, value4_low_null_flavor, value4_width_value, value4_width_precision, value4_width_null_flavor) VALUES (41, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 220.1, 2.0, 'NI', 100.1, 2.0, 'NI', 5.0, 2.0, 'NI');
INSERT INTO ivl_pqv (id, value1_low_value, value1_high_value, value2_low_value, value2_low_precision, value2_high_closed, value3_low_value, value3_low_precision, value3_high_value, value3_high_precision, value3_high_null_flavor, value4_high_value, value4_high_precision, value4_high_null_flavor, value4_low_value, value4_low_precision, value4_low_null_flavor, value4_width_value, value4_width_precision, value4_width_null_flavor) VALUES (42, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 230.1, 2.0, 'NI', 100.1, 2.0, 'NI', 5.0, 2.0, 'NI');
INSERT INTO ivl_pqv (id, value1_low_value, value1_high_value, value2_low_value, value2_low_precision, value2_high_closed, value3_low_value, value3_low_precision, value3_high_value, value3_high_precision, value3_high_null_flavor, value4_high_value, value4_high_precision, value4_high_null_flavor, value4_low_value, value4_low_precision, value4_low_null_flavor, value4_width_value, value4_width_precision, value4_width_null_flavor) VALUES (43, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 240.1, 2.0, 'NI', 100.1, 2.0, 'NI', 5.0, 2.0, 'NI');
INSERT INTO ivl_pqv (id, value1_low_value, value1_high_value, value2_low_value, value2_low_precision, value2_high_closed, value3_low_value, value3_low_precision, value3_high_value, value3_high_precision, value3_high_null_flavor, value4_high_value, value4_high_precision, value4_high_null_flavor, value4_low_value, value4_low_precision, value4_low_null_flavor, value4_width_value, value4_width_precision, value4_width_null_flavor) VALUES (44, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 250.1, 2.0, 'NI', 100.1, 2.0, 'NI', 5.0, 2.0, 'NI');


--
-- TOC entry 2294 (class 0 OID 18737)
-- Dependencies: 1720
-- Data for Name: ivl_real; Type: TABLE DATA; 
--

INSERT INTO ivl_real (id, value1_low_value, value1_high_value, value2_high_value, value2_low_closed, value3_any_value, value3_high_value, value3_high_closed, value3_low_value, value3_low_closed, value3_width_value, value3_width_null_flavor, value3_null_flavor) VALUES (1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ivl_real (id, value1_low_value, value1_high_value, value2_high_value, value2_low_closed, value3_any_value, value3_high_value, value3_high_closed, value3_low_value, value3_low_closed, value3_width_value, value3_width_null_flavor, value3_null_flavor) VALUES (2, 10.1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ivl_real (id, value1_low_value, value1_high_value, value2_high_value, value2_low_closed, value3_any_value, value3_high_value, value3_high_closed, value3_low_value, value3_low_closed, value3_width_value, value3_width_null_flavor, value3_null_flavor) VALUES (3, 1, 210.19999999999999, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ivl_real (id, value1_low_value, value1_high_value, value2_high_value, value2_low_closed, value3_any_value, value3_high_value, value3_high_closed, value3_low_value, value3_low_closed, value3_width_value, value3_width_null_flavor, value3_null_flavor) VALUES (4, NULL, 220.19999999999999, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ivl_real (id, value1_low_value, value1_high_value, value2_high_value, value2_low_closed, value3_any_value, value3_high_value, value3_high_closed, value3_low_value, value3_low_closed, value3_width_value, value3_width_null_flavor, value3_null_flavor) VALUES (5, 3, 230.19999999999999, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ivl_real (id, value1_low_value, value1_high_value, value2_high_value, value2_low_closed, value3_any_value, value3_high_value, value3_high_closed, value3_low_value, value3_low_closed, value3_width_value, value3_width_null_flavor, value3_null_flavor) VALUES (6, 230.19999999999999, 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ivl_real (id, value1_low_value, value1_high_value, value2_high_value, value2_low_closed, value3_any_value, value3_high_value, value3_high_closed, value3_low_value, value3_low_closed, value3_width_value, value3_width_null_flavor, value3_null_flavor) VALUES (7, NULL, NULL, -310, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ivl_real (id, value1_low_value, value1_high_value, value2_high_value, value2_low_closed, value3_any_value, value3_high_value, value3_high_closed, value3_low_value, value3_low_closed, value3_width_value, value3_width_null_flavor, value3_null_flavor) VALUES (8, NULL, NULL, 40, '1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ivl_real (id, value1_low_value, value1_high_value, value2_high_value, value2_low_closed, value3_any_value, value3_high_value, value3_high_closed, value3_low_value, value3_low_closed, value3_width_value, value3_width_null_flavor, value3_null_flavor) VALUES (9, NULL, NULL, NULL, '1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ivl_real (id, value1_low_value, value1_high_value, value2_high_value, value2_low_closed, value3_any_value, value3_high_value, value3_high_closed, value3_low_value, value3_low_closed, value3_width_value, value3_width_null_flavor, value3_null_flavor) VALUES (10, NULL, NULL, 4222222, '1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ivl_real (id, value1_low_value, value1_high_value, value2_high_value, value2_low_closed, value3_any_value, value3_high_value, value3_high_closed, value3_low_value, value3_low_closed, value3_width_value, value3_width_null_flavor, value3_null_flavor) VALUES (11, NULL, NULL, 43, '0', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ivl_real (id, value1_low_value, value1_high_value, value2_high_value, value2_low_closed, value3_any_value, value3_high_value, value3_high_closed, value3_low_value, value3_low_closed, value3_width_value, value3_width_null_flavor, value3_null_flavor) VALUES (12, NULL, NULL, 44, '0', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ivl_real (id, value1_low_value, value1_high_value, value2_high_value, value2_low_closed, value3_any_value, value3_high_value, value3_high_closed, value3_low_value, value3_low_closed, value3_width_value, value3_width_null_flavor, value3_null_flavor) VALUES (13, NULL, NULL, NULL, NULL, NULL, 510, '1', 2, '1', 44, NULL, NULL);
INSERT INTO ivl_real (id, value1_low_value, value1_high_value, value2_high_value, value2_low_closed, value3_any_value, value3_high_value, value3_high_closed, value3_low_value, value3_low_closed, value3_width_value, value3_width_null_flavor, value3_null_flavor) VALUES (14, NULL, NULL, NULL, NULL, NULL, 520, '1', 2, '1', 44, NULL, NULL);
INSERT INTO ivl_real (id, value1_low_value, value1_high_value, value2_high_value, value2_low_closed, value3_any_value, value3_high_value, value3_high_closed, value3_low_value, value3_low_closed, value3_width_value, value3_width_null_flavor, value3_null_flavor) VALUES (15, NULL, NULL, NULL, NULL, NULL, 530, '1', 2, '1', NULL, 'NI', NULL);
INSERT INTO ivl_real (id, value1_low_value, value1_high_value, value2_high_value, value2_low_closed, value3_any_value, value3_high_value, value3_high_closed, value3_low_value, value3_low_closed, value3_width_value, value3_width_null_flavor, value3_null_flavor) VALUES (16, NULL, NULL, NULL, NULL, NULL, 540, '1', 2, '1', 44, NULL, NULL);
INSERT INTO ivl_real (id, value1_low_value, value1_high_value, value2_high_value, value2_low_closed, value3_any_value, value3_high_value, value3_high_closed, value3_low_value, value3_low_closed, value3_width_value, value3_width_null_flavor, value3_null_flavor) VALUES (17, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'NI');
INSERT INTO ivl_real (id, value1_low_value, value1_high_value, value2_high_value, value2_low_closed, value3_any_value, value3_high_value, value3_high_closed, value3_low_value, value3_low_closed, value3_width_value, value3_width_null_flavor, value3_null_flavor) VALUES (18, NULL, NULL, NULL, NULL, NULL, 610, '1', 251, '1', 4, NULL, NULL);
INSERT INTO ivl_real (id, value1_low_value, value1_high_value, value2_high_value, value2_low_closed, value3_any_value, value3_high_value, value3_high_closed, value3_low_value, value3_low_closed, value3_width_value, value3_width_null_flavor, value3_null_flavor) VALUES (19, NULL, NULL, NULL, NULL, NULL, 620, '1', 252, '1', 4, NULL, NULL);
INSERT INTO ivl_real (id, value1_low_value, value1_high_value, value2_high_value, value2_low_closed, value3_any_value, value3_high_value, value3_high_closed, value3_low_value, value3_low_closed, value3_width_value, value3_width_null_flavor, value3_null_flavor) VALUES (20, NULL, NULL, NULL, NULL, NULL, 630, '1', 253, '1', 4, NULL, NULL);
INSERT INTO ivl_real (id, value1_low_value, value1_high_value, value2_high_value, value2_low_closed, value3_any_value, value3_high_value, value3_high_closed, value3_low_value, value3_low_closed, value3_width_value, value3_width_null_flavor, value3_null_flavor) VALUES (21, NULL, NULL, NULL, NULL, NULL, 640, '1', 254, '1', 4, NULL, NULL);
INSERT INTO ivl_real (id, value1_low_value, value1_high_value, value2_high_value, value2_low_closed, value3_any_value, value3_high_value, value3_high_closed, value3_low_value, value3_low_closed, value3_width_value, value3_width_null_flavor, value3_null_flavor) VALUES (22, NULL, NULL, NULL, NULL, 101, 650, '1', 255, '1', 4, NULL, NULL);


--
-- TOC entry 2295 (class 0 OID 18740)
-- Dependencies: 1721
-- Data for Name: ivl_ts; Type: TABLE DATA; 
--

INSERT INTO ivl_ts (id, value1_low_value, value1_high_value, value2_high_value, value2_low_closed, value3_high_value, value3_low_value, value3_width_null_flavor, value3_width_value, value3_null_flavor) VALUES (1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ivl_ts (id, value1_low_value, value1_high_value, value2_high_value, value2_low_closed, value3_high_value, value3_low_value, value3_width_null_flavor, value3_width_value, value3_null_flavor) VALUES (2, '2010-03-11 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ivl_ts (id, value1_low_value, value1_high_value, value2_high_value, value2_low_closed, value3_high_value, value3_low_value, value3_width_null_flavor, value3_width_value, value3_null_flavor) VALUES (3, '2010-03-12 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ivl_ts (id, value1_low_value, value1_high_value, value2_high_value, value2_low_closed, value3_high_value, value3_low_value, value3_width_null_flavor, value3_width_value, value3_null_flavor) VALUES (4, NULL, '2010-03-11 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ivl_ts (id, value1_low_value, value1_high_value, value2_high_value, value2_low_closed, value3_high_value, value3_low_value, value3_width_null_flavor, value3_width_value, value3_null_flavor) VALUES (5, NULL, '2000-03-12 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ivl_ts (id, value1_low_value, value1_high_value, value2_high_value, value2_low_closed, value3_high_value, value3_low_value, value3_width_null_flavor, value3_width_value, value3_null_flavor) VALUES (6, '2010-03-03 00:00:00', '2010-03-13 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ivl_ts (id, value1_low_value, value1_high_value, value2_high_value, value2_low_closed, value3_high_value, value3_low_value, value3_width_null_flavor, value3_width_value, value3_null_flavor) VALUES (7, NULL, NULL, '2010-03-11 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO ivl_ts (id, value1_low_value, value1_high_value, value2_high_value, value2_low_closed, value3_high_value, value3_low_value, value3_width_null_flavor, value3_width_value, value3_null_flavor) VALUES (8, NULL, NULL, '2001-03-11 00:00:00', '1', NULL, NULL, NULL, NULL, NULL);
INSERT INTO ivl_ts (id, value1_low_value, value1_high_value, value2_high_value, value2_low_closed, value3_high_value, value3_low_value, value3_width_null_flavor, value3_width_value, value3_null_flavor) VALUES (9, NULL, NULL, NULL, '1', NULL, NULL, NULL, NULL, NULL);
INSERT INTO ivl_ts (id, value1_low_value, value1_high_value, value2_high_value, value2_low_closed, value3_high_value, value3_low_value, value3_width_null_flavor, value3_width_value, value3_null_flavor) VALUES (10, NULL, NULL, '2010-03-13 00:00:00', '0', NULL, NULL, NULL, NULL, NULL);
INSERT INTO ivl_ts (id, value1_low_value, value1_high_value, value2_high_value, value2_low_closed, value3_high_value, value3_low_value, value3_width_null_flavor, value3_width_value, value3_null_flavor) VALUES (11, NULL, NULL, '2010-03-14 00:00:00', '1', NULL, NULL, NULL, NULL, NULL);
INSERT INTO ivl_ts (id, value1_low_value, value1_high_value, value2_high_value, value2_low_closed, value3_high_value, value3_low_value, value3_width_null_flavor, value3_width_value, value3_null_flavor) VALUES (12, NULL, NULL, '2010-03-15 00:00:00', '1', NULL, NULL, NULL, NULL, NULL);
INSERT INTO ivl_ts (id, value1_low_value, value1_high_value, value2_high_value, value2_low_closed, value3_high_value, value3_low_value, value3_width_null_flavor, value3_width_value, value3_null_flavor) VALUES (13, NULL, NULL, NULL, NULL, '2010-03-11 00:00:00', '2010-03-01 00:00:00', NULL, 4, NULL);
INSERT INTO ivl_ts (id, value1_low_value, value1_high_value, value2_high_value, value2_low_closed, value3_high_value, value3_low_value, value3_width_null_flavor, value3_width_value, value3_null_flavor) VALUES (14, NULL, NULL, NULL, NULL, '2010-03-12 00:00:00', '2010-03-02 00:00:00', NULL, 4, NULL);
INSERT INTO ivl_ts (id, value1_low_value, value1_high_value, value2_high_value, value2_low_closed, value3_high_value, value3_low_value, value3_width_null_flavor, value3_width_value, value3_null_flavor) VALUES (15, NULL, NULL, NULL, NULL, '2010-03-13 00:00:00', '2010-03-03 00:00:00', NULL, 4, NULL);
INSERT INTO ivl_ts (id, value1_low_value, value1_high_value, value2_high_value, value2_low_closed, value3_high_value, value3_low_value, value3_width_null_flavor, value3_width_value, value3_null_flavor) VALUES (16, NULL, NULL, NULL, NULL, '2010-03-14 00:00:00', '2010-03-04 00:00:00', 'NI', NULL, NULL);
INSERT INTO ivl_ts (id, value1_low_value, value1_high_value, value2_high_value, value2_low_closed, value3_high_value, value3_low_value, value3_width_null_flavor, value3_width_value, value3_null_flavor) VALUES (17, NULL, NULL, NULL, NULL, '2010-03-15 00:00:00', '2010-03-05 00:00:00', 'NI', NULL, NULL);
INSERT INTO ivl_ts (id, value1_low_value, value1_high_value, value2_high_value, value2_low_closed, value3_high_value, value3_low_value, value3_width_null_flavor, value3_width_value, value3_null_flavor) VALUES (18, NULL, NULL, NULL, NULL, NULL, '2010-03-01 00:00:00', NULL, 4, NULL);
INSERT INTO ivl_ts (id, value1_low_value, value1_high_value, value2_high_value, value2_low_closed, value3_high_value, value3_low_value, value3_width_null_flavor, value3_width_value, value3_null_flavor) VALUES (19, NULL, NULL, NULL, NULL, '2010-03-22 00:00:00', NULL, 'NI', NULL, NULL);
INSERT INTO ivl_ts (id, value1_low_value, value1_high_value, value2_high_value, value2_low_closed, value3_high_value, value3_low_value, value3_width_null_flavor, value3_width_value, value3_null_flavor) VALUES (20, NULL, NULL, NULL, NULL, NULL, NULL, 'NA', NULL, 'NA');
INSERT INTO ivl_ts (id, value1_low_value, value1_high_value, value2_high_value, value2_low_closed, value3_high_value, value3_low_value, value3_width_null_flavor, value3_width_value, value3_null_flavor) VALUES (21, NULL, NULL, NULL, NULL, '2010-03-24 00:00:00', '2010-03-04 00:00:00', NULL, 4, NULL);


--
-- TOC entry 2296 (class 0 OID 18743)
-- Dependencies: 1722
-- Data for Name: pq_datatype; Type: TABLE DATA; 
--

INSERT INTO pq_datatype (id, value1_value, value1_unit, value2_value, value3_null_flavor, value3_unit, value3_value, value3_precision) VALUES (1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO pq_datatype (id, value1_value, value1_unit, value2_value, value3_null_flavor, value3_unit, value3_value, value3_precision) VALUES (2, 1.12, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO pq_datatype (id, value1_value, value1_unit, value2_value, value3_null_flavor, value3_unit, value3_value, value3_precision) VALUES (3, 2.12, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO pq_datatype (id, value1_value, value1_unit, value2_value, value3_null_flavor, value3_unit, value3_value, value3_precision) VALUES (4, 3.12, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO pq_datatype (id, value1_value, value1_unit, value2_value, value3_null_flavor, value3_unit, value3_value, value3_precision) VALUES (5, 4.12, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO pq_datatype (id, value1_value, value1_unit, value2_value, value3_null_flavor, value3_unit, value3_value, value3_precision) VALUES (6, 5.12, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO pq_datatype (id, value1_value, value1_unit, value2_value, value3_null_flavor, value3_unit, value3_value, value3_precision) VALUES (7, 1.12, 'GALLON', NULL, NULL, NULL, NULL, NULL);
INSERT INTO pq_datatype (id, value1_value, value1_unit, value2_value, value3_null_flavor, value3_unit, value3_value, value3_precision) VALUES (8, 2.12, 'GALLON', NULL, NULL, NULL, NULL, NULL);
INSERT INTO pq_datatype (id, value1_value, value1_unit, value2_value, value3_null_flavor, value3_unit, value3_value, value3_precision) VALUES (9, 3.12, 'GALLON', NULL, NULL, NULL, NULL, NULL);
INSERT INTO pq_datatype (id, value1_value, value1_unit, value2_value, value3_null_flavor, value3_unit, value3_value, value3_precision) VALUES (10, 4.12, 'GALLON', NULL, NULL, NULL, NULL, NULL);
INSERT INTO pq_datatype (id, value1_value, value1_unit, value2_value, value3_null_flavor, value3_unit, value3_value, value3_precision) VALUES (11, 5.12, 'GALLON', NULL, NULL, NULL, NULL, NULL);
INSERT INTO pq_datatype (id, value1_value, value1_unit, value2_value, value3_null_flavor, value3_unit, value3_value, value3_precision) VALUES (12, NULL, NULL, 1.23, NULL, NULL, NULL, NULL);
INSERT INTO pq_datatype (id, value1_value, value1_unit, value2_value, value3_null_flavor, value3_unit, value3_value, value3_precision) VALUES (13, NULL, NULL, 2.23, NULL, NULL, NULL, NULL);
INSERT INTO pq_datatype (id, value1_value, value1_unit, value2_value, value3_null_flavor, value3_unit, value3_value, value3_precision) VALUES (14, NULL, NULL, 3.23, NULL, NULL, NULL, NULL);
INSERT INTO pq_datatype (id, value1_value, value1_unit, value2_value, value3_null_flavor, value3_unit, value3_value, value3_precision) VALUES (15, NULL, NULL, 4.23, NULL, NULL, NULL, NULL);
INSERT INTO pq_datatype (id, value1_value, value1_unit, value2_value, value3_null_flavor, value3_unit, value3_value, value3_precision) VALUES (16, NULL, NULL, 5.23, NULL, NULL, NULL, NULL);
INSERT INTO pq_datatype (id, value1_value, value1_unit, value2_value, value3_null_flavor, value3_unit, value3_value, value3_precision) VALUES (17, NULL, NULL, NULL, NULL, NULL, 1.34, NULL);
INSERT INTO pq_datatype (id, value1_value, value1_unit, value2_value, value3_null_flavor, value3_unit, value3_value, value3_precision) VALUES (18, NULL, NULL, NULL, NULL, NULL, 2.34, NULL);
INSERT INTO pq_datatype (id, value1_value, value1_unit, value2_value, value3_null_flavor, value3_unit, value3_value, value3_precision) VALUES (19, NULL, NULL, NULL, NULL, 'LITER', 1.37, NULL);
INSERT INTO pq_datatype (id, value1_value, value1_unit, value2_value, value3_null_flavor, value3_unit, value3_value, value3_precision) VALUES (20, NULL, NULL, NULL, NULL, 'LITER', 2.37, NULL);
INSERT INTO pq_datatype (id, value1_value, value1_unit, value2_value, value3_null_flavor, value3_unit, value3_value, value3_precision) VALUES (21, NULL, NULL, NULL, NULL, 'LITER', 3.37, NULL);
INSERT INTO pq_datatype (id, value1_value, value1_unit, value2_value, value3_null_flavor, value3_unit, value3_value, value3_precision) VALUES (22, NULL, NULL, NULL, NULL, 'LITER', 1.38, 2.0);
INSERT INTO pq_datatype (id, value1_value, value1_unit, value2_value, value3_null_flavor, value3_unit, value3_value, value3_precision) VALUES (23, NULL, NULL, NULL, NULL, 'LITER', 2.38, 2.0);
INSERT INTO pq_datatype (id, value1_value, value1_unit, value2_value, value3_null_flavor, value3_unit, value3_value, value3_precision) VALUES (24, NULL, NULL, NULL, NULL, 'LITER', 3.38, 2.0);
INSERT INTO pq_datatype (id, value1_value, value1_unit, value2_value, value3_null_flavor, value3_unit, value3_value, value3_precision) VALUES (25, NULL, NULL, NULL, 'NA', NULL, NULL, NULL);
INSERT INTO pq_datatype (id, value1_value, value1_unit, value2_value, value3_null_flavor, value3_unit, value3_value, value3_precision) VALUES (26, NULL, NULL, NULL, 'NA', NULL, NULL, NULL);
INSERT INTO pq_datatype (id, value1_value, value1_unit, value2_value, value3_null_flavor, value3_unit, value3_value, value3_precision) VALUES (27, NULL, NULL, NULL, 'NA', NULL, NULL, NULL);


--
-- TOC entry 2297 (class 0 OID 18749)
-- Dependencies: 1723
-- Data for Name: pqv_datatype; Type: TABLE DATA; 
--

INSERT INTO pqv_datatype (id, value1_value, value2_null_flavor, value2_value, value2_precision, value3_value, value3_precision, value4_null_flavor, value4_precision, value4_value) VALUES (1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO pqv_datatype (id, value1_value, value2_null_flavor, value2_value, value2_precision, value3_value, value3_precision, value4_null_flavor, value4_precision, value4_value) VALUES (2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO pqv_datatype (id, value1_value, value2_null_flavor, value2_value, value2_precision, value3_value, value3_precision, value4_null_flavor, value4_precision, value4_value) VALUES (3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO pqv_datatype (id, value1_value, value2_null_flavor, value2_value, value2_precision, value3_value, value3_precision, value4_null_flavor, value4_precision, value4_value) VALUES (4, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO pqv_datatype (id, value1_value, value2_null_flavor, value2_value, value2_precision, value3_value, value3_precision, value4_null_flavor, value4_precision, value4_value) VALUES (5, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO pqv_datatype (id, value1_value, value2_null_flavor, value2_value, value2_precision, value3_value, value3_precision, value4_null_flavor, value4_precision, value4_value) VALUES (6, 1.11, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO pqv_datatype (id, value1_value, value2_null_flavor, value2_value, value2_precision, value3_value, value3_precision, value4_null_flavor, value4_precision, value4_value) VALUES (7, 2.11, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO pqv_datatype (id, value1_value, value2_null_flavor, value2_value, value2_precision, value3_value, value3_precision, value4_null_flavor, value4_precision, value4_value) VALUES (8, 3.11, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO pqv_datatype (id, value1_value, value2_null_flavor, value2_value, value2_precision, value3_value, value3_precision, value4_null_flavor, value4_precision, value4_value) VALUES (9, 4.11, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO pqv_datatype (id, value1_value, value2_null_flavor, value2_value, value2_precision, value3_value, value3_precision, value4_null_flavor, value4_precision, value4_value) VALUES (10, 5.11, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO pqv_datatype (id, value1_value, value2_null_flavor, value2_value, value2_precision, value3_value, value3_precision, value4_null_flavor, value4_precision, value4_value) VALUES (11, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO pqv_datatype (id, value1_value, value2_null_flavor, value2_value, value2_precision, value3_value, value3_precision, value4_null_flavor, value4_precision, value4_value) VALUES (12, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO pqv_datatype (id, value1_value, value2_null_flavor, value2_value, value2_precision, value3_value, value3_precision, value4_null_flavor, value4_precision, value4_value) VALUES (13, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO pqv_datatype (id, value1_value, value2_null_flavor, value2_value, value2_precision, value3_value, value3_precision, value4_null_flavor, value4_precision, value4_value) VALUES (14, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO pqv_datatype (id, value1_value, value2_null_flavor, value2_value, value2_precision, value3_value, value3_precision, value4_null_flavor, value4_precision, value4_value) VALUES (15, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO pqv_datatype (id, value1_value, value2_null_flavor, value2_value, value2_precision, value3_value, value3_precision, value4_null_flavor, value4_precision, value4_value) VALUES (16, NULL, NULL, 1.22, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO pqv_datatype (id, value1_value, value2_null_flavor, value2_value, value2_precision, value3_value, value3_precision, value4_null_flavor, value4_precision, value4_value) VALUES (17, NULL, NULL, 2.22, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO pqv_datatype (id, value1_value, value2_null_flavor, value2_value, value2_precision, value3_value, value3_precision, value4_null_flavor, value4_precision, value4_value) VALUES (18, NULL, NULL, 3.22, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO pqv_datatype (id, value1_value, value2_null_flavor, value2_value, value2_precision, value3_value, value3_precision, value4_null_flavor, value4_precision, value4_value) VALUES (19, NULL, NULL, 4.22, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO pqv_datatype (id, value1_value, value2_null_flavor, value2_value, value2_precision, value3_value, value3_precision, value4_null_flavor, value4_precision, value4_value) VALUES (20, NULL, NULL, 5.22, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO pqv_datatype (id, value1_value, value2_null_flavor, value2_value, value2_precision, value3_value, value3_precision, value4_null_flavor, value4_precision, value4_value) VALUES (21, NULL, NULL, 1.23, 2.0, NULL, NULL, NULL, NULL, NULL);
INSERT INTO pqv_datatype (id, value1_value, value2_null_flavor, value2_value, value2_precision, value3_value, value3_precision, value4_null_flavor, value4_precision, value4_value) VALUES (22, NULL, NULL, 2.23, 2.0, NULL, NULL, NULL, NULL, NULL);
INSERT INTO pqv_datatype (id, value1_value, value2_null_flavor, value2_value, value2_precision, value3_value, value3_precision, value4_null_flavor, value4_precision, value4_value) VALUES (23, NULL, NULL, 3.23, 2.0, NULL, NULL, NULL, NULL, NULL);
INSERT INTO pqv_datatype (id, value1_value, value2_null_flavor, value2_value, value2_precision, value3_value, value3_precision, value4_null_flavor, value4_precision, value4_value) VALUES (24, NULL, NULL, 4.23, 2.0, NULL, NULL, NULL, NULL, NULL);
INSERT INTO pqv_datatype (id, value1_value, value2_null_flavor, value2_value, value2_precision, value3_value, value3_precision, value4_null_flavor, value4_precision, value4_value) VALUES (25, NULL, NULL, 5.23, 2.0, NULL, NULL, NULL, NULL, NULL);
INSERT INTO pqv_datatype (id, value1_value, value2_null_flavor, value2_value, value2_precision, value3_value, value3_precision, value4_null_flavor, value4_precision, value4_value) VALUES (26, NULL, 'NI', 1.23, 2.0, NULL, NULL, NULL, NULL, NULL);
INSERT INTO pqv_datatype (id, value1_value, value2_null_flavor, value2_value, value2_precision, value3_value, value3_precision, value4_null_flavor, value4_precision, value4_value) VALUES (27, NULL, 'NI', 2.23, 2.0, NULL, NULL, NULL, NULL, NULL);
INSERT INTO pqv_datatype (id, value1_value, value2_null_flavor, value2_value, value2_precision, value3_value, value3_precision, value4_null_flavor, value4_precision, value4_value) VALUES (28, NULL, 'NI', 3.23, 2.0, NULL, NULL, NULL, NULL, NULL);
INSERT INTO pqv_datatype (id, value1_value, value2_null_flavor, value2_value, value2_precision, value3_value, value3_precision, value4_null_flavor, value4_precision, value4_value) VALUES (29, NULL, 'NI', 4.23, 2.0, NULL, NULL, NULL, NULL, NULL);
INSERT INTO pqv_datatype (id, value1_value, value2_null_flavor, value2_value, value2_precision, value3_value, value3_precision, value4_null_flavor, value4_precision, value4_value) VALUES (30, NULL, 'NI', 5.23, 2.0, NULL, NULL, NULL, NULL, NULL);


--
-- TOC entry 2298 (class 0 OID 18755)
-- Dependencies: 1724
-- Data for Name: real_datatype; Type: TABLE DATA; 
--

INSERT INTO real_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (1, NULL, NULL, NULL);
INSERT INTO real_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (2, 1001.15, NULL, NULL);
INSERT INTO real_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (3, 1002.15, NULL, NULL);
INSERT INTO real_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (4, -1003, NULL, NULL);
INSERT INTO real_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (5, 1004, NULL, NULL);
INSERT INTO real_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (6, 1005.15, NULL, NULL);
INSERT INTO real_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (7, NULL, NULL, 1101.1500000000001);
INSERT INTO real_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (8, NULL, NULL, 1102);
INSERT INTO real_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (9, NULL, NULL, -1201.1500000000001);
INSERT INTO real_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (10, NULL, 'NA', NULL);
INSERT INTO real_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (11, NULL, 'NA', NULL);
INSERT INTO real_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (12, NULL, 'NA', NULL);


--
-- TOC entry 2299 (class 0 OID 18758)
-- Dependencies: 1725
-- Data for Name: sc_datatype; Type: TABLE DATA; 
--

INSERT INTO sc_datatype (id, value1_value, value1_code_code, value2_null_flavor, value2_value, value2_code_null_flavor, value2_code_code, value2_code_code_system, value2_code_code_system_name, value2_code_code_system_ver, value3_null_flavor, value3_value, value3_code_null_flavor, value3_code_code, value3_code_code_system, value3_code_code_system_name, value3_code_code_system_ver, value3_code_display_nflavor, value3_code_display_value, value3_code_orig_txt_nflavor, value3_code_orig_txt_desc, value3_code_orig_txt_value) VALUES (1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO sc_datatype (id, value1_value, value1_code_code, value2_null_flavor, value2_value, value2_code_null_flavor, value2_code_code, value2_code_code_system, value2_code_code_system_name, value2_code_code_system_ver, value3_null_flavor, value3_value, value3_code_null_flavor, value3_code_code, value3_code_code_system, value3_code_code_system_name, value3_code_code_system_ver, value3_code_display_nflavor, value3_code_display_value, value3_code_orig_txt_nflavor, value3_code_orig_txt_desc, value3_code_orig_txt_value) VALUES (2, 'VALUE1_VALUE1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO sc_datatype (id, value1_value, value1_code_code, value2_null_flavor, value2_value, value2_code_null_flavor, value2_code_code, value2_code_code_system, value2_code_code_system_name, value2_code_code_system_ver, value3_null_flavor, value3_value, value3_code_null_flavor, value3_code_code, value3_code_code_system, value3_code_code_system_name, value3_code_code_system_ver, value3_code_display_nflavor, value3_code_display_value, value3_code_orig_txt_nflavor, value3_code_orig_txt_desc, value3_code_orig_txt_value) VALUES (3, 'VALUE1_VALUE2', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO sc_datatype (id, value1_value, value1_code_code, value2_null_flavor, value2_value, value2_code_null_flavor, value2_code_code, value2_code_code_system, value2_code_code_system_name, value2_code_code_system_ver, value3_null_flavor, value3_value, value3_code_null_flavor, value3_code_code, value3_code_code_system, value3_code_code_system_name, value3_code_code_system_ver, value3_code_display_nflavor, value3_code_display_value, value3_code_orig_txt_nflavor, value3_code_orig_txt_desc, value3_code_orig_txt_value) VALUES (4, 'VALUE1_VALUE3', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO sc_datatype (id, value1_value, value1_code_code, value2_null_flavor, value2_value, value2_code_null_flavor, value2_code_code, value2_code_code_system, value2_code_code_system_name, value2_code_code_system_ver, value3_null_flavor, value3_value, value3_code_null_flavor, value3_code_code, value3_code_code_system, value3_code_code_system_name, value3_code_code_system_ver, value3_code_display_nflavor, value3_code_display_value, value3_code_orig_txt_nflavor, value3_code_orig_txt_desc, value3_code_orig_txt_value) VALUES (5, 'VALUE1_VALUE4', 'CODE1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO sc_datatype (id, value1_value, value1_code_code, value2_null_flavor, value2_value, value2_code_null_flavor, value2_code_code, value2_code_code_system, value2_code_code_system_name, value2_code_code_system_ver, value3_null_flavor, value3_value, value3_code_null_flavor, value3_code_code, value3_code_code_system, value3_code_code_system_name, value3_code_code_system_ver, value3_code_display_nflavor, value3_code_display_value, value3_code_orig_txt_nflavor, value3_code_orig_txt_desc, value3_code_orig_txt_value) VALUES (6, 'VALUE1_VALUE5', 'CODE2', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO sc_datatype (id, value1_value, value1_code_code, value2_null_flavor, value2_value, value2_code_null_flavor, value2_code_code, value2_code_code_system, value2_code_code_system_name, value2_code_code_system_ver, value3_null_flavor, value3_value, value3_code_null_flavor, value3_code_code, value3_code_code_system, value3_code_code_system_name, value3_code_code_system_ver, value3_code_display_nflavor, value3_code_display_value, value3_code_orig_txt_nflavor, value3_code_orig_txt_desc, value3_code_orig_txt_value) VALUES (7, NULL, NULL, 'UNK', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO sc_datatype (id, value1_value, value1_code_code, value2_null_flavor, value2_value, value2_code_null_flavor, value2_code_code, value2_code_code_system, value2_code_code_system_name, value2_code_code_system_ver, value3_null_flavor, value3_value, value3_code_null_flavor, value3_code_code, value3_code_code_system, value3_code_code_system_name, value3_code_code_system_ver, value3_code_display_nflavor, value3_code_display_value, value3_code_orig_txt_nflavor, value3_code_orig_txt_desc, value3_code_orig_txt_value) VALUES (8, NULL, NULL, NULL, 'VALUE2_VALUE1', NULL, 'VALUE2_CODE_CODE1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO sc_datatype (id, value1_value, value1_code_code, value2_null_flavor, value2_value, value2_code_null_flavor, value2_code_code, value2_code_code_system, value2_code_code_system_name, value2_code_code_system_ver, value3_null_flavor, value3_value, value3_code_null_flavor, value3_code_code, value3_code_code_system, value3_code_code_system_name, value3_code_code_system_ver, value3_code_display_nflavor, value3_code_display_value, value3_code_orig_txt_nflavor, value3_code_orig_txt_desc, value3_code_orig_txt_value) VALUES (9, NULL, NULL, NULL, 'VALUE2_VALUE2', NULL, 'VALUE2_CODE_CODE2', 'VALUE2_CODE_CODE_SYSTEM1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO sc_datatype (id, value1_value, value1_code_code, value2_null_flavor, value2_value, value2_code_null_flavor, value2_code_code, value2_code_code_system, value2_code_code_system_name, value2_code_code_system_ver, value3_null_flavor, value3_value, value3_code_null_flavor, value3_code_code, value3_code_code_system, value3_code_code_system_name, value3_code_code_system_ver, value3_code_display_nflavor, value3_code_display_value, value3_code_orig_txt_nflavor, value3_code_orig_txt_desc, value3_code_orig_txt_value) VALUES (10, NULL, NULL, NULL, 'VALUE2_VALUE3', NULL, 'VALUE2_CODE_CODE3', 'VALUE2_CODE_CODE_SYSTEM2', 'VALUE2_CODE_CODE_SYSTEM_NAME1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO sc_datatype (id, value1_value, value1_code_code, value2_null_flavor, value2_value, value2_code_null_flavor, value2_code_code, value2_code_code_system, value2_code_code_system_name, value2_code_code_system_ver, value3_null_flavor, value3_value, value3_code_null_flavor, value3_code_code, value3_code_code_system, value3_code_code_system_name, value3_code_code_system_ver, value3_code_display_nflavor, value3_code_display_value, value3_code_orig_txt_nflavor, value3_code_orig_txt_desc, value3_code_orig_txt_value) VALUES (11, NULL, NULL, NULL, 'VALUE2_VALUE4', NULL, 'VALUE2_CODE_CODE4', 'VALUE2_CODE_CODE_SYSTEM3', 'VALUE2_CODE_CODE_SYSTEM_NAME2', '1.1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO sc_datatype (id, value1_value, value1_code_code, value2_null_flavor, value2_value, value2_code_null_flavor, value2_code_code, value2_code_code_system, value2_code_code_system_name, value2_code_code_system_ver, value3_null_flavor, value3_value, value3_code_null_flavor, value3_code_code, value3_code_code_system, value3_code_code_system_name, value3_code_code_system_ver, value3_code_display_nflavor, value3_code_display_value, value3_code_orig_txt_nflavor, value3_code_orig_txt_desc, value3_code_orig_txt_value) VALUES (12, NULL, NULL, NULL, 'VALUE2_VALUE5', 'NA', 'VALUE2_CODE_CODE5', 'VALUE2_CODE_CODE_SYSTEM4', 'VALUE2_CODE_CODE_SYSTEM_NAME3', '1.1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO sc_datatype (id, value1_value, value1_code_code, value2_null_flavor, value2_value, value2_code_null_flavor, value2_code_code, value2_code_code_system, value2_code_code_system_name, value2_code_code_system_ver, value3_null_flavor, value3_value, value3_code_null_flavor, value3_code_code, value3_code_code_system, value3_code_code_system_name, value3_code_code_system_ver, value3_code_display_nflavor, value3_code_display_value, value3_code_orig_txt_nflavor, value3_code_orig_txt_desc, value3_code_orig_txt_value) VALUES (13, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'NA', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO sc_datatype (id, value1_value, value1_code_code, value2_null_flavor, value2_value, value2_code_null_flavor, value2_code_code, value2_code_code_system, value2_code_code_system_name, value2_code_code_system_ver, value3_null_flavor, value3_value, value3_code_null_flavor, value3_code_code, value3_code_code_system, value3_code_code_system_name, value3_code_code_system_ver, value3_code_display_nflavor, value3_code_display_value, value3_code_orig_txt_nflavor, value3_code_orig_txt_desc, value3_code_orig_txt_value) VALUES (14, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'VALUE3_VALUE1', NULL, 'VALUE3_CODE_CODE1', 'VALUE3_CODE_CODE_SYSTEM1', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO sc_datatype (id, value1_value, value1_code_code, value2_null_flavor, value2_value, value2_code_null_flavor, value2_code_code, value2_code_code_system, value2_code_code_system_name, value2_code_code_system_ver, value3_null_flavor, value3_value, value3_code_null_flavor, value3_code_code, value3_code_code_system, value3_code_code_system_name, value3_code_code_system_ver, value3_code_display_nflavor, value3_code_display_value, value3_code_orig_txt_nflavor, value3_code_orig_txt_desc, value3_code_orig_txt_value) VALUES (15, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'VALUE3_VALUE2', NULL, 'VALUE3_CODE_CODE1', 'VALUE3_CODE_CODE_SYSTEM1', 'VALUE3_CODE_CODE_SYSTEM_NAME1', 'VALUE3_CODE_CODE_SYSTEM_VER1', NULL, NULL, NULL, NULL, NULL);
INSERT INTO sc_datatype (id, value1_value, value1_code_code, value2_null_flavor, value2_value, value2_code_null_flavor, value2_code_code, value2_code_code_system, value2_code_code_system_name, value2_code_code_system_ver, value3_null_flavor, value3_value, value3_code_null_flavor, value3_code_code, value3_code_code_system, value3_code_code_system_name, value3_code_code_system_ver, value3_code_display_nflavor, value3_code_display_value, value3_code_orig_txt_nflavor, value3_code_orig_txt_desc, value3_code_orig_txt_value) VALUES (16, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'VALUE3_VALUE1', NULL, 'VALUE3_CODE_CODE1', 'VALUE3_CODE_CODE_SYSTEM1', 'VALUE3_CODE_CODE_SYSTEM_NAME1', 'VALUE3_CODE_CODE_SYSTEM_VER1', 'NI', NULL, 'NI', NULL, NULL);
INSERT INTO sc_datatype (id, value1_value, value1_code_code, value2_null_flavor, value2_value, value2_code_null_flavor, value2_code_code, value2_code_code_system, value2_code_code_system_name, value2_code_code_system_ver, value3_null_flavor, value3_value, value3_code_null_flavor, value3_code_code, value3_code_code_system, value3_code_code_system_name, value3_code_code_system_ver, value3_code_display_nflavor, value3_code_display_value, value3_code_orig_txt_nflavor, value3_code_orig_txt_desc, value3_code_orig_txt_value) VALUES (17, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'VALUE3_VALUE3', NULL, 'VALUE3_CODE_CODE1', 'VALUE3_CODE_CODE_SYSTEM1', 'VALUE3_CODE_CODE_SYSTEM_NAME1', 'VALUE3_CODE_CODE_SYSTEM_VER', NULL, 'VALUE3_CODE_DISPLAY_VALUE', 'NI', NULL, NULL);
INSERT INTO sc_datatype (id, value1_value, value1_code_code, value2_null_flavor, value2_value, value2_code_null_flavor, value2_code_code, value2_code_code_system, value2_code_code_system_name, value2_code_code_system_ver, value3_null_flavor, value3_value, value3_code_null_flavor, value3_code_code, value3_code_code_system, value3_code_code_system_name, value3_code_code_system_ver, value3_code_display_nflavor, value3_code_display_value, value3_code_orig_txt_nflavor, value3_code_orig_txt_desc, value3_code_orig_txt_value) VALUES (18, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'VALUE3_VALUE4', NULL, 'VALUE3_CODE_CODE31', 'VALUE3_CODE_CODE_SYSTEM31', 'VALUE3_CODE_CODE_SYSTEM_NAME31', 'VALUE3_CODE_CODE_SYSTEM_VER31', NULL, 'VALUE3_CODE_DISPLAY_VALUE31', 'NI', NULL, NULL);
INSERT INTO sc_datatype (id, value1_value, value1_code_code, value2_null_flavor, value2_value, value2_code_null_flavor, value2_code_code, value2_code_code_system, value2_code_code_system_name, value2_code_code_system_ver, value3_null_flavor, value3_value, value3_code_null_flavor, value3_code_code, value3_code_code_system, value3_code_code_system_name, value3_code_code_system_ver, value3_code_display_nflavor, value3_code_display_value, value3_code_orig_txt_nflavor, value3_code_orig_txt_desc, value3_code_orig_txt_value) VALUES (19, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'VALUE3_VALUE5', NULL, 'VALUE3_CODE_CODE31', 'VALUE3_CODE_CODE_SYSTEM31', 'VALUE3_CODE_CODE_SYSTEM_NAME31', 'VALUE3_CODE_CODE_SYSTEM_VER31', NULL, 'VALUE3_CODE_DISPLAY_VALUE31', 'NI', NULL, NULL);
INSERT INTO sc_datatype (id, value1_value, value1_code_code, value2_null_flavor, value2_value, value2_code_null_flavor, value2_code_code, value2_code_code_system, value2_code_code_system_name, value2_code_code_system_ver, value3_null_flavor, value3_value, value3_code_null_flavor, value3_code_code, value3_code_code_system, value3_code_code_system_name, value3_code_code_system_ver, value3_code_display_nflavor, value3_code_display_value, value3_code_orig_txt_nflavor, value3_code_orig_txt_desc, value3_code_orig_txt_value) VALUES (20, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'VALUE3_VALUE6', NULL, 'VALUE3_CODE_CODE31', 'VALUE3_CODE_CODE_SYSTEM31', 'VALUE3_CODE_CODE_SYSTEM_NAME31', 'VALUE3_CODE_CODE_SYSTEM_VER31', NULL, 'VALUE3_CODE_DISPLAY_VALUE31', 'NI', NULL, NULL);
INSERT INTO sc_datatype (id, value1_value, value1_code_code, value2_null_flavor, value2_value, value2_code_null_flavor, value2_code_code, value2_code_code_system, value2_code_code_system_name, value2_code_code_system_ver, value3_null_flavor, value3_value, value3_code_null_flavor, value3_code_code, value3_code_code_system, value3_code_code_system_name, value3_code_code_system_ver, value3_code_display_nflavor, value3_code_display_value, value3_code_orig_txt_nflavor, value3_code_orig_txt_desc, value3_code_orig_txt_value) VALUES (21, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'VALUE3_VALUE7', NULL, 'VALUE3_CODE_CODE31', 'VALUE3_CODE_CODE_SYSTEM31', 'VALUE3_CODE_CODE_SYSTEM_NAME31', 'VALUE3_CODE_CODE_SYSTEM_VER31', NULL, 'VALUE3_CODE_DISPLAY_VALUE31', NULL, 'VALUE3_CODE_ORIG_TXT_DESC1', NULL);
INSERT INTO sc_datatype (id, value1_value, value1_code_code, value2_null_flavor, value2_value, value2_code_null_flavor, value2_code_code, value2_code_code_system, value2_code_code_system_name, value2_code_code_system_ver, value3_null_flavor, value3_value, value3_code_null_flavor, value3_code_code, value3_code_code_system, value3_code_code_system_name, value3_code_code_system_ver, value3_code_display_nflavor, value3_code_display_value, value3_code_orig_txt_nflavor, value3_code_orig_txt_desc, value3_code_orig_txt_value) VALUES (22, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'VALUE3_VALUE8', NULL, 'VALUE3_CODE_CODE31', 'VALUE3_CODE_CODE_SYSTEM31', 'VALUE3_CODE_CODE_SYSTEM_NAME31', 'VALUE3_CODE_CODE_SYSTEM_VER31', NULL, 'VALUE3_CODE_DISPLAY_VALUE31', NULL, 'VALUE3_CODE_ORIG_TXT_DESC31', 'VALUE3_CODE_ORIG_TXT_VALUE31');
INSERT INTO sc_datatype (id, value1_value, value1_code_code, value2_null_flavor, value2_value, value2_code_null_flavor, value2_code_code, value2_code_code_system, value2_code_code_system_name, value2_code_code_system_ver, value3_null_flavor, value3_value, value3_code_null_flavor, value3_code_code, value3_code_code_system, value3_code_code_system_name, value3_code_code_system_ver, value3_code_display_nflavor, value3_code_display_value, value3_code_orig_txt_nflavor, value3_code_orig_txt_desc, value3_code_orig_txt_value) VALUES (23, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'VALUE3_VALUE9', 'NI', 'VALUE3_CODE_CODE31', 'VALUE3_CODE_CODE_SYSTEM31', 'VALUE3_CODE_CODE_SYSTEM_NAME31', 'VALUE3_CODE_CODE_SYSTEM_VER31', NULL, 'VALUE3_CODE_DISPLAY_VALUE31', NULL, 'VALUE3_CODE_ORIG_TXT_DESC31', 'VALUE3_CODE_ORIG_TXT_VALUE31');


--
-- TOC entry 2300 (class 0 OID 18764)
-- Dependencies: 1726
-- Data for Name: st_datatype; Type: TABLE DATA; 
--

INSERT INTO st_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (1, NULL, NULL, NULL);
INSERT INTO st_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (2, 'VALUE1_VALUE1', NULL, NULL);
INSERT INTO st_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (3, 'VALUE1_VALUE2', NULL, NULL);
INSERT INTO st_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (4, 'VALUE1_VALUE3', NULL, NULL);
INSERT INTO st_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (5, 'VALUE1_VALUE4', NULL, NULL);
INSERT INTO st_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (6, 'VALUE1_VALUE5', NULL, NULL);
INSERT INTO st_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (7, NULL, NULL, 'VALUE2_VALUE1');
INSERT INTO st_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (8, NULL, NULL, 'VALUE2_VALUE2');
INSERT INTO st_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (9, NULL, NULL, 'VALUE2_VALUE3');
INSERT INTO st_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (10, NULL, NULL, 'VALUE2_VALUE4');
INSERT INTO st_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (11, NULL, NULL, 'VALUE2_VALUE5');
INSERT INTO st_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (12, NULL, 'UNK', NULL);
INSERT INTO st_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (13, NULL, 'UNK', NULL);
INSERT INTO st_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (14, NULL, 'UNK', NULL);
INSERT INTO st_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (15, NULL, 'UNK', NULL);
INSERT INTO st_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (16, NULL, 'UNK', NULL);


--
-- TOC entry 2301 (class 0 OID 18767)
-- Dependencies: 1727
-- Data for Name: st_nt_datatype; Type: TABLE DATA; 
--

INSERT INTO st_nt_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (1, NULL, NULL, NULL);
INSERT INTO st_nt_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (2, 'VALUE1_VALUE1', NULL, NULL);
INSERT INTO st_nt_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (3, 'VALUE1_VALUE2', NULL, NULL);
INSERT INTO st_nt_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (4, 'VALUE1_VALUE3', NULL, NULL);
INSERT INTO st_nt_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (5, 'VALUE1_VALUE4', NULL, NULL);
INSERT INTO st_nt_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (6, 'VALUE1_VALUE5', NULL, NULL);
INSERT INTO st_nt_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (7, NULL, NULL, 'VALUE2_VALUE1');
INSERT INTO st_nt_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (8, NULL, NULL, 'VALUE2_VALUE2');
INSERT INTO st_nt_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (9, NULL, NULL, 'VALUE2_VALUE3');
INSERT INTO st_nt_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (10, NULL, NULL, 'VALUE2_VALUE4');
INSERT INTO st_nt_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (11, NULL, NULL, 'VALUE2_VALUE5');
INSERT INTO st_nt_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (12, NULL, 'UNK', NULL);
INSERT INTO st_nt_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (13, NULL, 'UNK', NULL);
INSERT INTO st_nt_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (14, NULL, 'UNK', NULL);
INSERT INTO st_nt_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (15, NULL, 'UNK', NULL);
INSERT INTO st_nt_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (16, NULL, 'UNK', NULL);


--
-- TOC entry 2302 (class 0 OID 18770)
-- Dependencies: 1728
-- Data for Name: tel_datatype; Type: TABLE DATA; 
--

INSERT INTO tel_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (1, NULL, NULL, NULL);
INSERT INTO tel_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (2, 'tel://123-456-7891', NULL, NULL);
INSERT INTO tel_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (3, 'tel://123-456-7892', NULL, NULL);
INSERT INTO tel_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (4, 'tel://123-456-7893', NULL, NULL);
INSERT INTO tel_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (5, 'tel://123-456-7894', NULL, NULL);
INSERT INTO tel_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (6, 'tel://123-456-7895', NULL, NULL);
INSERT INTO tel_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (7, NULL, NULL, 'tel://123-456-7896');
INSERT INTO tel_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (8, NULL, NULL, 'tel://123-456-7897');
INSERT INTO tel_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (9, NULL, NULL, 'tel://123-456-7898');
INSERT INTO tel_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (10, NULL, NULL, 'tel://123-456-7893');
INSERT INTO tel_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (11, NULL, NULL, 'tel://123-456-7894');
INSERT INTO tel_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (12, NULL, NULL, 'tel://123-456-7895');


--
-- TOC entry 2303 (class 0 OID 18773)
-- Dependencies: 1729
-- Data for Name: tel_email_datatype; Type: TABLE DATA; 
--

INSERT INTO tel_email_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (1, NULL, NULL, NULL);
INSERT INTO tel_email_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (2, 'mailto:jdoe1@nci.gov', NULL, NULL);
INSERT INTO tel_email_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (3, 'mailto:jdoe2@nci.gov', NULL, NULL);
INSERT INTO tel_email_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (4, 'mailto:jdoe3@nci.gov', NULL, NULL);
INSERT INTO tel_email_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (5, 'mailto:jdoe4@nci.gov', NULL, NULL);
INSERT INTO tel_email_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (6, 'mailto:jdoe5@nci.gov', NULL, NULL);
INSERT INTO tel_email_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (7, NULL, NULL, 'MailTo:jdoe1@nci.gov');
INSERT INTO tel_email_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (8, NULL, NULL, 'MailTo:jdoe2@nci.gov');
INSERT INTO tel_email_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (9, NULL, NULL, 'mailto:jdoe3@nci.gov');
INSERT INTO tel_email_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (10, NULL, NULL, 'mailto:jdoe4@nci.gov');
INSERT INTO tel_email_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (11, NULL, NULL, 'mailto:jdoe5@nci.gov');


--
-- TOC entry 2304 (class 0 OID 18776)
-- Dependencies: 1730
-- Data for Name: tel_person_datatype; Type: TABLE DATA; 
--

INSERT INTO tel_person_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (1, NULL, NULL, NULL);
INSERT INTO tel_person_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (2, 'tel:8004226231', NULL, NULL);
INSERT INTO tel_person_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (3, 'x-text-fax:8004226232', NULL, NULL);
INSERT INTO tel_person_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (4, 'x-text-tel:8004226233', NULL, NULL);
INSERT INTO tel_person_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (5, 'mailto:8004226235', NULL, NULL);
INSERT INTO tel_person_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (6, 'x-text-tel:8004226235', NULL, NULL);
INSERT INTO tel_person_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (7, NULL, NULL, 'x-text-tel:8004226234');
INSERT INTO tel_person_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (8, NULL, NULL, 'mailto:8004226235');
INSERT INTO tel_person_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (9, NULL, NULL, 'x-text-tel:8004226233');
INSERT INTO tel_person_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (10, NULL, NULL, 'mailto:8004226235');
INSERT INTO tel_person_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (11, NULL, NULL, 'x-text-tel:8004226235');


--
-- TOC entry 2305 (class 0 OID 18779)
-- Dependencies: 1731
-- Data for Name: tel_phone_datatype; Type: TABLE DATA; 
--

INSERT INTO tel_phone_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (1, NULL, NULL, NULL);
INSERT INTO tel_phone_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (2, 'tel:8004226231', NULL, NULL);
INSERT INTO tel_phone_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (3, 'x-text-tel:8004226232', NULL, NULL);
INSERT INTO tel_phone_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (4, 'x-text-fax:8004226233', NULL, NULL);
INSERT INTO tel_phone_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (5, 'tel:8004226234', NULL, NULL);
INSERT INTO tel_phone_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (6, 'tel:8004226235', NULL, NULL);
INSERT INTO tel_phone_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (7, NULL, NULL, 'tel:8004226231');
INSERT INTO tel_phone_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (8, NULL, NULL, 'x-text-tel:8004226232');
INSERT INTO tel_phone_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (9, NULL, NULL, 'x-text-fax:8004226233');
INSERT INTO tel_phone_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (10, NULL, NULL, 'tel:8004226234');
INSERT INTO tel_phone_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (11, NULL, NULL, 'tel:8004226235');


--
-- TOC entry 2306 (class 0 OID 18782)
-- Dependencies: 1732
-- Data for Name: tel_url_datatype; Type: TABLE DATA; 
--

INSERT INTO tel_url_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (1, NULL, NULL, NULL);
INSERT INTO tel_url_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (2, 'https://www.cancer.gov', NULL, NULL);
INSERT INTO tel_url_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (3, 'file://test.xml', NULL, NULL);
INSERT INTO tel_url_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (4, 'ftp://cancer.gov', NULL, NULL);
INSERT INTO tel_url_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (5, 'cid://www.cancer3.gov', NULL, NULL);
INSERT INTO tel_url_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (6, 'http://www.cancer4.gov', NULL, NULL);
INSERT INTO tel_url_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (7, NULL, NULL, 'https://www.cancer.gov');
INSERT INTO tel_url_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (8, NULL, NULL, 'nfs://d/test.xml');
INSERT INTO tel_url_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (9, NULL, NULL, 'cid://www.cancer3.gov');
INSERT INTO tel_url_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (10, NULL, NULL, 'ftp://cancer.gov');
INSERT INTO tel_url_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (11, NULL, NULL, 'http://www.cancer4.gov');


--
-- TOC entry 2307 (class 0 OID 18785)
-- Dependencies: 1733
-- Data for Name: ts_datatype; Type: TABLE DATA; 
--

INSERT INTO ts_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (1, NULL, NULL, NULL);
INSERT INTO ts_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (2, '2010-03-11 00:00:00', NULL, NULL);
INSERT INTO ts_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (3, '2010-03-12 00:00:00', NULL, NULL);
INSERT INTO ts_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (4, '2010-03-13 00:00:00', NULL, NULL);
INSERT INTO ts_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (5, '2010-03-14 00:00:00', NULL, NULL);
INSERT INTO ts_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (6, '2010-03-15 00:00:00', NULL, NULL);
INSERT INTO ts_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (7, NULL, NULL, '2010-03-21 00:00:00');
INSERT INTO ts_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (8, NULL, NULL, '2010-03-22 00:00:00');
INSERT INTO ts_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (9, NULL, NULL, '2010-03-23 00:00:00');
INSERT INTO ts_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (10, NULL, 'NA', NULL);
INSERT INTO ts_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (11, NULL, 'NA', NULL);
INSERT INTO ts_datatype (id, value1_value, value2_null_flavor, value2_value) VALUES (12, NULL, 'NA', NULL);


--
-- TOC entry 2001 (class 2606 OID 18795)
-- Dependencies: 1649 1649 1649
-- Name: ad_ad_datatype_value9_pkey; Type: CONSTRAINT 
--

ALTER TABLE ONLY ad_ad_datatype_value9
    ADD CONSTRAINT ad_ad_datatype_value9_pkey PRIMARY KEY (ad_datatype_id, ad_datatype_value9_id);


--
-- TOC entry 2004 (class 2606 OID 18797)
-- Dependencies: 1650 1650
-- Name: ad_datatype_pkey; Type: CONSTRAINT 
--

ALTER TABLE ONLY ad_datatype
    ADD CONSTRAINT ad_datatype_pkey PRIMARY KEY (id);


--
-- TOC entry 2008 (class 2606 OID 18799)
-- Dependencies: 1652 1652
-- Name: ad_datatype_value8_pkey; Type: CONSTRAINT 
--

ALTER TABLE ONLY ad_datatype_value8
    ADD CONSTRAINT ad_datatype_value8_pkey PRIMARY KEY (id);


--
-- TOC entry 2010 (class 2606 OID 18801)
-- Dependencies: 1653 1653
-- Name: ad_datatype_value9_pkey; Type: CONSTRAINT 
--

ALTER TABLE ONLY ad_datatype_value9
    ADD CONSTRAINT ad_datatype_value9_pkey PRIMARY KEY (id);


--
-- TOC entry 2012 (class 2606 OID 18803)
-- Dependencies: 1654 1654
-- Name: bl_datatype_pkey; Type: CONSTRAINT 
--

ALTER TABLE ONLY bl_datatype
    ADD CONSTRAINT bl_datatype_pkey PRIMARY KEY (id);


--
-- TOC entry 2014 (class 2606 OID 18805)
-- Dependencies: 1655 1655
-- Name: bl_nonnull_datatype_pkey; Type: CONSTRAINT 
--

ALTER TABLE ONLY bl_nonnull_datatype
    ADD CONSTRAINT bl_nonnull_datatype_pkey PRIMARY KEY (id);


--
-- TOC entry 2016 (class 2606 OID 18807)
-- Dependencies: 1656 1656 1656
-- Name: cd_cd_datatype_value8_pkey; Type: CONSTRAINT 
--

ALTER TABLE ONLY cd_cd_datatype_value8
    ADD CONSTRAINT cd_cd_datatype_value8_pkey PRIMARY KEY (cd_data_type_id, cd_datatype_value8_id);


--
-- TOC entry 2019 (class 2606 OID 18809)
-- Dependencies: 1657 1657
-- Name: cd_datatype_pkey; Type: CONSTRAINT 
--

ALTER TABLE ONLY cd_datatype
    ADD CONSTRAINT cd_datatype_pkey PRIMARY KEY (id);


--
-- TOC entry 2022 (class 2606 OID 18811)
-- Dependencies: 1658 1658
-- Name: cd_datatype_value6_pkey; Type: CONSTRAINT 
--

ALTER TABLE ONLY cd_datatype_value6
    ADD CONSTRAINT cd_datatype_value6_pkey PRIMARY KEY (cd_datatype_id);


--
-- TOC entry 2024 (class 2606 OID 18813)
-- Dependencies: 1659 1659
-- Name: cd_datatype_value7_pkey; Type: CONSTRAINT 
--

ALTER TABLE ONLY cd_datatype_value7
    ADD CONSTRAINT cd_datatype_value7_pkey PRIMARY KEY (id);


--
-- TOC entry 2026 (class 2606 OID 18815)
-- Dependencies: 1660 1660
-- Name: cd_datatype_value8_pkey; Type: CONSTRAINT 
--

ALTER TABLE ONLY cd_datatype_value8
    ADD CONSTRAINT cd_datatype_value8_pkey PRIMARY KEY (id);


--
-- TOC entry 2028 (class 2606 OID 18817)
-- Dependencies: 1661 1661
-- Name: dset_ad_datatype_pkey; Type: CONSTRAINT 
--

ALTER TABLE ONLY dset_ad_datatype
    ADD CONSTRAINT dset_ad_datatype_pkey PRIMARY KEY (id);


--
-- TOC entry 2030 (class 2606 OID 18819)
-- Dependencies: 1662 1662 1662
-- Name: dset_ad_dset_ad_value8_pkey; Type: CONSTRAINT 
--

ALTER TABLE ONLY dset_ad_dset_ad_value8
    ADD CONSTRAINT dset_ad_dset_ad_value8_pkey PRIMARY KEY (dset_ad_datatype_id, dset_ad_value8_id);


--
-- TOC entry 2033 (class 2606 OID 18821)
-- Dependencies: 1663 1663
-- Name: dset_ad_value1_pkey; Type: CONSTRAINT 
--

ALTER TABLE ONLY dset_ad_value1
    ADD CONSTRAINT dset_ad_value1_pkey PRIMARY KEY (dset_ad_datatype_id);


--
-- TOC entry 2035 (class 2606 OID 18823)
-- Dependencies: 1664 1664
-- Name: dset_ad_value2_pkey; Type: CONSTRAINT 
--

ALTER TABLE ONLY dset_ad_value2
    ADD CONSTRAINT dset_ad_value2_pkey PRIMARY KEY (dset_ad_datatype_id);


--
-- TOC entry 2037 (class 2606 OID 18825)
-- Dependencies: 1665 1665
-- Name: dset_ad_value3_pkey; Type: CONSTRAINT 
--

ALTER TABLE ONLY dset_ad_value3
    ADD CONSTRAINT dset_ad_value3_pkey PRIMARY KEY (dset_ad_datatype_id);


--
-- TOC entry 2039 (class 2606 OID 18827)
-- Dependencies: 1666 1666
-- Name: dset_ad_value4_pkey; Type: CONSTRAINT 
--

ALTER TABLE ONLY dset_ad_value4
    ADD CONSTRAINT dset_ad_value4_pkey PRIMARY KEY (dset_ad_datatype_id);


--
-- TOC entry 2041 (class 2606 OID 18829)
-- Dependencies: 1667 1667
-- Name: dset_ad_value5_pkey; Type: CONSTRAINT 
--

ALTER TABLE ONLY dset_ad_value5
    ADD CONSTRAINT dset_ad_value5_pkey PRIMARY KEY (dset_ad_datatype_id);


--
-- TOC entry 2043 (class 2606 OID 18831)
-- Dependencies: 1668 1668
-- Name: dset_ad_value6_pkey; Type: CONSTRAINT 
--

ALTER TABLE ONLY dset_ad_value6
    ADD CONSTRAINT dset_ad_value6_pkey PRIMARY KEY (dset_ad_datatype_id);


--
-- TOC entry 2045 (class 2606 OID 18833)
-- Dependencies: 1669 1669
-- Name: dset_ad_value7_pkey; Type: CONSTRAINT 
--

ALTER TABLE ONLY dset_ad_value7
    ADD CONSTRAINT dset_ad_value7_pkey PRIMARY KEY (id);


--
-- TOC entry 2048 (class 2606 OID 18835)
-- Dependencies: 1670 1670
-- Name: dset_ad_value8_pkey; Type: CONSTRAINT 
--

ALTER TABLE ONLY dset_ad_value8
    ADD CONSTRAINT dset_ad_value8_pkey PRIMARY KEY (id);


--
-- TOC entry 2052 (class 2606 OID 18839)
-- Dependencies: 1672 1672 1672
-- Name: dset_cd_cd_value7_pkey; Type: CONSTRAINT 
--

ALTER TABLE ONLY dset_cd_cd_value7
    ADD CONSTRAINT dset_cd_cd_value7_pkey PRIMARY KEY (dset_cd_id, dset_cd_value7_id);


--
-- TOC entry 2050 (class 2606 OID 18837)
-- Dependencies: 1671 1671
-- Name: dset_cd_pkey; Type: CONSTRAINT 
--

ALTER TABLE ONLY dset_cd
    ADD CONSTRAINT dset_cd_pkey PRIMARY KEY (id);


--
-- TOC entry 2055 (class 2606 OID 18841)
-- Dependencies: 1673 1673
-- Name: dset_cd_value1_pkey; Type: CONSTRAINT 
--

ALTER TABLE ONLY dset_cd_value1
    ADD CONSTRAINT dset_cd_value1_pkey PRIMARY KEY (dset_cd_id);


--
-- TOC entry 2057 (class 2606 OID 18843)
-- Dependencies: 1674 1674
-- Name: dset_cd_value2_pkey; Type: CONSTRAINT 
--

ALTER TABLE ONLY dset_cd_value2
    ADD CONSTRAINT dset_cd_value2_pkey PRIMARY KEY (dset_cd_id);


--
-- TOC entry 2059 (class 2606 OID 18845)
-- Dependencies: 1675 1675
-- Name: dset_cd_value3_pkey; Type: CONSTRAINT 
--

ALTER TABLE ONLY dset_cd_value3
    ADD CONSTRAINT dset_cd_value3_pkey PRIMARY KEY (dset_cd_id);


--
-- TOC entry 2061 (class 2606 OID 18847)
-- Dependencies: 1676 1676
-- Name: dset_cd_value4_pkey; Type: CONSTRAINT 
--

ALTER TABLE ONLY dset_cd_value4
    ADD CONSTRAINT dset_cd_value4_pkey PRIMARY KEY (dset_cd_id);


--
-- TOC entry 2063 (class 2606 OID 18849)
-- Dependencies: 1677 1677
-- Name: dset_cd_value5_pkey; Type: CONSTRAINT 
--

ALTER TABLE ONLY dset_cd_value5
    ADD CONSTRAINT dset_cd_value5_pkey PRIMARY KEY (dset_cd_id);


--
-- TOC entry 2065 (class 2606 OID 18851)
-- Dependencies: 1678 1678
-- Name: dset_cd_value6_pkey; Type: CONSTRAINT 
--

ALTER TABLE ONLY dset_cd_value6
    ADD CONSTRAINT dset_cd_value6_pkey PRIMARY KEY (id);


--
-- TOC entry 2068 (class 2606 OID 18853)
-- Dependencies: 1679 1679
-- Name: dset_cd_value7_pkey; Type: CONSTRAINT 
--

ALTER TABLE ONLY dset_cd_value7
    ADD CONSTRAINT dset_cd_value7_pkey PRIMARY KEY (id);


--
-- TOC entry 2070 (class 2606 OID 18855)
-- Dependencies: 1680 1680
-- Name: dset_en_pkey; Type: CONSTRAINT 
--

ALTER TABLE ONLY dset_en
    ADD CONSTRAINT dset_en_pkey PRIMARY KEY (id);


--
-- TOC entry 2072 (class 2606 OID 18857)
-- Dependencies: 1681 1681
-- Name: dset_en_value1_pkey; Type: CONSTRAINT 
--

ALTER TABLE ONLY dset_en_value1
    ADD CONSTRAINT dset_en_value1_pkey PRIMARY KEY (dset_en_datatype_id);


--
-- TOC entry 2074 (class 2606 OID 18859)
-- Dependencies: 1682 1682
-- Name: dset_en_value2_pkey; Type: CONSTRAINT 
--

ALTER TABLE ONLY dset_en_value2
    ADD CONSTRAINT dset_en_value2_pkey PRIMARY KEY (dset_en_datatype_id);


--
-- TOC entry 2076 (class 2606 OID 18861)
-- Dependencies: 1683 1683
-- Name: dset_en_value3_pkey; Type: CONSTRAINT 
--

ALTER TABLE ONLY dset_en_value3
    ADD CONSTRAINT dset_en_value3_pkey PRIMARY KEY (dset_en_datatype_id);


--
-- TOC entry 2078 (class 2606 OID 18863)
-- Dependencies: 1684 1684
-- Name: dset_en_value4_pkey; Type: CONSTRAINT 
--

ALTER TABLE ONLY dset_en_value4
    ADD CONSTRAINT dset_en_value4_pkey PRIMARY KEY (dset_en_datatype_id);


--
-- TOC entry 2080 (class 2606 OID 18865)
-- Dependencies: 1685 1685
-- Name: dset_en_value5_pkey; Type: CONSTRAINT 
--

ALTER TABLE ONLY dset_en_value5
    ADD CONSTRAINT dset_en_value5_pkey PRIMARY KEY (dset_en_datatype_id);


--
-- TOC entry 2082 (class 2606 OID 18867)
-- Dependencies: 1686 1686
-- Name: dset_en_value6_pkey; Type: CONSTRAINT 
--

ALTER TABLE ONLY dset_en_value6
    ADD CONSTRAINT dset_en_value6_pkey PRIMARY KEY (dset_en_datatype_id);


--
-- TOC entry 2084 (class 2606 OID 18869)
-- Dependencies: 1687 1687
-- Name: dset_en_value7_pkey; Type: CONSTRAINT 
--

ALTER TABLE ONLY dset_en_value7
    ADD CONSTRAINT dset_en_value7_pkey PRIMARY KEY (dset_en_datatype_id);


--
-- TOC entry 2088 (class 2606 OID 18873)
-- Dependencies: 1689 1689 1689
-- Name: dset_ii_ii_value6_pkey; Type: CONSTRAINT 
--

ALTER TABLE ONLY dset_ii_ii_value6
    ADD CONSTRAINT dset_ii_ii_value6_pkey PRIMARY KEY (dset_ii_id, dset_ii_value6_id);


--
-- TOC entry 2086 (class 2606 OID 18871)
-- Dependencies: 1688 1688
-- Name: dset_ii_pkey; Type: CONSTRAINT 
--

ALTER TABLE ONLY dset_ii
    ADD CONSTRAINT dset_ii_pkey PRIMARY KEY (id);


--
-- TOC entry 2091 (class 2606 OID 18875)
-- Dependencies: 1690 1690
-- Name: dset_ii_value1_pkey; Type: CONSTRAINT 
--

ALTER TABLE ONLY dset_ii_value1
    ADD CONSTRAINT dset_ii_value1_pkey PRIMARY KEY (dset_ii_id);


--
-- TOC entry 2093 (class 2606 OID 18877)
-- Dependencies: 1691 1691
-- Name: dset_ii_value2_pkey; Type: CONSTRAINT 
--

ALTER TABLE ONLY dset_ii_value2
    ADD CONSTRAINT dset_ii_value2_pkey PRIMARY KEY (dset_ii_id);


--
-- TOC entry 2095 (class 2606 OID 18879)
-- Dependencies: 1692 1692
-- Name: dset_ii_value3_pkey; Type: CONSTRAINT 
--

ALTER TABLE ONLY dset_ii_value3
    ADD CONSTRAINT dset_ii_value3_pkey PRIMARY KEY (dset_ii_id);


--
-- TOC entry 2097 (class 2606 OID 18881)
-- Dependencies: 1693 1693
-- Name: dset_ii_value4_pkey; Type: CONSTRAINT 
--

ALTER TABLE ONLY dset_ii_value4
    ADD CONSTRAINT dset_ii_value4_pkey PRIMARY KEY (dset_ii_id);


--
-- TOC entry 2099 (class 2606 OID 18883)
-- Dependencies: 1694 1694
-- Name: dset_ii_value5_pkey; Type: CONSTRAINT 
--

ALTER TABLE ONLY dset_ii_value5
    ADD CONSTRAINT dset_ii_value5_pkey PRIMARY KEY (id);


--
-- TOC entry 2102 (class 2606 OID 18885)
-- Dependencies: 1695 1695
-- Name: dset_ii_value6_pkey; Type: CONSTRAINT 
--

ALTER TABLE ONLY dset_ii_value6
    ADD CONSTRAINT dset_ii_value6_pkey PRIMARY KEY (id);


--
-- TOC entry 2106 (class 2606 OID 18889)
-- Dependencies: 1697 1697
-- Name: dset_tel_email_pkey; Type: CONSTRAINT 
--

ALTER TABLE ONLY dset_tel_email
    ADD CONSTRAINT dset_tel_email_pkey PRIMARY KEY (id);


--
-- TOC entry 2108 (class 2606 OID 18891)
-- Dependencies: 1698 1698
-- Name: dset_tel_email_value1_pkey; Type: CONSTRAINT 
--

ALTER TABLE ONLY dset_tel_email_value1
    ADD CONSTRAINT dset_tel_email_value1_pkey PRIMARY KEY (dset_tel_email_id);


--
-- TOC entry 2111 (class 2606 OID 18893)
-- Dependencies: 1700 1700
-- Name: dset_tel_person_pkey; Type: CONSTRAINT 
--

ALTER TABLE ONLY dset_tel_person
    ADD CONSTRAINT dset_tel_person_pkey PRIMARY KEY (id);


--
-- TOC entry 2113 (class 2606 OID 18895)
-- Dependencies: 1701 1701
-- Name: dset_tel_person_value1_pkey; Type: CONSTRAINT 
--

ALTER TABLE ONLY dset_tel_person_value1
    ADD CONSTRAINT dset_tel_person_value1_pkey PRIMARY KEY (dset_tel_person_id);


--
-- TOC entry 2115 (class 2606 OID 18897)
-- Dependencies: 1702 1702
-- Name: dset_tel_phone_pkey; Type: CONSTRAINT 
--

ALTER TABLE ONLY dset_tel_phone
    ADD CONSTRAINT dset_tel_phone_pkey PRIMARY KEY (id);


--
-- TOC entry 2117 (class 2606 OID 18899)
-- Dependencies: 1703 1703
-- Name: dset_tel_phone_value1_pkey; Type: CONSTRAINT 
--

ALTER TABLE ONLY dset_tel_phone_value1
    ADD CONSTRAINT dset_tel_phone_value1_pkey PRIMARY KEY (dset_tel_phone_id);


--
-- TOC entry 2104 (class 2606 OID 18887)
-- Dependencies: 1696 1696
-- Name: dset_tel_pkey; Type: CONSTRAINT 
--

ALTER TABLE ONLY dset_tel
    ADD CONSTRAINT dset_tel_pkey PRIMARY KEY (id);


--
-- TOC entry 2119 (class 2606 OID 18901)
-- Dependencies: 1704 1704 1704
-- Name: dset_tel_tel_value_3_pkey; Type: CONSTRAINT 
--

ALTER TABLE ONLY dset_tel_tel_value_3
    ADD CONSTRAINT dset_tel_tel_value_3_pkey PRIMARY KEY (dset_tel_id, dset_tel_value3_id);


--
-- TOC entry 2122 (class 2606 OID 18903)
-- Dependencies: 1705 1705
-- Name: dset_tel_url_pkey; Type: CONSTRAINT 
--

ALTER TABLE ONLY dset_tel_url
    ADD CONSTRAINT dset_tel_url_pkey PRIMARY KEY (id);


--
-- TOC entry 2124 (class 2606 OID 18905)
-- Dependencies: 1706 1706
-- Name: dset_tel_url_value1_pkey; Type: CONSTRAINT 
--

ALTER TABLE ONLY dset_tel_url_value1
    ADD CONSTRAINT dset_tel_url_value1_pkey PRIMARY KEY (dset_tel_url_id);


--
-- TOC entry 2126 (class 2606 OID 18907)
-- Dependencies: 1707 1707
-- Name: dset_tel_value1_pkey; Type: CONSTRAINT 
--

ALTER TABLE ONLY dset_tel_value1
    ADD CONSTRAINT dset_tel_value1_pkey PRIMARY KEY (dset_tel_id);


--
-- TOC entry 2128 (class 2606 OID 18909)
-- Dependencies: 1708 1708
-- Name: dset_tel_value2_pkey; Type: CONSTRAINT 
--

ALTER TABLE ONLY dset_tel_value2
    ADD CONSTRAINT dset_tel_value2_pkey PRIMARY KEY (id);


--
-- TOC entry 2131 (class 2606 OID 18911)
-- Dependencies: 1709 1709
-- Name: dset_tel_value3_pkey; Type: CONSTRAINT 
--

ALTER TABLE ONLY dset_tel_value3
    ADD CONSTRAINT dset_tel_value3_pkey PRIMARY KEY (id);


--
-- TOC entry 2133 (class 2606 OID 18913)
-- Dependencies: 1710 1710
-- Name: ed_datatype_pkey; Type: CONSTRAINT 
--

ALTER TABLE ONLY ed_datatype
    ADD CONSTRAINT ed_datatype_pkey PRIMARY KEY (id);


--
-- TOC entry 2135 (class 2606 OID 18915)
-- Dependencies: 1711 1711
-- Name: ed_text_datatype_pkey; Type: CONSTRAINT 
--

ALTER TABLE ONLY ed_text_datatype
    ADD CONSTRAINT ed_text_datatype_pkey PRIMARY KEY (id);


--
-- TOC entry 2137 (class 2606 OID 18917)
-- Dependencies: 1712 1712
-- Name: en_datatype_pkey; Type: CONSTRAINT 
--

ALTER TABLE ONLY en_datatype
    ADD CONSTRAINT en_datatype_pkey PRIMARY KEY (id);


--
-- TOC entry 2139 (class 2606 OID 18919)
-- Dependencies: 1713 1713
-- Name: en_on_datatype_pkey; Type: CONSTRAINT 
--

ALTER TABLE ONLY en_on_datatype
    ADD CONSTRAINT en_on_datatype_pkey PRIMARY KEY (id);


--
-- TOC entry 2141 (class 2606 OID 18921)
-- Dependencies: 1714 1714
-- Name: en_pn_datatype_pkey; Type: CONSTRAINT 
--

ALTER TABLE ONLY en_pn_datatype
    ADD CONSTRAINT en_pn_datatype_pkey PRIMARY KEY (id);


--
-- TOC entry 2143 (class 2606 OID 18923)
-- Dependencies: 1715 1715
-- Name: ii_datatype_pkey; Type: CONSTRAINT 
--

ALTER TABLE ONLY ii_datatype
    ADD CONSTRAINT ii_datatype_pkey PRIMARY KEY (id);


--
-- TOC entry 2145 (class 2606 OID 18925)
-- Dependencies: 1716 1716
-- Name: int_datatype_pkey; Type: CONSTRAINT 
--

ALTER TABLE ONLY int_datatype
    ADD CONSTRAINT int_datatype_pkey PRIMARY KEY (id);


--
-- TOC entry 2147 (class 2606 OID 18927)
-- Dependencies: 1717 1717
-- Name: ivl_int_pkey; Type: CONSTRAINT 
--

ALTER TABLE ONLY ivl_int
    ADD CONSTRAINT ivl_int_pkey PRIMARY KEY (id);


--
-- TOC entry 2149 (class 2606 OID 18929)
-- Dependencies: 1718 1718
-- Name: ivl_pq_pkey; Type: CONSTRAINT 
--

ALTER TABLE ONLY ivl_pq
    ADD CONSTRAINT ivl_pq_pkey PRIMARY KEY (id);


--
-- TOC entry 2151 (class 2606 OID 18931)
-- Dependencies: 1719 1719
-- Name: ivl_pqv_pkey; Type: CONSTRAINT 
--

ALTER TABLE ONLY ivl_pqv
    ADD CONSTRAINT ivl_pqv_pkey PRIMARY KEY (id);


--
-- TOC entry 2153 (class 2606 OID 18933)
-- Dependencies: 1722 1722
-- Name: pq_datatype_pkey; Type: CONSTRAINT 
--

ALTER TABLE ONLY pq_datatype
    ADD CONSTRAINT pq_datatype_pkey PRIMARY KEY (id);


--
-- TOC entry 2155 (class 2606 OID 18935)
-- Dependencies: 1723 1723
-- Name: pqv_datatype_pkey; Type: CONSTRAINT 
--

ALTER TABLE ONLY pqv_datatype
    ADD CONSTRAINT pqv_datatype_pkey PRIMARY KEY (id);


--
-- TOC entry 2157 (class 2606 OID 18937)
-- Dependencies: 1724 1724
-- Name: real_datatype_pkey; Type: CONSTRAINT 
--

ALTER TABLE ONLY real_datatype
    ADD CONSTRAINT real_datatype_pkey PRIMARY KEY (id);


--
-- TOC entry 2159 (class 2606 OID 18939)
-- Dependencies: 1725 1725
-- Name: sc_datatype_pkey; Type: CONSTRAINT 
--

ALTER TABLE ONLY sc_datatype
    ADD CONSTRAINT sc_datatype_pkey PRIMARY KEY (id);


--
-- TOC entry 2161 (class 2606 OID 18941)
-- Dependencies: 1726 1726
-- Name: st_datatype_pkey; Type: CONSTRAINT 
--

ALTER TABLE ONLY st_datatype
    ADD CONSTRAINT st_datatype_pkey PRIMARY KEY (id);


--
-- TOC entry 2163 (class 2606 OID 18943)
-- Dependencies: 1727 1727
-- Name: st_nt_datatype_pkey; Type: CONSTRAINT 
--

ALTER TABLE ONLY st_nt_datatype
    ADD CONSTRAINT st_nt_datatype_pkey PRIMARY KEY (id);


--
-- TOC entry 2165 (class 2606 OID 18945)
-- Dependencies: 1728 1728
-- Name: tel_datatype_pkey; Type: CONSTRAINT 
--

ALTER TABLE ONLY tel_datatype
    ADD CONSTRAINT tel_datatype_pkey PRIMARY KEY (id);


--
-- TOC entry 2167 (class 2606 OID 18947)
-- Dependencies: 1729 1729
-- Name: tel_email_datatype_pkey; Type: CONSTRAINT 
--

ALTER TABLE ONLY tel_email_datatype
    ADD CONSTRAINT tel_email_datatype_pkey PRIMARY KEY (id);


--
-- TOC entry 2169 (class 2606 OID 18949)
-- Dependencies: 1730 1730
-- Name: tel_person_datatype_pkey; Type: CONSTRAINT 
--

ALTER TABLE ONLY tel_person_datatype
    ADD CONSTRAINT tel_person_datatype_pkey PRIMARY KEY (id);


--
-- TOC entry 2171 (class 2606 OID 18951)
-- Dependencies: 1731 1731
-- Name: tel_phone_datatype_pkey; Type: CONSTRAINT 
--

ALTER TABLE ONLY tel_phone_datatype
    ADD CONSTRAINT tel_phone_datatype_pkey PRIMARY KEY (id);


--
-- TOC entry 2173 (class 2606 OID 18953)
-- Dependencies: 1732 1732
-- Name: tel_url_datatype_pkey; Type: CONSTRAINT 
--

ALTER TABLE ONLY tel_url_datatype
    ADD CONSTRAINT tel_url_datatype_pkey PRIMARY KEY (id);


--
-- TOC entry 2175 (class 2606 OID 18955)
-- Dependencies: 1733 1733
-- Name: ts_datatype_pkey; Type: CONSTRAINT 
--

ALTER TABLE ONLY ts_datatype
    ADD CONSTRAINT ts_datatype_pkey PRIMARY KEY (id);


--
-- TOC entry 2002 (class 1259 OID 19191)
-- Dependencies: 1649
-- Name: fk_ad_ad_datatype__ad_datatype1; Type: INDEX 
--

CREATE INDEX fk_ad_ad_datatype__ad_datatype1 ON ad_ad_datatype_value9 USING btree (ad_datatype_id);


--
-- TOC entry 2006 (class 1259 OID 19193)
-- Dependencies: 1651
-- Name: fk_ad_datatype_val_ad_datatype1; Type: INDEX 
--

CREATE INDEX fk_ad_datatype_val_ad_datatype1 ON ad_datatype_value7 USING btree (ad_datatype_id);


--
-- TOC entry 2005 (class 1259 OID 19192)
-- Dependencies: 1650
-- Name: fk_ad_datatype_value81; Type: INDEX 
--

CREATE INDEX fk_ad_datatype_value81 ON ad_datatype USING btree (ad_datatype_value8_id);


--
-- TOC entry 2017 (class 1259 OID 19194)
-- Dependencies: 1656
-- Name: fk_cd_cd_datatype__cd_datatype1; Type: INDEX 
--

CREATE INDEX fk_cd_cd_datatype__cd_datatype1 ON cd_cd_datatype_value8 USING btree (cd_data_type_id);


--
-- TOC entry 2020 (class 1259 OID 19195)
-- Dependencies: 1657
-- Name: fk_cd_datatype_value71; Type: INDEX 
--

CREATE INDEX fk_cd_datatype_value71 ON cd_datatype USING btree (cd_datatype_value7_id);


--
-- TOC entry 2031 (class 1259 OID 19196)
-- Dependencies: 1662
-- Name: fk_dser_ad_dset__dset_ad_data1; Type: INDEX 
--

CREATE INDEX fk_dser_ad_dset__dset_ad_data1 ON dset_ad_dset_ad_value8 USING btree (dset_ad_datatype_id);


--
-- TOC entry 2046 (class 1259 OID 19197)
-- Dependencies: 1669
-- Name: fk_dset_ad_value_dset_ad71; Type: INDEX 
--

CREATE INDEX fk_dset_ad_value_dset_ad71 ON dset_ad_value7 USING btree (dset_ad_datatype_id);


--
-- TOC entry 2053 (class 1259 OID 19198)
-- Dependencies: 1672
-- Name: fk_dset_cd_cd_va_dset_cd51; Type: INDEX 
--

CREATE INDEX fk_dset_cd_cd_va_dset_cd51 ON dset_cd_cd_value7 USING btree (dset_cd_value7_id);


--
-- TOC entry 2066 (class 1259 OID 19199)
-- Dependencies: 1678
-- Name: fk_dset_cd_value6_dset_cd1; Type: INDEX 
--

CREATE INDEX fk_dset_cd_value6_dset_cd1 ON dset_cd_value6 USING btree (dset_cd_id);


--
-- TOC entry 2089 (class 1259 OID 19200)
-- Dependencies: 1689
-- Name: fk_dset_ii_ii_value6_dset_ii1; Type: INDEX 
--

CREATE INDEX fk_dset_ii_ii_value6_dset_ii1 ON dset_ii_ii_value6 USING btree (dset_ii_id);


--
-- TOC entry 2100 (class 1259 OID 19201)
-- Dependencies: 1694
-- Name: fk_dset_ii_value5_dset_ii1; Type: INDEX 
--

CREATE INDEX fk_dset_ii_value5_dset_ii1 ON dset_ii_value5 USING btree (dset_ii_id);


--
-- TOC entry 2120 (class 1259 OID 19203)
-- Dependencies: 1704
-- Name: fk_dset_tel_tel__dset_tel_val1; Type: INDEX 
--

CREATE INDEX fk_dset_tel_tel__dset_tel_val1 ON dset_tel_tel_value_3 USING btree (dset_tel_value3_id);


--
-- TOC entry 2129 (class 1259 OID 19204)
-- Dependencies: 1708
-- Name: fk_dset_tel_value2_dset_tel1; Type: INDEX 
--

CREATE INDEX fk_dset_tel_value2_dset_tel1 ON dset_tel_value2 USING btree (dset_tel_id);


--
-- TOC entry 2109 (class 1259 OID 19202)
-- Dependencies: 1699
-- Name: fk_tel_email_val_dset_tel_ema1; Type: INDEX 
--

CREATE INDEX fk_tel_email_val_dset_tel_ema1 ON dset_tel_email_value2 USING btree (dset_tel_email_id);


--
-- TOC entry 2177 (class 2606 OID 18961)
-- Dependencies: 2009 1653 1649
-- Name: fk_ad_ad_datatyp_ad_datatype_v; Type: FK CONSTRAINT; 
--

ALTER TABLE ONLY ad_ad_datatype_value9
    ADD CONSTRAINT fk_ad_ad_datatyp_ad_datatype_v FOREIGN KEY (ad_datatype_value9_id) REFERENCES ad_datatype_value9(id) MATCH FULL;


--
-- TOC entry 2176 (class 2606 OID 18956)
-- Dependencies: 2003 1649 1650
-- Name: fk_ad_ad_datatype__ad_datatype; Type: FK CONSTRAINT; 
--

ALTER TABLE ONLY ad_ad_datatype_value9
    ADD CONSTRAINT fk_ad_ad_datatype__ad_datatype FOREIGN KEY (ad_datatype_id) REFERENCES ad_datatype(id) MATCH FULL;


--
-- TOC entry 2179 (class 2606 OID 18971)
-- Dependencies: 1651 2003 1650
-- Name: fk_ad_datatype_val_ad_datatype; Type: FK CONSTRAINT; 
--

ALTER TABLE ONLY ad_datatype_value7
    ADD CONSTRAINT fk_ad_datatype_val_ad_datatype FOREIGN KEY (ad_datatype_id) REFERENCES ad_datatype(id) MATCH FULL;


--
-- TOC entry 2178 (class 2606 OID 18966)
-- Dependencies: 2007 1652 1650
-- Name: fk_ad_datatype_value8; Type: FK CONSTRAINT; 
--

ALTER TABLE ONLY ad_datatype
    ADD CONSTRAINT fk_ad_datatype_value8 FOREIGN KEY (ad_datatype_value8_id) REFERENCES ad_datatype_value8(id) MATCH FULL;


--
-- TOC entry 2181 (class 2606 OID 18981)
-- Dependencies: 1660 2025 1656
-- Name: fk_cd_cd_datatyp_cd_datatype_v; Type: FK CONSTRAINT; 
--

ALTER TABLE ONLY cd_cd_datatype_value8
    ADD CONSTRAINT fk_cd_cd_datatyp_cd_datatype_v FOREIGN KEY (cd_datatype_value8_id) REFERENCES cd_datatype_value8(id) MATCH FULL;


--
-- TOC entry 2180 (class 2606 OID 18976)
-- Dependencies: 1657 2018 1656
-- Name: fk_cd_cd_datatype__cd_datatype; Type: FK CONSTRAINT; 
--

ALTER TABLE ONLY cd_cd_datatype_value8
    ADD CONSTRAINT fk_cd_cd_datatype__cd_datatype FOREIGN KEY (cd_data_type_id) REFERENCES cd_datatype(id) MATCH FULL;


--
-- TOC entry 2183 (class 2606 OID 18991)
-- Dependencies: 1658 2018 1657
-- Name: fk_cd_datatype_val_cd_datatype; Type: FK CONSTRAINT; 
--

ALTER TABLE ONLY cd_datatype_value6
    ADD CONSTRAINT fk_cd_datatype_val_cd_datatype FOREIGN KEY (cd_datatype_id) REFERENCES cd_datatype(id) MATCH FULL;


--
-- TOC entry 2182 (class 2606 OID 18986)
-- Dependencies: 2023 1657 1659
-- Name: fk_cd_datatype_value7; Type: FK CONSTRAINT; 
--

ALTER TABLE ONLY cd_datatype
    ADD CONSTRAINT fk_cd_datatype_value7 FOREIGN KEY (cd_datatype_value7_id) REFERENCES cd_datatype_value7(id) MATCH FULL;


--
-- TOC entry 2188 (class 2606 OID 19016)
-- Dependencies: 2027 1661 1665
-- Name: fk_dser_ad_datat_val_ad3; Type: FK CONSTRAINT; 
--

ALTER TABLE ONLY dset_ad_value3
    ADD CONSTRAINT fk_dser_ad_datat_val_ad3 FOREIGN KEY (dset_ad_datatype_id) REFERENCES dset_ad_datatype(id) MATCH FULL;


--
-- TOC entry 2190 (class 2606 OID 19026)
-- Dependencies: 1667 2027 1661
-- Name: fk_dser_ad_datat_val_ad5; Type: FK CONSTRAINT; 
--

ALTER TABLE ONLY dset_ad_value5
    ADD CONSTRAINT fk_dser_ad_datat_val_ad5 FOREIGN KEY (dset_ad_datatype_id) REFERENCES dset_ad_datatype(id) MATCH FULL;


--
-- TOC entry 2187 (class 2606 OID 19011)
-- Dependencies: 1664 1661 2027
-- Name: fk_dser_ad_datat_val_ad_datat; Type: FK CONSTRAINT; 
--

ALTER TABLE ONLY dset_ad_value2
    ADD CONSTRAINT fk_dser_ad_datat_val_ad_datat FOREIGN KEY (dset_ad_datatype_id) REFERENCES dset_ad_datatype(id) MATCH FULL;


--
-- TOC entry 2186 (class 2606 OID 19006)
-- Dependencies: 1663 2027 1661
-- Name: fk_dser_ad_dataty_val_ad_da; Type: FK CONSTRAINT; 
--

ALTER TABLE ONLY dset_ad_value1
    ADD CONSTRAINT fk_dser_ad_dataty_val_ad_da FOREIGN KEY (dset_ad_datatype_id) REFERENCES dset_ad_datatype(id) MATCH FULL;


--
-- TOC entry 2184 (class 2606 OID 18996)
-- Dependencies: 2027 1661 1662
-- Name: fk_dser_ad_dset__dset_ad_data; Type: FK CONSTRAINT; 
--

ALTER TABLE ONLY dset_ad_dset_ad_value8
    ADD CONSTRAINT fk_dser_ad_dset__dset_ad_data FOREIGN KEY (dset_ad_datatype_id) REFERENCES dset_ad_datatype(id) MATCH FULL;


--
-- TOC entry 2185 (class 2606 OID 19001)
-- Dependencies: 1670 2047 1662
-- Name: fk_dser_ad_dset__dset_ad_valu; Type: FK CONSTRAINT; 
--

ALTER TABLE ONLY dset_ad_dset_ad_value8
    ADD CONSTRAINT fk_dser_ad_dset__dset_ad_valu FOREIGN KEY (dset_ad_value8_id) REFERENCES dset_ad_value8(id) MATCH FULL;


--
-- TOC entry 2189 (class 2606 OID 19021)
-- Dependencies: 1666 2027 1661
-- Name: fk_dser_ad_type_val_ad4; Type: FK CONSTRAINT; 
--

ALTER TABLE ONLY dset_ad_value4
    ADD CONSTRAINT fk_dser_ad_type_val_ad4 FOREIGN KEY (dset_ad_datatype_id) REFERENCES dset_ad_datatype(id) MATCH FULL;


--
-- TOC entry 2191 (class 2606 OID 19031)
-- Dependencies: 2027 1668 1661
-- Name: fk_dser_ad_type_val_ad6; Type: FK CONSTRAINT; 
--

ALTER TABLE ONLY dset_ad_value6
    ADD CONSTRAINT fk_dser_ad_type_val_ad6 FOREIGN KEY (dset_ad_datatype_id) REFERENCES dset_ad_datatype(id) MATCH FULL;


--
-- TOC entry 2192 (class 2606 OID 19036)
-- Dependencies: 2027 1661 1669
-- Name: fk_dset_ad_value_dset_ad7; Type: FK CONSTRAINT; 
--

ALTER TABLE ONLY dset_ad_value7
    ADD CONSTRAINT fk_dset_ad_value_dset_ad7 FOREIGN KEY (dset_ad_datatype_id) REFERENCES dset_ad_datatype(id) MATCH FULL;


--
-- TOC entry 2194 (class 2606 OID 19046)
-- Dependencies: 1679 1672 2067
-- Name: fk_dset_cd_cd_va_dset_cd5; Type: FK CONSTRAINT; 
--

ALTER TABLE ONLY dset_cd_cd_value7
    ADD CONSTRAINT fk_dset_cd_cd_va_dset_cd5 FOREIGN KEY (dset_cd_value7_id) REFERENCES dset_cd_value7(id) MATCH FULL;


--
-- TOC entry 2193 (class 2606 OID 19041)
-- Dependencies: 1671 1672 2049
-- Name: fk_dset_cd_cd_value7_dset_cd; Type: FK CONSTRAINT; 
--

ALTER TABLE ONLY dset_cd_cd_value7
    ADD CONSTRAINT fk_dset_cd_cd_value7_dset_cd FOREIGN KEY (dset_cd_id) REFERENCES dset_cd(id) MATCH FULL;


--
-- TOC entry 2195 (class 2606 OID 19051)
-- Dependencies: 1673 2049 1671
-- Name: fk_dset_cd_value1_dset_cd; Type: FK CONSTRAINT; 
--

ALTER TABLE ONLY dset_cd_value1
    ADD CONSTRAINT fk_dset_cd_value1_dset_cd FOREIGN KEY (dset_cd_id) REFERENCES dset_cd(id) MATCH FULL;


--
-- TOC entry 2196 (class 2606 OID 19056)
-- Dependencies: 2049 1674 1671
-- Name: fk_dset_cd_value2_dset_cd; Type: FK CONSTRAINT; 
--

ALTER TABLE ONLY dset_cd_value2
    ADD CONSTRAINT fk_dset_cd_value2_dset_cd FOREIGN KEY (dset_cd_id) REFERENCES dset_cd(id) MATCH FULL;


--
-- TOC entry 2197 (class 2606 OID 19061)
-- Dependencies: 1675 2049 1671
-- Name: fk_dset_cd_value3_dset_cd; Type: FK CONSTRAINT; 
--

ALTER TABLE ONLY dset_cd_value3
    ADD CONSTRAINT fk_dset_cd_value3_dset_cd FOREIGN KEY (dset_cd_id) REFERENCES dset_cd(id) MATCH FULL;


--
-- TOC entry 2198 (class 2606 OID 19066)
-- Dependencies: 1671 1676 2049
-- Name: fk_dset_cd_value4_dset_cd; Type: FK CONSTRAINT; 
--

ALTER TABLE ONLY dset_cd_value4
    ADD CONSTRAINT fk_dset_cd_value4_dset_cd FOREIGN KEY (dset_cd_id) REFERENCES dset_cd(id) MATCH FULL;


--
-- TOC entry 2199 (class 2606 OID 19071)
-- Dependencies: 1677 2049 1671
-- Name: fk_dset_cd_value5_dset_cd; Type: FK CONSTRAINT; 
--

ALTER TABLE ONLY dset_cd_value5
    ADD CONSTRAINT fk_dset_cd_value5_dset_cd FOREIGN KEY (dset_cd_id) REFERENCES dset_cd(id) MATCH FULL;


--
-- TOC entry 2200 (class 2606 OID 19076)
-- Dependencies: 2049 1671 1678
-- Name: fk_dset_cd_value6_dset_cd; Type: FK CONSTRAINT; 
--

ALTER TABLE ONLY dset_cd_value6
    ADD CONSTRAINT fk_dset_cd_value6_dset_cd FOREIGN KEY (dset_cd_id) REFERENCES dset_cd(id) MATCH FULL;


--
-- TOC entry 2208 (class 2606 OID 19116)
-- Dependencies: 2101 1689 1695
-- Name: fk_dset_ii_ii_va_dset_ii_val; Type: FK CONSTRAINT; 
--

ALTER TABLE ONLY dset_ii_ii_value6
    ADD CONSTRAINT fk_dset_ii_ii_va_dset_ii_val FOREIGN KEY (dset_ii_value6_id) REFERENCES dset_ii_value6(id) MATCH FULL;


--
-- TOC entry 2207 (class 2606 OID 19111)
-- Dependencies: 1689 1688 2085
-- Name: fk_dset_ii_ii_value6_dset_ii; Type: FK CONSTRAINT; 
--

ALTER TABLE ONLY dset_ii_ii_value6
    ADD CONSTRAINT fk_dset_ii_ii_value6_dset_ii FOREIGN KEY (dset_ii_id) REFERENCES dset_ii(id) MATCH FULL;


--
-- TOC entry 2209 (class 2606 OID 19121)
-- Dependencies: 1690 1688 2085
-- Name: fk_dset_ii_value1_dsetii; Type: FK CONSTRAINT; 
--

ALTER TABLE ONLY dset_ii_value1
    ADD CONSTRAINT fk_dset_ii_value1_dsetii FOREIGN KEY (dset_ii_id) REFERENCES dset_ii(id) MATCH FULL;


--
-- TOC entry 2210 (class 2606 OID 19126)
-- Dependencies: 2085 1691 1688
-- Name: fk_dset_ii_value2_dsetii; Type: FK CONSTRAINT; 
--

ALTER TABLE ONLY dset_ii_value2
    ADD CONSTRAINT fk_dset_ii_value2_dsetii FOREIGN KEY (dset_ii_id) REFERENCES dset_ii(id) MATCH FULL;


--
-- TOC entry 2211 (class 2606 OID 19131)
-- Dependencies: 2085 1692 1688
-- Name: fk_dset_ii_value3_dsetii; Type: FK CONSTRAINT; 
--

ALTER TABLE ONLY dset_ii_value3
    ADD CONSTRAINT fk_dset_ii_value3_dsetii FOREIGN KEY (dset_ii_id) REFERENCES dset_ii(id) MATCH FULL;


--
-- TOC entry 2212 (class 2606 OID 19136)
-- Dependencies: 2085 1688 1693
-- Name: fk_dset_ii_value4_dsetii; Type: FK CONSTRAINT; 
--

ALTER TABLE ONLY dset_ii_value4
    ADD CONSTRAINT fk_dset_ii_value4_dsetii FOREIGN KEY (dset_ii_id) REFERENCES dset_ii(id) MATCH FULL;


--
-- TOC entry 2213 (class 2606 OID 19141)
-- Dependencies: 1688 1694 2085
-- Name: fk_dset_ii_value5_dset_ii; Type: FK CONSTRAINT; 
--

ALTER TABLE ONLY dset_ii_value5
    ADD CONSTRAINT fk_dset_ii_value5_dset_ii FOREIGN KEY (dset_ii_id) REFERENCES dset_ii(id) MATCH FULL;


--
-- TOC entry 2214 (class 2606 OID 19146)
-- Dependencies: 2105 1697 1698
-- Name: fk_dset_tel_emai_dset_tel_ema; Type: FK CONSTRAINT; 
--

ALTER TABLE ONLY dset_tel_email_value1
    ADD CONSTRAINT fk_dset_tel_emai_dset_tel_ema FOREIGN KEY (dset_tel_email_id) REFERENCES dset_tel_email(id) MATCH FULL;


--
-- TOC entry 2216 (class 2606 OID 19156)
-- Dependencies: 1700 1701 2110
-- Name: fk_dset_tel_pers_dset_tel_per; Type: FK CONSTRAINT; 
--

ALTER TABLE ONLY dset_tel_person_value1
    ADD CONSTRAINT fk_dset_tel_pers_dset_tel_per FOREIGN KEY (dset_tel_person_id) REFERENCES dset_tel_person(id) MATCH FULL;


--
-- TOC entry 2217 (class 2606 OID 19161)
-- Dependencies: 2114 1703 1702
-- Name: fk_dset_tel_phon_dset_tel_pho; Type: FK CONSTRAINT; 
--

ALTER TABLE ONLY dset_tel_phone_value1
    ADD CONSTRAINT fk_dset_tel_phon_dset_tel_pho FOREIGN KEY (dset_tel_phone_id) REFERENCES dset_tel_phone(id) MATCH FULL;


--
-- TOC entry 2219 (class 2606 OID 19171)
-- Dependencies: 2130 1704 1709
-- Name: fk_dset_tel_tel__dset_tel_val; Type: FK CONSTRAINT; 
--

ALTER TABLE ONLY dset_tel_tel_value_3
    ADD CONSTRAINT fk_dset_tel_tel__dset_tel_val FOREIGN KEY (dset_tel_value3_id) REFERENCES dset_tel_value3(id) MATCH FULL;


--
-- Name: fk_dset_tel_tel_valu_dset_tel; Type: FK CONSTRAINT; 
--

ALTER TABLE ONLY dset_tel_tel_value_3
    ADD CONSTRAINT fk_dset_tel_tel_valu_dset_tel FOREIGN KEY (dset_tel_id) REFERENCES dset_tel(id) MATCH FULL;


--
-- Name: fk_dset_tel_url_v_dset_tel_url; Type: FK CONSTRAINT; 
--

ALTER TABLE ONLY dset_tel_url_value1
    ADD CONSTRAINT fk_dset_tel_url_v_dset_tel_url FOREIGN KEY (dset_tel_url_id) REFERENCES dset_tel_url(id) MATCH FULL;


--
-- Name: fk_dset_tel_value1_dset_tel; Type: FK CONSTRAINT; 
--

ALTER TABLE ONLY dset_tel_value1
    ADD CONSTRAINT fk_dset_tel_value1_dset_tel FOREIGN KEY (dset_tel_id) REFERENCES dset_tel(id) MATCH FULL;


--
-- Name: fk_dset_tel_value2_dset_tel; Type: FK CONSTRAINT; 
--

ALTER TABLE ONLY dset_tel_value2
    ADD CONSTRAINT fk_dset_tel_value2_dset_tel FOREIGN KEY (dset_tel_id) REFERENCES dset_tel(id) MATCH FULL;


--
-- Name: fk_en_datatype_val4_en_datat; Type: FK CONSTRAINT; 
--

ALTER TABLE ONLY dset_en_value4
    ADD CONSTRAINT fk_en_datatype_val4_en_datat FOREIGN KEY (dset_en_datatype_id) REFERENCES dset_en(id) MATCH FULL;


--
-- Name: fk_en_datatype_val6_en_datat; Type: FK CONSTRAINT; 
--

ALTER TABLE ONLY dset_en_value6
    ADD CONSTRAINT fk_en_datatype_val6_en_datat FOREIGN KEY (dset_en_datatype_id) REFERENCES dset_en(id) MATCH FULL;


--
-- Name: fk_en_datatype_val7_en_datat; Type: FK CONSTRAINT; 
--

ALTER TABLE ONLY dset_en_value7
    ADD CONSTRAINT fk_en_datatype_val7_en_datat FOREIGN KEY (dset_en_datatype_id) REFERENCES dset_en(id) MATCH FULL;


--
-- Name: fk_en_type_val2_en_type; Type: FK CONSTRAINT; 
--

ALTER TABLE ONLY dset_en_value2
    ADD CONSTRAINT fk_en_type_val2_en_type FOREIGN KEY (dset_en_datatype_id) REFERENCES dset_en(id) MATCH FULL;


--
-- Name: fk_en_type_val3_en_type; Type: FK CONSTRAINT; 
--

ALTER TABLE ONLY dset_en_value3
    ADD CONSTRAINT fk_en_type_val3_en_type FOREIGN KEY (dset_en_datatype_id) REFERENCES dset_en(id) MATCH FULL;


--
-- Name: fk_en_type_val_en_type; Type: FK CONSTRAINT; 
--

ALTER TABLE ONLY dset_en_value1
    ADD CONSTRAINT fk_en_type_val_en_type FOREIGN KEY (dset_en_datatype_id) REFERENCES dset_en(id) MATCH FULL;


--
-- Name: fk_tel_email_val_dset_tel_ema; Type: FK CONSTRAINT; 
--

ALTER TABLE ONLY dset_tel_email_value2
    ADD CONSTRAINT fk_tel_email_val_dset_tel_ema FOREIGN KEY (dset_tel_email_id) REFERENCES dset_tel_email(id) MATCH FULL;

