CREATE TABLE ad_datatype_value8 (
  id int(8) NOT NULL,
  al_value varchar(50)  default NULL,
  al_code varchar(50)  default NULL,
  al_codesystem varchar(50)  default NULL,
  dal_code varchar(50)  default NULL,
  dal_value varchar(50)  default NULL,
  dal_codesystem varchar(50)  default NULL,
  cty_value varchar(50)  default NULL,
  cty_code varchar(50)  default NULL,
  cty_codesystem varchar(50)  default NULL,
  PRIMARY KEY  (id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE ad_datatype_value9 (
  id int(8) NOT NULL,
  al_value varchar(50)  default NULL,
  al_code varchar(50)  default NULL,
  al_codesystem varchar(50)  default NULL,
  dal_code varchar(50)  default NULL,
  dal_value varchar(50)  default NULL,
  dal_codesystem varchar(50)  default NULL,
  cty_value varchar(50)  default NULL,
  cty_code varchar(50)  default NULL,
  cty_codesystem varchar(50)  default NULL,
  PRIMARY KEY  (id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


CREATE TABLE ad_datatype (
  id int(8) NOT NULL,
  value1_al_value varchar(50)  default NULL,
  value2_dal_value varchar(50)  default NULL,
  value2_dal_code varchar(50)  default NULL,
  value3_al_value varchar(50)  default NULL,
  value3_al_code varchar(50)  default NULL,
  value3_al_codesystem varchar(50)  default NULL,
  value4_al1_value varchar(50)  default NULL,
  value4_al2_value varchar(50)  default NULL,
  value4_al1_code varchar(50)  default NULL,
  value4_al2_code varchar(50)  default NULL,
  value4_al1_codesystem varchar(50)  default NULL,
  value4_al2_codesystem varchar(50)  default NULL,
  value5_al_value varchar(50)  default NULL,
  value5_al_code varchar(50)  default NULL,
  value5_al_codesystem varchar(50)  default NULL,
  value5_dal_code varchar(50)  default NULL,
  value5_dal_value varchar(50)  default NULL,
  value5_dal_codesystem varchar(50)  default NULL,
  value5_cty_value varchar(50)  default NULL,
  value5_cty_code varchar(50)  default NULL,
  value5_cty_codesystem varchar(50)  default NULL,
  value6_adl_value varchar(50)  default NULL,
  value6_al_value varchar(50)  default NULL,
  value6_bnn_value varchar(50)  default NULL,
  value6_bnr_value varchar(50)  default NULL,
  value6_bns_value varchar(50)  default NULL,
  value6_car_value varchar(50)  default NULL,
  value6_cen_value varchar(50)  default NULL,
  value6_cnt_value varchar(50)  default NULL,
  value6_cpa_value varchar(50)  default NULL,
  value6_cty_value varchar(50)  default NULL,
  value6_dal_value varchar(50)  default NULL,
  value6_del_value varchar(50)  default NULL,
  value6_dinsta_value varchar(50)  default NULL,
  value6_dinstq_value varchar(50)  default NULL,
  value6_dir_value varchar(50)  default NULL,
  value6_dmod_value varchar(50)  default NULL,
  value6_dmodid_value varchar(50)  default NULL,
  value6_int_value varchar(50)  default NULL,
  value6_pob_value varchar(50)  default NULL,
  value6_pre_value varchar(50)  default NULL,
  value6_sal_value varchar(50)  default NULL,
  value6_sta_value varchar(50)  default NULL,
  value6_stb_value varchar(50)  default NULL,
  value6_str_value varchar(50)  default NULL,
  value6_sttyp_value varchar(50)  default NULL,
  value6_unid_value varchar(50)  default NULL,
  value6_unit_value varchar(50)  default NULL,
  value6_zip_value varchar(50)  default NULL,
  value6_adl_code varchar(50)  default NULL,
  value6_bns_code varchar(50)  default NULL,
  value6_bns_codesystem varchar(50)  default NULL,
  value6_dal_code varchar(50)  default NULL,
  value6_dal_codesystem varchar(50)  default NULL,
  value6_int_code varchar(50)  default NULL,
  value6_int_codesystem varchar(50)  default NULL,
  value6_stb_code varchar(50)  default NULL,
  value6_stb_codesystem varchar(50)  default NULL,
  value6_zip_codesystem varchar(50)  default NULL,
  value6_zip_code varchar(50)  default NULL,
  ad_datatype_value8_id int(8) default NULL,
  PRIMARY KEY  (id),
  KEY fk_ad_datatype_value8 (ad_datatype_value8_id),
  CONSTRAINT fk_ad_datatype_value8 FOREIGN KEY (ad_datatype_value8_id) REFERENCES ad_datatype_value8 (id) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE ad_datatype_value7 (
  ad_datatype_id int(8) default NULL,
  al_value varchar(50)  default NULL,
  al_code varchar(50)  default NULL,
  al_codesystem varchar(50)  default NULL,
  dal_code varchar(50)  default NULL,
  dal_value varchar(50)  default NULL,
  dal_codesystem varchar(50)  default NULL,
  cty_value varchar(50)  default NULL,
  cty_code varchar(50)  default NULL,
  cty_codesystem varchar(50)  default NULL,
  KEY fk_ad_datatype_val_ad_datatype (ad_datatype_id),
  CONSTRAINT fk_ad_datatype_val_ad_datatype FOREIGN KEY (ad_datatype_id) REFERENCES ad_datatype (id) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;




CREATE TABLE ad_ad_datatype_value9 (
  ad_datatype_id int(8) NOT NULL,
  ad_datatype_value9_id int(8) NOT NULL,
  PRIMARY KEY  (ad_datatype_value9_id,ad_datatype_id),
  KEY fk_ad_ad_datatype__ad_datatype (ad_datatype_id),
  CONSTRAINT fk_ad_ad_datatype__ad_datatype FOREIGN KEY (ad_datatype_id) REFERENCES ad_datatype (id) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT fk_ad_ad_datatyp_ad_datatype_v FOREIGN KEY (ad_datatype_value9_id) REFERENCES ad_datatype_value9 (id) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


CREATE TABLE bl_datatype (
  id int(8) NOT NULL,
  value1_value varchar(1)  default NULL,
  value2_null_flavor varchar(50)  default NULL,
  value2_value varchar(1)  default NULL,
  PRIMARY KEY  (id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE bl_nonnull_datatype (
  id int(8) NOT NULL,
  value1_value varchar(1)  default NULL,
  PRIMARY KEY  (id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



CREATE TABLE cd_datatype_value7 (
  id int(8) NOT NULL,
  null_flavor varchar(50)  default NULL,
  code varchar(50)  default NULL,
  code_system varchar(50)  default NULL,
  code_system_name varchar(50)  default NULL,
  code_system_version varchar(50)  default NULL,
  display_null_flavor varchar(50)  default NULL,
  display_value varchar(50)  default NULL,
  orig_txt_null_flavor varchar(50)  default NULL,
  orig_txt_value longtext ,
  orig_txt_description varchar(50)  default NULL,
  PRIMARY KEY  (id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE cd_datatype_value8 (
  id int(8) NOT NULL,
  null_flavor varchar(50)  default NULL,
  code varchar(50)  default NULL,
  code_system varchar(50)  default NULL,
  code_system_name varchar(50)  default NULL,
  code_system_version varchar(50)  default NULL,
  display_null_flavor varchar(50)  default NULL,
  display_value varchar(50)  default NULL,
  orig_txt_null_flavor varchar(50)  default NULL,
  orig_txt_value longtext ,
  orig_txt_description varchar(50)  default NULL,
  PRIMARY KEY  (id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE cd_datatype (
  id int(8) NOT NULL,
  value1_code varchar(50)  default NULL,
  value2_null_flavor varchar(50)  default NULL,
  value2_code varchar(50)  default NULL,
  value3_code varchar(50)  default NULL,
  value4_null_flavor varchar(50)  default NULL,
  value4_code varchar(50)  default NULL,
  value4_code_system varchar(50)  default NULL,
  value4_code_system_version varchar(50)  default NULL,
  value4_code_system_name varchar(50)  default NULL,
  value4_display_null_flavor varchar(50)  default NULL,
  value4_display_value varchar(50)  default NULL,
  value4_orig_txt_null_flavor varchar(50)  default NULL,
  value4_orig_txt_value longtext ,
  value4_orig_txt_description varchar(50)  default NULL,
  value5_null_flavor varchar(50)  default NULL,
  value5_code varchar(50)  default NULL,
  value5_code_system varchar(50)  default NULL,
  value5_code_system_name varchar(50)  default NULL,
  value5_code_system_version varchar(50)  default NULL,
  value5_display_null_flavor varchar(50)  default NULL,
  value5_display_value varchar(50)  default NULL,
  value5_orig_txt_null_flavor varchar(50)  default NULL,
  value5_orig_txt_value longtext ,
  value5_orig_txt_description varchar(50)  default NULL,
  cd_datatype_value7_id int(8) default NULL,
  PRIMARY KEY  (id),
  KEY fk_cd_datatype_value7 (cd_datatype_value7_id),
  CONSTRAINT fk_cd_datatype_value7 FOREIGN KEY (cd_datatype_value7_id) REFERENCES cd_datatype_value7 (id) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE cd_datatype_value6 (
  cd_datatype_id int(8) NOT NULL,
  null_flavor varchar(50)  default NULL,
  code varchar(50)  default NULL,
  code_system varchar(50)  default NULL,
  code_system_name varchar(50)  default NULL,
  code_system_version varchar(50)  default NULL,
  display_null_flavor varchar(50)  default NULL,
  display_value varchar(50)  default NULL,
  orig_txt_null_flavor varchar(50)  default NULL,
  orig_txt_value longtext ,
  orig_txt_description varchar(50)  default NULL,
  PRIMARY KEY  (cd_datatype_id),
  CONSTRAINT fk_cd_datatype_val_cd_datatype FOREIGN KEY (cd_datatype_id) REFERENCES cd_datatype (id) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE cd_cd_datatype_value8 (
  cd_data_type_id int(8) NOT NULL,
  cd_datatype_value8_id int(8) NOT NULL,
  PRIMARY KEY  (cd_datatype_value8_id,cd_data_type_id),
  KEY fk_cd_cd_datatype__cd_datatype (cd_data_type_id),
  CONSTRAINT fk_cd_cd_datatype__cd_datatype FOREIGN KEY (cd_data_type_id) REFERENCES cd_datatype (id) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT fk_cd_cd_datatyp_cd_datatype_v FOREIGN KEY (cd_datatype_value8_id) REFERENCES cd_datatype_value8 (id) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


CREATE TABLE dset_ad_datatype (
  id int(8) NOT NULL,
  PRIMARY KEY  (id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



CREATE TABLE dset_ad_value1 (
  dset_ad_datatype_id int(8) NOT NULL,
  adxp_al_value varchar(50)  default NULL,
  PRIMARY KEY  (dset_ad_datatype_id),
  CONSTRAINT fk_dser_ad_dataty_val_ad_da FOREIGN KEY (dset_ad_datatype_id) REFERENCES dset_ad_datatype (id) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE dset_ad_value2 (
  dset_ad_datatype_id int(8) NOT NULL,
  adxp_dal_value varchar(50)  default NULL,
  adxp_dal_code varchar(50)  default NULL,
  PRIMARY KEY  (dset_ad_datatype_id),
  CONSTRAINT fk_dser_ad_datat_val_ad_datat FOREIGN KEY (dset_ad_datatype_id) REFERENCES dset_ad_datatype (id) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE dset_ad_value3 (
  dset_ad_datatype_id int(8) NOT NULL,
  adxp_al_value varchar(50)  default NULL,
  adxp_al_code varchar(50)  default NULL,
  adxp_al_codesystem varchar(50)  default NULL,
  PRIMARY KEY  (dset_ad_datatype_id),
  CONSTRAINT fk_dser_ad_datat_val_ad3 FOREIGN KEY (dset_ad_datatype_id) REFERENCES dset_ad_datatype (id) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE dset_ad_value4 (
  dset_ad_datatype_id int(8) NOT NULL,
  adxp_al1_value varchar(50)  default NULL,
  adxp_al2_value varchar(50)  default NULL,
  adxp_al1_code varchar(50)  default NULL,
  adxp_al2_code varchar(50)  default NULL,
  adxp_al2_codesystem varchar(50)  default NULL,
  adxp_al1_codesystem varchar(50)  default NULL,
  PRIMARY KEY  (dset_ad_datatype_id),
  CONSTRAINT fk_dser_ad_type_val_ad4 FOREIGN KEY (dset_ad_datatype_id) REFERENCES dset_ad_datatype (id) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE dset_ad_value5 (
  dset_ad_datatype_id int(8) NOT NULL,
  adxp_al_value varchar(50)  default NULL,
  adxp_al_code varchar(50)  default NULL,
  adxp_al_codesystem varchar(50)  default NULL,
  adxp_dal_code varchar(50)  default NULL,
  adxp_dal_codesystem varchar(50)  default NULL,
  adxp_dal_value varchar(50)  default NULL,
  adxp_cty_value varchar(50)  default NULL,
  adxp_cty_code varchar(50)  default NULL,
  adxp_cty_codesystem varchar(50)  default NULL,
  PRIMARY KEY  (dset_ad_datatype_id),
  CONSTRAINT fk_dser_ad_datat_val_ad5 FOREIGN KEY (dset_ad_datatype_id) REFERENCES dset_ad_datatype (id) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE dset_ad_value6 (
  dset_ad_datatype_id int(8) NOT NULL,
  adxp_adl_value varchar(50)  default NULL,
  adxp_al_value varchar(50)  default NULL,
  adxp_bnn_value varchar(50)  default NULL,
  adxp_bnr_value varchar(50)  default NULL,
  adxp_bns_value varchar(50)  default NULL,
  adxp_car_value varchar(50)  default NULL,
  adxp_cen_value varchar(50)  default NULL,
  adxp_cnt_value varchar(50)  default NULL,
  adxp_cpa_value varchar(50)  default NULL,
  adxp_cty_value varchar(50)  default NULL,
  adxp_dal_value varchar(50)  default NULL,
  adxp_del_value varchar(50)  default NULL,
  adxp_dinsta_value varchar(50)  default NULL,
  adxp_dinstq_value varchar(50)  default NULL,
  adxp_dir_value varchar(50)  default NULL,
  adxp_dmod_value varchar(50)  default NULL,
  adxp_dmodid_value varchar(50)  default NULL,
  adxp_int_value varchar(50)  default NULL,
  adxp_pob_value varchar(50)  default NULL,
  adxp_pre_value varchar(50)  default NULL,
  adxp_sal_value varchar(50)  default NULL,
  adxp_sta_value varchar(50)  default NULL,
  adxp_stb_value varchar(50)  default NULL,
  adxp_str_value varchar(50)  default NULL,
  adxp_sttyp_value varchar(50)  default NULL,
  adxp_unid_value varchar(50)  default NULL,
  adxp_unit_value varchar(50)  default NULL,
  adxp_zip_value varchar(50)  default NULL,
  adxp_adl_code varchar(50)  default NULL,
  adxp_bns_code varchar(50)  default NULL,
  adxp_bns_codesystem varchar(50)  default NULL,
  adxp_dal_code varchar(50)  default NULL,
  adxp_dal_codesystem varchar(50)  default NULL,
  adxp_int_code varchar(50)  default NULL,
  adxp_int_codesystem varchar(50)  default NULL,
  adxp_stb_code varchar(50)  default NULL,
  adxp_stb_codesystem varchar(50)  default NULL,
  adxp_zip_codesystem varchar(50)  default NULL,
  adxp_zip_code varchar(50)  default NULL,
  PRIMARY KEY  (dset_ad_datatype_id),
  CONSTRAINT fk_dser_ad_type_val_ad6 FOREIGN KEY (dset_ad_datatype_id) REFERENCES dset_ad_datatype (id) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE dset_ad_value7 (
  id int(8) NOT NULL,
  dset_ad_datatype_id int(8) default NULL,
  adxp_al_value varchar(50)  default NULL,
  adxp_al_code varchar(50)  default NULL,
  adxp_al_codesystem varchar(50)  default NULL,
  adxp_dal_code varchar(50)  default NULL,
  adxp_dal_codesystem varchar(50)  default NULL,
  adxp_dal_value varchar(50)  default NULL,
  adxp_cty_value varchar(50)  default NULL,
  adxp_cty_code varchar(50)  default NULL,
  adxp_cty_codesystem varchar(50)  default NULL,
  PRIMARY KEY  (id),
  KEY fk_dset_ad_value_dset_ad7 (dset_ad_datatype_id),
  CONSTRAINT fk_dset_ad_value_dset_ad7 FOREIGN KEY (dset_ad_datatype_id) REFERENCES dset_ad_datatype (id) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE dset_ad_value8 (
  id int(8) NOT NULL,
  adxp_al_value varchar(50)  default NULL,
  adxp_al_code varchar(50)  default NULL,
  adxp_al_codesystem varchar(50)  default NULL,
  adxp_dal_code varchar(50)  default NULL,
  adxp_dal_codesystem varchar(50)  default NULL,
  adxp_dal_value varchar(50)  default NULL,
  adxp_cty_value varchar(50)  default NULL,
  adxp_cty_code varchar(50)  default NULL,
  adxp_cty_codesystem varchar(50)  default NULL,
  PRIMARY KEY  (id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE dset_ad_dset_ad_value8 (
  dset_ad_datatype_id int(8) NOT NULL,
  dset_ad_value8_id int(8) NOT NULL,
  PRIMARY KEY  (dset_ad_value8_id,dset_ad_datatype_id),
  KEY fk_dser_ad_dset__dset_ad_data (dset_ad_datatype_id),
  CONSTRAINT fk_dser_ad_dset__dset_ad_data FOREIGN KEY (dset_ad_datatype_id) REFERENCES dset_ad_datatype (id) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT fk_dser_ad_dset__dset_ad_valu FOREIGN KEY (dset_ad_value8_id) REFERENCES dset_ad_value8 (id) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE dset_cd (
  id int(8) NOT NULL,
  PRIMARY KEY  (id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


CREATE TABLE dset_cd_value1 (
  dset_cd_id int(8) NOT NULL,
  code varchar(50)  default NULL,
  PRIMARY KEY  (dset_cd_id),
  CONSTRAINT fk_dset_cd_value1_dset_cd FOREIGN KEY (dset_cd_id) REFERENCES dset_cd (id) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE dset_cd_value2 (
  dset_cd_id int(8) NOT NULL,
  code varchar(50)  default NULL,
  null_flavor varchar(50)  default NULL,
  PRIMARY KEY  (dset_cd_id),
  CONSTRAINT fk_dset_cd_value2_dset_cd FOREIGN KEY (dset_cd_id) REFERENCES dset_cd (id) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE dset_cd_value3 (
  dset_cd_id int(8) NOT NULL,
  code varchar(50)  default NULL,
  code_system varchar(50)  default NULL,
  code_system_name varchar(50)  default NULL,
  PRIMARY KEY  (dset_cd_id),
  CONSTRAINT fk_dset_cd_value3_dset_cd FOREIGN KEY (dset_cd_id) REFERENCES dset_cd (id) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE dset_cd_value4 (
  dset_cd_id int(8) NOT NULL,
  code varchar(50)  default NULL,
  code_system varchar(50)  default NULL,
  code_system_name varchar(50)  default NULL,
  code_system_version varchar(50)  default NULL,
  displayable_value varchar(50)  default NULL,
  originaltext_value varchar(50)  default NULL,
  originaltext_desc varchar(50)  default NULL,
  PRIMARY KEY  (dset_cd_id),
  CONSTRAINT fk_dset_cd_value4_dset_cd FOREIGN KEY (dset_cd_id) REFERENCES dset_cd (id) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE dset_cd_value5 (
  dset_cd_id int(8) NOT NULL,
  code varchar(50)  default NULL,
  code_system varchar(50)  default NULL,
  code_system_name varchar(50)  default NULL,
  code_system_version varchar(50)  default NULL,
  displayable_value varchar(50)  default NULL,
  originaltext_value varchar(50)  default NULL,
  originaltext_desc varchar(50)  default NULL,
  PRIMARY KEY  (dset_cd_id),
  CONSTRAINT fk_dset_cd_value5_dset_cd FOREIGN KEY (dset_cd_id) REFERENCES dset_cd (id) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE dset_cd_value6 (
  id int(8) NOT NULL,
  dset_cd_id int(8) default NULL,
  code varchar(50)  default NULL,
  PRIMARY KEY  (id),
  KEY fk_dset_cd_value6_dset_cd (dset_cd_id),
  CONSTRAINT fk_dset_cd_value6_dset_cd FOREIGN KEY (dset_cd_id) REFERENCES dset_cd (id) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE dset_cd_value7 (
  id int(8) NOT NULL,
  code varchar(50)  default NULL,
  PRIMARY KEY  (id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE dset_cd_cd_value7 (
  dset_cd_id int(8) NOT NULL,
  dset_cd_value7_id int(8) NOT NULL,
  PRIMARY KEY  (dset_cd_id,dset_cd_value7_id),
  KEY fk_dset_cd_cd_va_dset_cd5 (dset_cd_value7_id),
  CONSTRAINT fk_dset_cd_cd_value7_dset_cd FOREIGN KEY (dset_cd_id) REFERENCES dset_cd (id) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT fk_dset_cd_cd_va_dset_cd5 FOREIGN KEY (dset_cd_value7_id) REFERENCES dset_cd_value7 (id) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE dset_en (
  id int(8) NOT NULL,
  PRIMARY KEY  (id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE dset_en_value1 (
  dset_en_datatype_id int(8) NOT NULL,
  enxp_pn_value varchar(50)  default NULL,
  PRIMARY KEY  (dset_en_datatype_id),
  CONSTRAINT fk_en_type_val_en_type FOREIGN KEY (dset_en_datatype_id) REFERENCES dset_en (id) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE dset_en_value2 (
  dset_en_datatype_id int(8) NOT NULL,
  enxp_pn_value varchar(50)  default NULL,
  enxp_pn_code varchar(50)  default NULL,
  PRIMARY KEY  (dset_en_datatype_id),
  CONSTRAINT fk_en_type_val2_en_type FOREIGN KEY (dset_en_datatype_id) REFERENCES dset_en (id) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE dset_en_value3 (
  dset_en_datatype_id int(8) NOT NULL,
  enxp_pn_code varchar(50)  default NULL,
  enxp_pn_value varchar(50)  default NULL,
  enxp_pn_codesystem varchar(50)  default NULL,
  enxp_pn_codesystemversion varchar(50)  default NULL,
  PRIMARY KEY  (dset_en_datatype_id),
  CONSTRAINT fk_en_type_val3_en_type FOREIGN KEY (dset_en_datatype_id) REFERENCES dset_en (id) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE dset_en_value4 (
  dset_en_datatype_id int(8) NOT NULL,
  enxp_pn_value varchar(50)  default NULL,
  enxp_pn_enpq varchar(50)  default NULL,
  PRIMARY KEY  (dset_en_datatype_id),
  CONSTRAINT fk_en_datatype_val4_en_datat FOREIGN KEY (dset_en_datatype_id) REFERENCES dset_en (id) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE dset_en_value5 (
  dset_en_datatype_id int(8) NOT NULL,
  enxp_pn_value varchar(50)  default NULL,
  enxp_pn2_value varchar(50)  default NULL,
  enxp_pn_code varchar(50)  default NULL,
  enxp_pn2_code varchar(50)  default NULL,
  enxp_pn_codesystem varchar(50)  default NULL,
  enxp_pn2_codesystem varchar(50)  default NULL,
  enxp_pn2_codesystemversion varchar(50)  default NULL,
  enxp_pn_codesystemversion varchar(50)  default NULL,
  PRIMARY KEY  (dset_en_datatype_id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE dset_en_value6 (
  dset_en_datatype_id int(8) NOT NULL,
  enxp_pn_value varchar(50)  default NULL,
  enxp_pn_code varchar(50)  default NULL,
  enxp_pn_codesystem varchar(50)  default NULL,
  enxp_pn_codesystemversion varchar(50)  default NULL,
  enxp_on_value varchar(50)  default NULL,
  enxp_on_code varchar(50)  default NULL,
  enxp_on_codesystem varchar(50)  default NULL,
  enxp_on_codesystemversion varchar(50)  default NULL,
  PRIMARY KEY  (dset_en_datatype_id),
  CONSTRAINT fk_en_datatype_val6_en_datat FOREIGN KEY (dset_en_datatype_id) REFERENCES dset_en (id) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE dset_en_value7 (
  dset_en_datatype_id int(8) NOT NULL,
  enxp_pn_code varchar(50)  default NULL,
  enxp_on_code varchar(50)  default NULL,
  enxp_pn_value varchar(50)  default NULL,
  enxp_on_value varchar(50)  default NULL,
  enxp_on_codesystem varchar(50)  default NULL,
  enxp_pn_codesystem varchar(50)  default NULL,
  enxp_on_codesystemversion varchar(50)  default NULL,
  enxp_pn_codesystemversion varchar(50)  default NULL,
  PRIMARY KEY  (dset_en_datatype_id),
  CONSTRAINT fk_en_datatype_val7_en_datat FOREIGN KEY (dset_en_datatype_id) REFERENCES dset_en (id) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE dset_ii (
  id int(8) NOT NULL,
  PRIMARY KEY  (id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


CREATE TABLE dset_ii_value1 (
  dset_ii_id int(8) NOT NULL,
  extension varchar(50)  default NULL,
  PRIMARY KEY  (dset_ii_id),
  CONSTRAINT fk_dset_ii_value1_dsetii FOREIGN KEY (dset_ii_id) REFERENCES dset_ii (id) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE dset_ii_value2 (
  dset_ii_id int(8) NOT NULL,
  extension varchar(50)  default NULL,
  root varchar(50)  default NULL,
  null_flavor varchar(50)  default NULL,
  PRIMARY KEY  (dset_ii_id),
  CONSTRAINT fk_dset_ii_value2_dsetii FOREIGN KEY (dset_ii_id) REFERENCES dset_ii (id) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE dset_ii_value3 (
  dset_ii_id int(8) NOT NULL,
  extension varchar(50)  default NULL,
  identifier_name varchar(50)  default NULL,
  displayable_value varchar(1)  default NULL,
  reliability varchar(50)  default NULL,
  scope varchar(50)  default NULL,
  PRIMARY KEY  (dset_ii_id),
  CONSTRAINT fk_dset_ii_value3_dsetii FOREIGN KEY (dset_ii_id) REFERENCES dset_ii (id) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE dset_ii_value4 (
  dset_ii_id int(8) NOT NULL,
  root varchar(50)  default NULL,
  extension varchar(50)  default NULL,
  identifier_name varchar(50)  default NULL,
  reliability varchar(50)  default NULL,
  scope varchar(50)  default NULL,
  displayable_value varchar(1)  default NULL,
  null_flavor varchar(50)  default NULL,
  PRIMARY KEY  (dset_ii_id),
  CONSTRAINT fk_dset_ii_value4_dsetii FOREIGN KEY (dset_ii_id) REFERENCES dset_ii (id) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE dset_ii_value5 (
  id int(8) NOT NULL,
  dset_ii_id int(8) default NULL,
  extension varchar(50)  default NULL,
  PRIMARY KEY  (id),
  KEY fk_dset_ii_value5_dset_ii (dset_ii_id),
  CONSTRAINT fk_dset_ii_value5_dset_ii FOREIGN KEY (dset_ii_id) REFERENCES dset_ii (id) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE dset_ii_value6 (
  id int(8) NOT NULL,
  extension varchar(50)  default NULL,
  PRIMARY KEY  (id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE dset_ii_ii_value6 (
  dset_ii_id int(8) NOT NULL,
  dset_ii_value6_id int(8) NOT NULL,
  PRIMARY KEY  (dset_ii_value6_id,dset_ii_id),
  KEY fk_dset_ii_ii_value6_dset_ii (dset_ii_id),
  CONSTRAINT fk_dset_ii_ii_value6_dset_ii FOREIGN KEY (dset_ii_id) REFERENCES dset_ii (id) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT fk_dset_ii_ii_va_dset_ii_val FOREIGN KEY (dset_ii_value6_id) REFERENCES dset_ii_value6 (id) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


CREATE TABLE dset_tel (
  id int(8) NOT NULL,
  null_flavor varchar(50)  default NULL,
  PRIMARY KEY  (id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE dset_tel_email (
  id int(8) NOT NULL,
  null_flavor varchar(50)  default NULL,
  PRIMARY KEY  (id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE dset_tel_email_value1 (
  dset_tel_email_id int(8) NOT NULL,
  tel_email_value varchar(50)  default NULL,
  PRIMARY KEY  (dset_tel_email_id),
  CONSTRAINT fk_dset_tel_emai_dset_tel_ema FOREIGN KEY (dset_tel_email_id) REFERENCES dset_tel_email (id) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE dset_tel_email_value2 (
  dset_tel_email_id int(8) default NULL,
  tel_email_value varchar(50)  default NULL,
  tel_email_null_flavor varchar(50)  default NULL,
  KEY fk_tel_email_val_dset_tel_ema (dset_tel_email_id),
  CONSTRAINT fk_tel_email_val_dset_tel_ema FOREIGN KEY (dset_tel_email_id) REFERENCES dset_tel_email (id) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE dset_tel_person (
  id int(8) NOT NULL,
  null_flavor varchar(50)  default NULL,
  PRIMARY KEY  (id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE dset_tel_person_value1 (
  dset_tel_person_id int(8) NOT NULL,
  tel_person_value varchar(50)  default NULL,
  PRIMARY KEY  (dset_tel_person_id),
  CONSTRAINT fk_dset_tel_pers_dset_tel_per FOREIGN KEY (dset_tel_person_id) REFERENCES dset_tel_person (id) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE dset_tel_phone (
  id int(8) NOT NULL,
  null_flavor varchar(50)  default NULL,
  PRIMARY KEY  (id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE dset_tel_phone_value1 (
  dset_tel_phone_id int(8) NOT NULL,
  tel_phone_value varchar(50)  default NULL,
  PRIMARY KEY  (dset_tel_phone_id),
  CONSTRAINT fk_dset_tel_phon_dset_tel_pho FOREIGN KEY (dset_tel_phone_id) REFERENCES dset_tel_phone (id) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE dset_tel_url (
  id int(8) NOT NULL,
  null_flavor varchar(50)  default NULL,
  PRIMARY KEY  (id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE dset_tel_url_value1 (
  dset_tel_url_id int(8) NOT NULL,
  tel_url_value varchar(50)  default NULL,
  PRIMARY KEY  (dset_tel_url_id),
  CONSTRAINT fk_dset_tel_url_v_dset_tel_url FOREIGN KEY (dset_tel_url_id) REFERENCES dset_tel_url (id) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE dset_tel_value1 (
  dset_tel_id int(8) NOT NULL,
  tel_value varchar(50)  default NULL,
  PRIMARY KEY  (dset_tel_id),
  CONSTRAINT fk_dset_tel_value1_dset_tel FOREIGN KEY (dset_tel_id) REFERENCES dset_tel (id) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE dset_tel_value3 (
  id int(8) NOT NULL,
  tel_value varchar(50)  default NULL,
  PRIMARY KEY  (id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE dset_tel_tel_value_3 (
  dset_tel_id int(8) NOT NULL,
  dset_tel_value3_id int(8) NOT NULL,
  PRIMARY KEY  (dset_tel_id,dset_tel_value3_id),
  KEY fk_dset_tel_tel__dset_tel_val (dset_tel_value3_id),
  CONSTRAINT fk_dset_tel_tel_valu_dset_tel FOREIGN KEY (dset_tel_id) REFERENCES dset_tel (id) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT fk_dset_tel_tel__dset_tel_val FOREIGN KEY (dset_tel_value3_id) REFERENCES dset_tel_value3 (id) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


CREATE TABLE dset_tel_value2 (
  id int(8) NOT NULL,
  dset_tel_id int(8) default NULL,
  tel_value varchar(50)  default NULL,
  PRIMARY KEY  (id),
  KEY fk_dset_tel_value2_dset_tel (dset_tel_id),
  CONSTRAINT fk_dset_tel_value2_dset_tel FOREIGN KEY (dset_tel_id) REFERENCES dset_tel (id) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



CREATE TABLE ed_datatype (
  id int(8) NOT NULL,
  value1_data longblob,
  value2_null_flavor varchar(50)  default NULL,
  value2_data longblob,
  value2_compression varchar(50)  default NULL,
  value3_null_flavor varchar(50)  default NULL,
  value3_data longblob,
  value3_compression varchar(50)  default NULL,
  value3_description varchar(50)  default NULL,
  value3_value varchar(50)  default NULL,
  PRIMARY KEY  (id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE ed_text_datatype (
  id int(8) NOT NULL,
  value1_value varchar(50)  default NULL,
  value2_null_flavor varchar(50)  default NULL,
  value2_value varchar(50)  default NULL,
  value3_null_flavor varchar(50)  default NULL,
  value3_data longblob,
  value3_compression varchar(50)  default NULL,
  value3_description varchar(50)  default NULL,
  value3_value varchar(50)  default NULL,
  PRIMARY KEY  (id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE en_datatype (
  id int(8) NOT NULL,
  value1_pn_value varchar(50)  default NULL,
  value2_pn_value varchar(50)  default NULL,
  value2_pn_code varchar(50)  default NULL,
  value3_pn_value varchar(50)  default NULL,
  value3_pn_code varchar(50)  default NULL,
  value3_pn_code_system varchar(50)  default NULL,
  value3_pn_code_system_version varchar(50)  default NULL,
  value4_pn_value varchar(50)  default NULL,
  value4_pn_enpq varchar(50)  default NULL,
  value5_pn_value varchar(50)  default NULL,
  value5_pn2_value varchar(50)  default NULL,
  value5_pn_code varchar(50)  default NULL,
  value5_pn2_code varchar(50)  default NULL,
  value5_pn_code_system varchar(50)  default NULL,
  value5_pn2_code_system varchar(50)  default NULL,
  value5_pn_code_system_version varchar(50)  default NULL,
  value5_pn2_code_system_version varchar(50)  default NULL,
  value6_pn_value varchar(50)  default NULL,
  value6_on_value varchar(50)  default NULL,
  value6_pn_code varchar(50)  default NULL,
  value6_on_code varchar(50)  default NULL,
  value6_pn_code_system varchar(50)  default NULL,
  value6_on_code_system varchar(50)  default NULL,
  value6_pn_code_system_version varchar(50)  default NULL,
  value6_on_code_system_version varchar(50)  default NULL,
  PRIMARY KEY  (id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE en_on_datatype (
  id int(8) NOT NULL,
  value1_on_value varchar(50)  default NULL,
  PRIMARY KEY  (id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE en_pn_datatype (
  id int(8) NOT NULL,
  value1_pn_value varchar(50)  default NULL,
  PRIMARY KEY  (id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE ii_datatype (
  id int(8) NOT NULL,
  value1_extension varchar(50)  default NULL,
  value2_null_flavor varchar(50)  default NULL,
  value2_root varchar(50)  default NULL,
  value2_extension varchar(50)  default NULL,
  value3_null_flavor varchar(50)  default NULL,
  value3_extension varchar(50)  default NULL,
  value3_identifier_name varchar(50)  default NULL,
  value3_reliability varchar(50)  default NULL,
  value3_scope varchar(50)  default NULL,
  value3_displayable varchar(1)  default NULL,
  value4_null_flavor varchar(50)  default NULL,
  value4_root varchar(50)  default NULL,
  value4_extension varchar(50)  default NULL,
  value4_identifier_name varchar(50)  default NULL,
  value4_reliability varchar(50)  default NULL,
  value4_scope varchar(50)  default NULL,
  value4_displayable varchar(1)  default NULL,
  PRIMARY KEY  (id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE int_datatype (
  id int(8) NOT NULL,
  value1_value decimal(22,0) default NULL,
  value2_null_flavor varchar(50)  default NULL,
  value2_value decimal(22,0) default NULL,
  PRIMARY KEY  (id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE ivl_int (
  id int(8) NOT NULL,
  value1_low_value decimal(22,0) default NULL,
  value1_high_value decimal(22,0) default NULL,
  value2_high_value decimal(22,0) default NULL,
  value2_lowclosed varchar(1)  default NULL,
  value3_any_value decimal(22,0) default NULL,
  value3_low_value decimal(22,0) default NULL,
  value3_high_closed varchar(1)  default NULL,
  value4_low_value decimal(22,0) default NULL,
  value4_high_value decimal(22,0) default NULL,
  value4_width_value int(8) default NULL,
  value4_null_flavor varchar(50)  default NULL,
  PRIMARY KEY  (id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE ivl_pq (
  id int(8) NOT NULL,
  value1_low_value decimal(8,2) default NULL,
  value2_low_value decimal(8,2) default NULL,
  value2_low_precision decimal(22,0) default NULL,
  value2_low_unit varchar(50)  default NULL,
  value3_low_value decimal(8,2) default NULL,
  value3_low_precision decimal(22,0) default NULL,
  value3_low_unit varchar(50)  default NULL,
  value3_null_flavor varchar(50)  default NULL,
  value4_high_value decimal(8,2) default NULL,
  value4_high_precision decimal(22,0) default NULL,
  value4_high_unit varchar(50)  default NULL,
  value4_high_closed varchar(1)  default NULL,
  value4_high_null_flavor varchar(50)  default NULL,
  value4_low_value decimal(8,2) default NULL,
  value4_low_precision decimal(22,0) default NULL,
  value4_low_unit varchar(50)  default NULL,
  value4_low_null_flavor varchar(50)  default NULL,
  value4_low_closed varchar(1)  default NULL,
  value4_width_value decimal(8,2) default NULL,
  value4_width_precision decimal(22,0) default NULL,
  value4_width_unit varchar(50)  default NULL,
  value4_width_null_flavor varchar(50)  default NULL,
  value4_null_flavor varchar(50)  default NULL,
  PRIMARY KEY  (id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE ivl_pqv (
  id int(8) NOT NULL,
  value1_low_value decimal(8,2) default NULL,
  value1_high_value decimal(8,2) default NULL,
  value2_low_value decimal(8,2) default NULL,
  value2_low_precision decimal(22,0) default NULL,
  value2_high_closed varchar(1)  default NULL,
  value3_low_value decimal(8,2) default NULL,
  value3_low_precision decimal(22,0) default NULL,
  value3_high_value decimal(8,2) default NULL,
  value3_high_precision decimal(22,0) default NULL,
  value3_high_null_flavor varchar(50)  default NULL,
  value4_high_value decimal(8,2) default NULL,
  value4_high_precision decimal(22,0) default NULL,
  value4_high_null_flavor varchar(50)  default NULL,
  value4_low_value decimal(8,2) default NULL,
  value4_low_precision decimal(22,0) default NULL,
  value4_low_null_flavor varchar(50)  default NULL,
  value4_width_value decimal(8,2) default NULL,
  value4_width_precision decimal(22,0) default NULL,
  value4_width_null_flavor varchar(50)  default NULL,
  PRIMARY KEY  (id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE ivl_real (
  id int(8) default NULL,
  value1_low_value double default NULL,
  value1_high_value double default NULL,
  value2_high_value double default NULL,
  value2_low_closed varchar(1)  default NULL,
  value3_any_value double default NULL,
  value3_high_value double default NULL,
  value3_high_closed varchar(1)  default NULL,
  value3_low_value double default NULL,
  value3_low_closed varchar(1)  default NULL,
  value3_width_value double default NULL,
  value3_width_null_flavor varchar(50)  default NULL,
  value3_null_flavor varchar(50)  default NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE ivl_ts (
  id int(8) default NULL,
  value1_low_value datetime default NULL,
  value1_high_value datetime default NULL,
  value2_high_value datetime default NULL,
  value2_low_closed varchar(1)  default NULL,
  value3_high_value datetime default NULL,
  value3_low_value datetime default NULL,
  value3_width_null_flavor varchar(50)  default NULL,
  value3_width_value int(8) default NULL,
  value3_null_flavor varchar(50)  default NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE pqv_datatype (
  id int(8) NOT NULL,
  value1_value decimal(8,2) default NULL,
  value2_null_flavor varchar(50)  default NULL,
  value2_value decimal(8,2) default NULL,
  value2_precision decimal(22,0) default NULL,
  value3_value decimal(8,2) default NULL,
  value3_precision decimal(22,0) default NULL,
  value4_null_flavor varchar(50)  default NULL,
  value4_precision decimal(22,0) default NULL,
  value4_value decimal(8,2) default NULL,
  PRIMARY KEY  (id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE pq_datatype (
  id int(8) NOT NULL,
  value1_value decimal(8,2) default NULL,
  value1_unit varchar(50)  default NULL,
  value2_value decimal(8,2) default NULL,
  value3_null_flavor varchar(50)  default NULL,
  value3_unit varchar(50)  default NULL,
  value3_value decimal(8,2) default NULL,
  value3_precision decimal(22,0) default NULL,
  PRIMARY KEY  (id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE real_datatype (
  id int(8) NOT NULL,
  value1_value double default NULL,
  value2_null_flavor varchar(50)  default NULL,
  value2_value double default NULL,
  PRIMARY KEY  (id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE sc_datatype (
  id int(8) NOT NULL,
  value1_value varchar(50)  default NULL,
  value1_code_code varchar(50)  default NULL,
  value2_null_flavor varchar(50)  default NULL,
  value2_value varchar(50)  default NULL,
  value2_code_null_flavor varchar(50)  default NULL,
  value2_code_code varchar(50)  default NULL,
  value2_code_code_system varchar(50)  default NULL,
  value2_code_code_system_name varchar(50)  default NULL,
  value2_code_code_system_ver varchar(50)  default NULL,
  value3_null_flavor varchar(50)  default NULL,
  value3_value varchar(50)  default NULL,
  value3_code_null_flavor varchar(50)  default NULL,
  value3_code_code varchar(50)  default NULL,
  value3_code_code_system varchar(50)  default NULL,
  value3_code_code_system_name varchar(50)  default NULL,
  value3_code_code_system_ver varchar(50)  default NULL,
  value3_code_display_nflavor varchar(50)  default NULL,
  value3_code_display_value varchar(50)  default NULL,
  value3_code_orig_txt_nflavor varchar(50)  default NULL,
  value3_code_orig_txt_desc varchar(50)  default NULL,
  value3_code_orig_txt_value varchar(50)  default NULL,
  PRIMARY KEY  (id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE st_datatype (
  id int(8) NOT NULL,
  value1_value varchar(50)  default NULL,
  value2_null_flavor varchar(50)  default NULL,
  value2_value varchar(50)  default NULL,
  PRIMARY KEY  (id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE st_nt_datatype (
  id int(8) NOT NULL,
  value1_value varchar(50)  default NULL,
  value2_null_flavor varchar(50)  default NULL,
  value2_value varchar(50)  default NULL,
  PRIMARY KEY  (id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE tel_datatype (
  id int(8) NOT NULL,
  value1_value varchar(50)  default NULL,
  value2_null_flavor varchar(50)  default NULL,
  value2_value varchar(50)  default NULL,
  PRIMARY KEY  (id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE tel_email_datatype (
  id int(8) NOT NULL,
  value1_value varchar(50)  default NULL,
  value2_null_flavor varchar(50)  default NULL,
  value2_value varchar(50)  default NULL,
  PRIMARY KEY  (id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE tel_person_datatype (
  id int(8) NOT NULL,
  value1_value varchar(50)  default NULL,
  value2_null_flavor varchar(50)  default NULL,
  value2_value varchar(50)  default NULL,
  PRIMARY KEY  (id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE tel_phone_datatype (
  id int(8) NOT NULL,
  value1_value varchar(50)  default NULL,
  value2_null_flavor varchar(50)  default NULL,
  value2_value varchar(50)  default NULL,
  PRIMARY KEY  (id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE tel_url_datatype (
  id int(8) NOT NULL,
  value1_value varchar(50)  default NULL,
  value2_null_flavor varchar(50)  default NULL,
  value2_value varchar(50)  default NULL,
  PRIMARY KEY  (id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE ts_datatype (
  id int(8) NOT NULL,
  value1_value datetime default NULL,
  value2_null_flavor varchar(50)  default NULL,
  value2_value datetime default NULL,
  PRIMARY KEY  (id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



-- ----------------------------------------------------------------------
-- SQL data bulk transfer script generated by the MySQL Migration Toolkit
-- ----------------------------------------------------------------------

-- Disable foreign key checks
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;

INSERT INTO ad_ad_datatype_value9(ad_datatype_id, ad_datatype_value9_id)
VALUES (44, 2),
  (45, 2),
  (46, 2),
  (47, 2),
  (48, 2);

INSERT INTO ad_datatype(id, value1_al_value, value2_dal_value, value2_dal_code, value3_al_value, value3_al_code, value3_al_codesystem, value4_al1_value, value4_al2_value, value4_al1_code, value4_al2_code, value4_al1_codesystem, value4_al2_codesystem, value5_al_value, value5_al_code, value5_al_codesystem, value5_dal_code, value5_dal_value, value5_dal_codesystem, value5_cty_value, value5_cty_code, value5_cty_codesystem, value6_adl_value, value6_al_value, value6_bnn_value, value6_bnr_value, value6_bns_value, value6_car_value, value6_cen_value, value6_cnt_value, value6_cpa_value, value6_cty_value, value6_dal_value, value6_del_value, value6_dinsta_value, value6_dinstq_value, value6_dir_value, value6_dmod_value, value6_dmodid_value, value6_int_value, value6_pob_value, value6_pre_value, value6_sal_value, value6_sta_value, value6_stb_value, value6_str_value, value6_sttyp_value, value6_unid_value, value6_unit_value, value6_zip_value, value6_adl_code, value6_bns_code, value6_bns_codesystem, value6_dal_code, value6_dal_codesystem, value6_int_code, value6_int_codesystem, value6_stb_code, value6_stb_codesystem, value6_zip_codesystem, value6_zip_code, ad_datatype_value8_id)
VALUES (1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (2, '1 Jefferson Street', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (3, '2 E Jefferson Street', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (4, '3 Jefferson Street', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (5, '4 Sun Street', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (6, '5 Sun Street', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (7, NULL, '5th Floor', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (8, NULL, '6th Floor', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (9, NULL, '7th Floor', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (10, NULL, '8th Floor', 'NCI5', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (11, NULL, '9th Floor', 'NCI6', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (12, NULL, NULL, NULL, 'E Jefferson Street', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (13, NULL, NULL, NULL, 'E Jefferson Street', 'NCI', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (14, NULL, NULL, NULL, 'Jefferson Street', 'NCI1', 'codeSystem1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (15, NULL, NULL, NULL, 'F Jefferson Street', 'NCI2', 'codeSystem2', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (16, NULL, NULL, NULL, 'G Jefferson Street', 'NCI3', 'codeSystem3', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (17, NULL, NULL, NULL, NULL, NULL, NULL, 'E Jefferson Street', NULL, 'NCI1', NULL, 'codeSystem1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (18, NULL, NULL, NULL, NULL, NULL, NULL, 'F Jefferson Street', NULL, 'NCI2', NULL, 'codeSystem2', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (19, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'E Jefferson Street', NULL, 'NCI-DC1', NULL, 'codeSystem2', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (20, NULL, NULL, NULL, NULL, NULL, NULL, 'F Jefferson Street', 'F Jefferson Street', NULL, 'NCI-DC2', NULL, 'codeSystem2', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (21, NULL, NULL, NULL, NULL, NULL, NULL, 'G Jefferson Street', 'G Jefferson Street', 'NCI3', 'NCI-DC3', NULL, 'codeSystem2', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (22, NULL, NULL, NULL, NULL, NULL, NULL, 'H Jefferson Street', 'H Jefferson Street', 'NCI4', 'NCI-DC4', 'codeSystem4', 'codeSystem2', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (23, NULL, NULL, NULL, NULL, NULL, NULL, 'I Jefferson Street', 'I Jefferson Street', 'NCI5', 'NCI-DC5', 'codeSystem5', 'codeSystem2', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (24, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'Address line1', NULL, NULL, NULL, 'Delivery line1', NULL, 'Rockville', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (25, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'Address line1', 'AL_CODE1', NULL, 'DAL_CODE1', 'Delivery line1', NULL, 'Rockville', 'CITY_CODE1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (26, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'Address line1', NULL, 'AL_CODE_SYSTEM1', NULL, 'Delivery line1', 'DAL_CODE_SYSTEM1', 'Rockville', NULL, 'CITY_CODE_SYSTEM', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (27, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'Address line1', 'AL_CODE2', 'AL_CODE_SYSTEM1', 'DAL_CODE2', 'Delivery line1', 'DAL_CODE_SYSTEM2', 'Rockville', 'CITY_CODE2', 'CITY_CODE_SYSTEM', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (28, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'Address line1', 'AL_CODE3', 'AL_CODE_SYSTEM1', 'DAL_CODE3', 'Delivery line1', 'DAL_CODE_SYSTEM3', 'Rockville', 'CITY_CODE3', 'CITY_CODE_SYSTEM', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (29, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'VALUE6_ADL_VALUE1', 'VALUE6_AL_VALUE1', 'VALUE6_BNN_VALUE1', 'VALUE6_BNR_VALUE1', 'VALUE6_BNS_VALUE1', 'VALUE6_CAR_VALUE1', 'VALUE6_CEN_VALUE1', 'VALUE6_CNT_VALUE1', 'VALUE6_CPA_VALUE1', 'VALUE6_CTY_VALUE1', 'VALUE6_DAL_VALUE1', 'VALUE6_DEL_VALUE1', 'VALUE6_DINSTA_VALUE1', 'VALUE6_DINSTQ_VALUE1', 'VALUE6_DIR_VALUE1', 'VALUE6_DMOD_VALUE1', 'VALUE6_DMODID_VALUE1', 'VALUE6_INT_VALUE1', 'VALUE6_POB_VALUE1', 'VALUE6_PRE_VALUE1', 'VALUE6_SAL_VALUE1', 'VALUE6_STA_VALUE1', 'VALUE6_STB_VALUE1', 'VALUE6_STR_VALUE1', 'VALUE6_STTYP_VALUE1', 'VALUE6_UNID_VALUE1', 'VALUE6_UNIT_VALUE1', 'VALUE6_ZIP_VALUE1', 'VALUE6_ADL_CODE1', 'VALUE6_BNS_CODE1', 'VALUE6_BNS_CODESYSTEM1', 'VALUE6_DAL_CODE1', 'VALUE6_DAL_CODESYSTEM1', 'VALUE6_INT_CODE1', 'VALUE6_INT_CODESYSTEM1', 'VALUE6_STB_CODE1', 'VALUE6_STB_CODESYSTEM1', 'VALUE6_ZIP_CODESYSTEM1', 'VALUE6_ZIP_CODE1', NULL),
  (30, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'VALUE6_ADL_VALUE2', 'VALUE6_AL_VALUE2', 'VALUE6_BNN_VALUE2', 'VALUE6_BNR_VALUE2', 'VALUE6_BNS_VALUE2', 'VALUE6_CAR_VALUE2', 'VALUE6_CEN_VALUE2', 'VALUE6_CNT_VALUE2', 'VALUE6_CPA_VALUE2', 'VALUE6_CTY_VALUE2', 'VALUE6_DAL_VALUE2', 'VALUE6_DEL_VALUE2', 'VALUE6_DINSTA_VALUE2', 'VALUE6_DINSTQ_VALUE2', 'VALUE6_DIR_VALUE2', 'VALUE6_DMOD_VALUE2', 'VALUE6_DMODID_VALUE2', 'VALUE6_INT_VALUE2', 'VALUE6_POB_VALUE2', 'VALUE6_PRE_VALUE2', 'VALUE6_SAL_VALUE2', 'VALUE6_STA_VALUE2', 'VALUE6_STB_VALUE2', 'VALUE6_STR_VALUE2', 'VALUE6_STTYP_VALUE2', 'VALUE6_UNID_VALUE2', 'VALUE6_UNIT_VALUE2', 'VALUE6_ZIP_VALUE2', 'VALUE6_ADL_CODE2', 'VALUE6_BNS_CODE2', 'VALUE6_BNS_CODESYSTEM2', 'VALUE6_DAL_CODE2', 'VALUE6_DAL_CODESYSTEM2', 'VALUE6_INT_CODE2', 'VALUE6_INT_CODESYSTEM2', 'VALUE6_STB_CODE2', 'VALUE6_STB_CODESYSTEM2', 'VALUE6_ZIP_CODESYSTEM2', 'VALUE6_ZIP_CODE2', NULL),
  (31, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'VALUE6_ADL_VALUE3', 'VALUE6_AL_VALUE3', 'VALUE6_BNN_VALUE3', 'VALUE6_BNR_VALUE3', 'VALUE6_BNS_VALUE3', 'VALUE6_CAR_VALUE3', 'VALUE6_CEN_VALUE3', 'VALUE6_CNT_VALUE3', 'VALUE6_CPA_VALUE3', 'VALUE6_CTY_VALUE3', 'VALUE6_DAL_VALUE3', 'VALUE6_DEL_VALUE3', 'VALUE6_DINSTA_VALUE3', 'VALUE6_DINSTQ_VALUE3', 'VALUE6_DIR_VALUE3', 'VALUE6_DMOD_VALUE3', 'VALUE6_DMODID_VALUE3', 'VALUE6_INT_VALUE3', 'VALUE6_POB_VALUE3', 'VALUE6_PRE_VALUE3', 'VALUE6_SAL_VALUE3', 'VALUE6_STA_VALUE3', 'VALUE6_STB_VALUE3', 'VALUE6_STR_VALUE3', 'VALUE6_STTYP_VALUE3', 'VALUE6_UNID_VALUE3', 'VALUE6_UNIT_VALUE3', 'VALUE6_ZIP_VALUE3', 'VALUE6_ADL_CODE3', 'VALUE6_BNS_CODE3', 'VALUE6_BNS_CODESYSTEM3', 'VALUE6_DAL_CODE3', 'VALUE6_DAL_CODESYSTEM3', 'VALUE6_INT_CODE3', 'VALUE6_INT_CODESYSTEM3', 'VALUE6_STB_CODE3', 'VALUE6_STB_CODESYSTEM3', 'VALUE6_ZIP_CODESYSTEM3', 'VALUE6_ZIP_CODE3', NULL),
  (32, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'VALUE6_ADL_VALUE4', 'VALUE6_AL_VALUE4', 'VALUE6_BNN_VALUE4', 'VALUE6_BNR_VALUE4', 'VALUE6_BNS_VALUE4', 'VALUE6_CAR_VALUE4', 'VALUE6_CEN_VALUE4', 'VALUE6_CNT_VALUE4', 'VALUE6_CPA_VALUE4', 'VALUE6_CTY_VALUE4', 'VALUE6_DAL_VALUE4', 'VALUE6_DEL_VALUE4', 'VALUE6_DINSTA_VALUE4', 'VALUE6_DINSTQ_VALUE4', 'VALUE6_DIR_VALUE4', 'VALUE6_DMOD_VALUE4', 'VALUE6_DMODID_VALUE4', 'VALUE6_INT_VALUE4', 'VALUE6_POB_VALUE4', 'VALUE6_PRE_VALUE4', 'VALUE6_SAL_VALUE4', 'VALUE6_STA_VALUE4', 'VALUE6_STB_VALUE4', 'VALUE6_STR_VALUE4', 'VALUE6_STTYP_VALUE4', 'VALUE6_UNID_VALUE4', 'VALUE6_UNIT_VALUE4', 'VALUE6_ZIP_VALUE4', 'VALUE6_ADL_CODE4', 'VALUE6_BNS_CODE4', 'VALUE6_BNS_CODESYSTEM4', 'VALUE6_DAL_CODE4', 'VALUE6_DAL_CODESYSTEM4', 'VALUE6_INT_CODE4', 'VALUE6_INT_CODESYSTEM4', 'VALUE6_STB_CODE4', 'VALUE6_STB_CODESYSTEM4', 'VALUE6_ZIP_CODESYSTEM4', 'VALUE6_ZIP_CODE4', NULL),
  (33, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'VALUE6_ADL_VALUE5', 'VALUE6_AL_VALUE5', 'VALUE6_BNN_VALUE5', 'VALUE6_BNR_VALUE5', 'VALUE6_BNS_VALUE5', 'VALUE6_CAR_VALUE5', 'VALUE6_CEN_VALUE5', 'VALUE6_CNT_VALUE5', 'VALUE6_CPA_VALUE5', 'VALUE6_CTY_VALUE5', 'VALUE6_DAL_VALUE5', 'VALUE6_DEL_VALUE5', 'VALUE6_DINSTA_VALUE5', 'VALUE6_DINSTQ_VALUE5', 'VALUE6_DIR_VALUE5', 'VALUE6_DMOD_VALUE5', 'VALUE6_DMODID_VALUE5', 'VALUE6_INT_VALUE5', 'VALUE6_POB_VALUE5', 'VALUE6_PRE_VALUE5', 'VALUE6_SAL_VALUE5', 'VALUE6_STA_VALUE5', 'VALUE6_STB_VALUE5', 'VALUE6_STR_VALUE5', 'VALUE6_STTYP_VALUE5', 'VALUE6_UNID_VALUE5', 'VALUE6_UNIT_VALUE5', 'VALUE6_ZIP_VALUE5', 'VALUE6_ADL_CODE5', 'VALUE6_BNS_CODE5', 'VALUE6_BNS_CODESYSTEM5', 'VALUE6_DAL_CODE5', 'VALUE6_DAL_CODESYSTEM5', 'VALUE6_INT_CODE5', 'VALUE6_INT_CODESYSTEM5', 'VALUE6_STB_CODE5', 'VALUE6_STB_CODESYSTEM5', 'VALUE6_ZIP_CODESYSTEM5', 'VALUE6_ZIP_CODE5', NULL),
  (34, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (35, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (36, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (37, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (38, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (39, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1),
  (40, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 2),
  (41, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 3),
  (42, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 4),
  (43, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 5),
  (44, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (45, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (46, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (47, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (48, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);

INSERT INTO ad_datatype_value7(ad_datatype_id, al_value, al_code, al_codesystem, dal_code, dal_value, dal_codesystem, cty_value, cty_code, cty_codesystem)
VALUES (34, 'AL_VALUE1', 'AL_CODE1', 'AL_CODESYSTEM1', NULL, NULL, NULL, NULL, NULL, NULL),
  (35, NULL, NULL, NULL, 'DAL_CODE1', 'DAL_VALUE1', 'DAL_CODESYSTEM1', 'CTY_VALUE1', 'CTY_CODE1', 'CTY_CODESYSTEM1'),
  (36, 'AL_VALUE2', 'AL_CODE2', 'AL_CODESYSTEM2', 'DAL_CODE2', 'DAL_VALUE2', 'DAL_CODESYSTEM2', NULL, NULL, NULL),
  (37, 'AL_VALUE3', 'AL_CODE3', 'AL_CODESYSTEM3', 'DAL_CODE3', 'DAL_VALUE3', 'DAL_CODESYSTEM3', 'CTY_VALUE3', 'CTY_CODE3', 'CTY_CODESYSTEM3'),
  (38, 'AL_VALUE4', 'AL_CODE4', 'AL_CODESYSTEM4', 'DAL_CODE4', 'DAL_VALUE4', 'DAL_CODESYSTEM4', 'CTY_VALUE4', 'CTY_CODE4', 'CTY_CODESYSTEM4');

INSERT INTO ad_datatype_value8(id, al_value, al_code, al_codesystem, dal_code, dal_value, dal_codesystem, cty_value, cty_code, cty_codesystem)
VALUES (1, 'AL_VALUE1', 'AL_CODE1', 'AL_CODESYSTEM1', NULL, NULL, NULL, NULL, NULL, NULL),
  (2, NULL, NULL, NULL, 'DAL_CODE1', 'DAL_VALUE1', 'DAL_CODESYSTEM1', 'CTY_VALUE1', 'CTY_CODE1', 'CTY_CODESYSTEM1'),
  (3, 'AL_VALUE2', 'AL_CODE2', 'AL_CODESYSTEM2', NULL, NULL, NULL, 'CTY_VALUE2', 'CTY_CODE2', 'CTY_CODESYSTEM2'),
  (4, 'AL_VALUE3', 'AL_CODE3', 'AL_CODESYSTEM3', 'DAL_CODE3', 'DAL_VALUE3', 'DAL_CODESYSTEM3', NULL, NULL, NULL),
  (5, 'AL_VALUE4', 'AL_CODE4', 'AL_CODESYSTEM4', 'DAL_CODE4', 'DAL_VALUE4', 'DAL_CODESYSTEM4', 'CTY_VALUE4', 'CTY_CODE4', 'CTY_CODESYSTEM4');

INSERT INTO ad_datatype_value9(id, al_value, al_code, al_codesystem, dal_code, dal_value, dal_codesystem, cty_value, cty_code, cty_codesystem)
VALUES (1, 'AL_VALUE1', 'AL_CODE1', 'AL_CODESYSTEM1', NULL, NULL, NULL, NULL, NULL, NULL),
  (2, 'AL_VALUE4', 'AL_CODE4', 'AL_CODESYSTEM4', 'DAL_CODE4', 'DAL_VALUE4', 'DAL_CODESYSTEM4', 'CTY_VALUE4', 'CTY_CODE4', 'CTY_CODESYSTEM4');

INSERT INTO bl_datatype(id, value1_value, value2_null_flavor, value2_value)
VALUES (1, NULL, NULL, NULL),
  (2, '1', NULL, NULL),
  (3, NULL, NULL, '1'),
  (4, NULL, 'INV', '0');

INSERT INTO bl_nonnull_datatype(id, value1_value)
VALUES (1, NULL),
  (2, '1');

INSERT INTO cd_cd_datatype_value8(cd_data_type_id, cd_datatype_value8_id)
VALUES (40, 1),
  (41, 1),
  (42, 1),
  (43, 1),
  (44, 1);

INSERT INTO cd_datatype(id, value1_code, value2_null_flavor, value2_code, value3_code, value4_null_flavor, value4_code, value4_code_system, value4_code_system_version, value4_code_system_name, value4_display_null_flavor, value4_display_value, value4_orig_txt_null_flavor, value4_orig_txt_value, value4_orig_txt_description, value5_null_flavor, value5_code, value5_code_system, value5_code_system_name, value5_code_system_version, value5_display_null_flavor, value5_display_value, value5_orig_txt_null_flavor, value5_orig_txt_value, value5_orig_txt_description, cd_datatype_value7_id)
VALUES (1, 'CODE1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (2, 'CODE2', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (3, 'CODE3', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (4, 'CODE4', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (5, 'CODE5', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (6, NULL, NULL, 'CODE1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (7, NULL, NULL, 'CODE2', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (8, NULL, NULL, 'CODE3', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (9, NULL, 'NI', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (10, NULL, 'NI', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (11, NULL, NULL, NULL, 'CODE1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (12, NULL, NULL, NULL, 'CODE2', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (13, NULL, NULL, NULL, 'CODE3', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (14, NULL, NULL, NULL, 'CODE4', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (15, NULL, NULL, NULL, 'CODE5', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (16, NULL, NULL, NULL, NULL, 'NI', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (17, NULL, NULL, NULL, NULL, NULL, 'CODE2', NULL, NULL, NULL, NULL, 'VALUE4_DISPLAY_VALUE1', 'NI', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (18, NULL, NULL, NULL, NULL, NULL, 'CODE3', NULL, NULL, NULL, NULL, 'VALUE4_DISPLAY_VALUE2', NULL, NULL, 'VALUE4_ORIG_TXT_DESCRIPTION1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (19, NULL, NULL, NULL, NULL, NULL, 'CODE4', NULL, NULL, NULL, NULL, 'VALUE4_DISPLAY_VALUE2', NULL, 'VALUE4_ORIG_TXT_VALUE1', 'VALUE4_ORIG_TXT_DESCRIPTION2', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (20, NULL, NULL, NULL, NULL, NULL, 'CODE5', 'VALUE4_CODE_SYSTEM', NULL, NULL, NULL, 'VALUE4_DISPLAY_VALUE3', NULL, 'VALUE4_ORIG_TXT_VALUE2', 'VALUE4_ORIG_TXT_DESCRIPTION3', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (21, NULL, NULL, NULL, NULL, NULL, 'CODE6', 'VALUE4_CODE_SYSTEM', NULL, NULL, NULL, 'VALUE4_DISPLAY_VALUE4', NULL, 'VALUE4_ORIG_TXT_VALUE3', 'VALUE4_ORIG_TXT_DESCRIPTION4', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (22, NULL, NULL, NULL, NULL, NULL, 'CODE7', 'VALUE4_CODE_SYSTEM', 'VALUE4_CODE_SYSTEM_VERSION1', NULL, NULL, 'VALUE4_DISPLAY_VALUE4', NULL, 'VALUE4_ORIG_TXT_VALUE4', 'VALUE4_ORIG_TXT_DESCRIPTION5', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (23, NULL, NULL, NULL, NULL, NULL, 'CODE8', 'VALUE4_CODE_SYSTEM', 'VALUE4_CODE_SYSTEM_VERSION1', 'VALUE4_CODE_SYSTEM_NAME', NULL, 'VALUE4_DISPLAY_VALUE4', NULL, 'VALUE4_ORIG_TXT_VALUE5', 'VALUE4_ORIG_TXT_DESCRIPTION6', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (24, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'CODE1', 'VALUE5_CODE_SYSTEM1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (25, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'CODE2', 'VALUE5_CODE_SYSTEM2', NULL, NULL, NULL, 'VALUE5_DISPLAY_VALUE1', NULL, NULL, NULL, NULL),
  (26, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'CODE3', NULL, 'VALUE5_CODE_SYSTEM_NAME1', NULL, NULL, 'VALUE5_DISPLAY_VALUE2', NULL, NULL, 'VALUE5_ORIG_TXT_DESCRIPTION1', NULL),
  (27, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'CODE4', 'VALUE5_CODE_SYSTEM3', NULL, NULL, NULL, 'VALUE5_DISPLAY_VALUE2', NULL, 'VALUE5_ORIG_TXT_VALUE1', 'VALUE5_ORIG_TXT_DESCRIPTION2', NULL),
  (28, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'CODE5', 'VALUE5_CODE_SYSTEM', 'VALUE5_CODE_SYSTEM_NAME2', NULL, NULL, 'VALUE5_DISPLAY_VALUE3', NULL, 'VALUE5_ORIG_TXT_VALUE2', 'VALUE5_ORIG_TXT_DESCRIPTION3', NULL),
  (29, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'CODE6', 'VALUE5_CODE_SYSTEM', NULL, NULL, NULL, 'VALUE5_DISPLAY_VALUE5', NULL, 'VALUE5_ORIG_TXT_VALUE3', 'VALUE5_ORIG_TXT_DESCRIPTION4', NULL),
  (30, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'CODE7', 'VALUE5_CODE_SYSTEM', NULL, 'VALUE5_CODE_SYSTEM_VERSION1', NULL, 'VALUE5_DISPLAY_VALUE5', NULL, 'VALUE5_ORIG_TXT_VALUE5', 'VALUE5_ORIG_TXT_DESCRIPTION5', NULL),
  (31, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'CODE8', 'VALUE5_CODE_SYSTEM', 'VALUE5_CODE_SYSTEM_NAME', 'VALUE5_CODE_SYSTEM_VERSION1', NULL, 'VALUE5_DISPLAY_VALUE5', NULL, 'VALUE5_ORIG_TXT_VALUE5', 'VALUE5_ORIG_TXT_DESCRIPTION6', NULL),
  (32, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'CODE1', 'VALUE5_CODE_SYSTEM1', NULL, NULL, 'NI', NULL, 'NI', NULL, NULL, 1),
  (33, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'CODE2', 'VALUE5_CODE_SYSTEM2', NULL, NULL, NULL, 'VALUE5_DISPLAY_VALUE1', 'NI', NULL, NULL, 2),
  (34, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'CODE3', NULL, 'VALUE5_CODE_SYSTEM_NAME1', NULL, NULL, 'VALUE5_DISPLAY_VALUE2', NULL, NULL, 'VALUE5_ORIG_TXT_DESCRIPTION1', 3),
  (35, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'CODE4', 'VALUE5_CODE_SYSTEM3', NULL, NULL, NULL, 'VALUE5_DISPLAY_VALUE2', NULL, 'VALUE5_ORIG_TXT_VALUE1', 'VALUE5_ORIG_TXT_DESCRIPTION2', 4),
  (36, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'CODE5', 'VALUE5_CODE_SYSTEM', 'VALUE5_CODE_SYSTEM_NAME2', NULL, NULL, 'VALUE5_DISPLAY_VALUE3', NULL, 'VALUE5_ORIG_TXT_VALUE2', 'VALUE5_ORIG_TXT_DESCRIPTION3', 5),
  (37, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'CODE6', 'VALUE5_CODE_SYSTEM', NULL, NULL, NULL, 'VALUE5_DISPLAY_VALUE5', NULL, 'VALUE5_ORIG_TXT_VALUE3', 'VALUE5_ORIG_TXT_DESCRIPTION4', 6),
  (38, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'CODE7', 'VALUE5_CODE_SYSTEM', NULL, 'VALUE5_CODE_SYSTEM_VERSION1', NULL, 'VALUE5_DISPLAY_VALUE5', NULL, 'VALUE5_ORIG_TXT_VALUE5', 'VALUE5_ORIG_TXT_DESCRIPTION5', 7),
  (39, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'CODE8', 'VALUE5_CODE_SYSTEM', 'VALUE5_CODE_SYSTEM_NAME', 'VALUE5_CODE_SYSTEM_VERSION1', NULL, 'VALUE5_DISPLAY_VALUE5', NULL, 'VALUE5_ORIG_TXT_VALUE5', 'VALUE5_ORIG_TXT_DESCRIPTION6', 8),
  (40, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (41, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (42, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (43, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (44, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (45, NULL, NULL, 'CODE61', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (46, NULL, NULL, 'CODE62', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (47, NULL, NULL, 'CODE63', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (48, NULL, NULL, 'CODE64', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (49, NULL, NULL, 'CODE65', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (50, NULL, NULL, 'CODE66', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);

INSERT INTO cd_datatype_value6(cd_datatype_id, null_flavor, code, code_system, code_system_name, code_system_version, display_null_flavor, display_value, orig_txt_null_flavor, orig_txt_value, orig_txt_description)
VALUES (45, NULL, 'CODE1', NULL, NULL, NULL, NULL, 'VALUE6_DISPLAY_VALUE1', 'NI', NULL, NULL),
  (46, NULL, 'CODE2', NULL, NULL, NULL, NULL, 'VALUE6_DISPLAY_VALUE2', NULL, 'ORIG_TXT_VALUE1', NULL),
  (47, NULL, 'CODE3', NULL, NULL, NULL, NULL, 'VALUE6_DISPLAY_VALUE3', NULL, 'ORIG_TXT_VALUE2', 'ORIG_TXT_DESCRIPTION1'),
  (48, NULL, 'CODE4', 'CODE_SYSTEM_61', NULL, NULL, NULL, 'VALUE6_DISPLAY_VALUE4', NULL, 'ORIG_TXT_VALUE3', 'ORIG_TXT_DESCRIPTION2'),
  (49, NULL, 'CODE5', 'CODE_SYSTEM_62', 'CODE_SYSTEM_NAME_61', NULL, NULL, 'VALUE6_DISPLAY_VALUE5', NULL, 'ORIG_TXT_VALUE4', 'ORIG_TXT_DESCRIPTION3'),
  (50, NULL, 'CODE1', 'CODE_SYSTEM_63', 'CODE_SYSTEM_NAME_62', 'CODE_SYSTEM_VERSION_61', NULL, 'VALUE6_DISPLAY_VALUE6', NULL, NULL, NULL);

INSERT INTO cd_datatype_value7(id, null_flavor, code, code_system, code_system_name, code_system_version, display_null_flavor, display_value, orig_txt_null_flavor, orig_txt_value, orig_txt_description)
VALUES (1, NULL, 'CODE1', NULL, NULL, NULL, NULL, 'VALUE7_DISPLAY_VALUE1', NULL, 'ORIG_TXT_VALUE1', NULL),
  (2, NULL, 'CODE2', NULL, NULL, NULL, NULL, 'VALUE7_DISPLAY_VALUE2', NULL, 'ORIG_TXT_VALUE2', 'ORIG_TXT_DESCRIPTION1'),
  (3, NULL, 'CODE3', 'CODE_SYSTEM_1', NULL, NULL, NULL, 'VALUE7_DISPLAY_VALUE3', NULL, 'ORIG_TXT_VALUE3', 'ORIG_TXT_DESCRIPTION2'),
  (4, NULL, 'CODE4', 'CODE_SYSTEM_2', 'CODE_SYSTEM_NAME_1', NULL, NULL, 'VALUE7_DISPLAY_VALUE4', NULL, 'ORIG_TXT_VALUE4', 'ORIG_TXT_DESCRIPTION3'),
  (5, NULL, 'CODE5', 'CODE_SYSTEM_3', 'CODE_SYSTEM_NAME_2', 'CODE_SYSTEM_VERSION_1', NULL, 'VALUE7_DISPLAY_VALUE5', NULL, 'ORIG_TXT_VALUE5', 'ORIG_TXT_DESCRIPTION4'),
  (6, NULL, 'CODE6', NULL, NULL, NULL, NULL, 'VALUE7_DISPLAY_VALUE6', NULL, NULL, 'ORIG_TXT_DESCRIPTION5'),
  (7, NULL, 'CODE7', NULL, NULL, NULL, 'NI', NULL, NULL, 'ORIG_TXT_VALUE7', 'ORIG_TXT_DESCRIPTION6'),
  (8, NULL, 'CODE8', 'CODE_SYSTEM_4', NULL, NULL, NULL, 'VALUE7_DISPLAY_VALUE8', NULL, 'ORIG_TXT_VALUE8', 'ORIG_TXT_DESCRIPTION7');

INSERT INTO cd_datatype_value8(id, null_flavor, code, code_system, code_system_name, code_system_version, display_null_flavor, display_value, orig_txt_null_flavor, orig_txt_value, orig_txt_description)
VALUES (1, NULL, 'CODE1', NULL, NULL, NULL, NULL, 'VALUE8_DISPLAY_VALUE1', 'NI', NULL, NULL);

INSERT INTO dset_ad_datatype(id)
VALUES (1),
  (2),
  (3),
  (4),
  (5),
  (6),
  (7),
  (8),
  (9),
  (10),
  (11),
  (12),
  (13),
  (14),
  (15),
  (16),
  (17),
  (18),
  (19),
  (20),
  (21),
  (22),
  (23),
  (24),
  (25),
  (26),
  (27),
  (28),
  (29),
  (30),
  (31),
  (32),
  (33),
  (34),
  (35),
  (36),
  (37),
  (38),
  (39),
  (40),
  (41),
  (42),
  (43),
  (44),
  (45),
  (46),
  (47),
  (48),
  (49),
  (50),
  (51),
  (52),
  (53),
  (54),
  (55),
  (56),
  (57),
  (58);

INSERT INTO dset_ad_dset_ad_value8(dset_ad_datatype_id, dset_ad_value8_id)
VALUES (48, 1),
  (48, 2),
  (48, 3),
  (48, 4),
  (48, 5),
  (48, 6),
  (48, 7),
  (48, 8),
  (48, 9),
  (48, 10);

INSERT INTO dset_ad_value1(dset_ad_datatype_id, adxp_al_value)
VALUES (1, NULL),
  (2, '1 Jefferson Street'),
  (3, '2 Jefferson Street'),
  (4, '3 Jefferson Street'),
  (5, '4 Sun Street'),
  (6, '5 Sun Street');

INSERT INTO dset_ad_value2(dset_ad_datatype_id, adxp_dal_value, adxp_dal_code)
VALUES (7, 'Suite 100', NULL),
  (8, 'Suite 101', NULL),
  (9, NULL, 'CODE1'),
  (10, 'Suite 103', 'CODE2'),
  (11, 'Suite 104', 'CODE3');

INSERT INTO dset_ad_value3(dset_ad_datatype_id, adxp_al_value, adxp_al_code, adxp_al_codesystem)
VALUES (12, '1 Jefferson Street', NULL, NULL),
  (13, '2 Jefferson Street', 'CODE1', NULL),
  (14, NULL, 'CODE2', NULL),
  (15, '3 Jefferson Street', 'CODE3', NULL),
  (16, '4 Jefferson Street', 'CODE4', 'CODE_SYSTEM');

INSERT INTO dset_ad_value4(dset_ad_datatype_id, adxp_al1_value, adxp_al2_value, adxp_al1_code, adxp_al2_code, adxp_al2_codesystem, adxp_al1_codesystem)
VALUES (17, '100 Jefferson Street', NULL, NULL, NULL, NULL, NULL),
  (18, '101 Jefferson Street', NULL, 'NCI101', NULL, NULL, NULL),
  (19, '102 Jefferson Street', NULL, 'NCI102', NULL, NULL, 'ADXP_AL1_CODESYSTEM1'),
  (20, NULL, '200 Executive Blvd', NULL, NULL, NULL, NULL),
  (21, NULL, '201 Executive Blvd', NULL, 'NCI201', NULL, NULL),
  (22, NULL, '202 Executive Blvd', NULL, 'NCI202', 'ADXP_AL2_CODESYSTEM1', NULL),
  (23, '100 Jefferson Street', '200 Executive Blvd', NULL, NULL, NULL, NULL),
  (24, '101 Jefferson Street', '201 Executive Blvd', 'NCI101', 'NCI201', NULL, NULL),
  (25, '102 Jefferson Street', '202 Executive Blvd', 'NCI102', 'NCI202', 'ADXP_AL2_CODESYSTEM1', 'ADXP_AL1_CODESYSTEM1');

INSERT INTO dset_ad_value5(dset_ad_datatype_id, adxp_al_value, adxp_al_code, adxp_al_codesystem, adxp_dal_code, adxp_dal_codesystem, adxp_dal_value, adxp_cty_value, adxp_cty_code, adxp_cty_codesystem)
VALUES (26, '100 Jefferson Street', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (27, '101 Jefferson Street', 'NCIAL101', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (28, '102 Jefferson Street', 'NCIAL102', 'ADXP_AL_CODESYSTEM1', NULL, NULL, NULL, NULL, NULL, NULL),
  (29, '103 Jefferson Street', 'NCIAL103', 'ADXP_AL_CODESYSTEM2', 'NCIDAL100', NULL, NULL, NULL, NULL, NULL),
  (30, '104 Jefferson Street', 'NCIAL104', 'ADXP_AL_CODESYSTEM3', 'NCIDAL101', 'ADXP_DAL_CODESYSTEM1', NULL, NULL, NULL, NULL),
  (31, '105 Jefferson Street', 'NCIAL105', 'ADXP_AL_CODESYSTEM4', 'NCIDAL102', 'ADXP_DAL_CODESYSTEM2', 'Suite 500', NULL, NULL, NULL),
  (32, '106 Jefferson Street', 'NCIAL106', 'ADXP_AL_CODESYSTEM5', 'NCIDAL103', 'ADXP_DAL_CODESYSTEM3', 'Suite 501', 'Rockville', NULL, NULL),
  (33, '107 Jefferson Street', 'NCIAL107', 'ADXP_AL_CODESYSTEM6', 'NCIDAL104', 'ADXP_DAL_CODESYSTEM4', 'Suite 502', 'Rockville', 'RCK', NULL),
  (34, '108 Jefferson Street', 'NCIAL108', 'ADXP_AL_CODESYSTEM7', 'NCIDAL105', 'ADXP_DAL_CODESYSTEM5', 'Suite 503', 'Rockville', 'RCK', 'RCK_CODE_SYS'),
  (35, '108 Jefferson Street', 'NCIAL108', 'ADXP_AL_CODESYSTEM7', 'NCIDAL105', 'ADXP_DAL_CODESYSTEM5', 'Suite 503', 'Rockville', 'RCK', 'RCK_CODE_SYS');

INSERT INTO dset_ad_value6(dset_ad_datatype_id, adxp_adl_value, adxp_al_value, adxp_bnn_value, adxp_bnr_value, adxp_bns_value, adxp_car_value, adxp_cen_value, adxp_cnt_value, adxp_cpa_value, adxp_cty_value, adxp_dal_value, adxp_del_value, adxp_dinsta_value, adxp_dinstq_value, adxp_dir_value, adxp_dmod_value, adxp_dmodid_value, adxp_int_value, adxp_pob_value, adxp_pre_value, adxp_sal_value, adxp_sta_value, adxp_stb_value, adxp_str_value, adxp_sttyp_value, adxp_unid_value, adxp_unit_value, adxp_zip_value, adxp_adl_code, adxp_bns_code, adxp_bns_codesystem, adxp_dal_code, adxp_dal_codesystem, adxp_int_code, adxp_int_codesystem, adxp_stb_code, adxp_stb_codesystem, adxp_zip_codesystem, adxp_zip_code)
VALUES (36, 'ADXP_ADL_VALUE1', 'ADXP_AL_VALUE1', 'ADXP_BNN_VALUE1', 'ADXP_BNR_VALUE1', 'ADXP_BNS_VALUE1', 'ADXP_CAR_VALUE1', 'ADXP_CEN_VALUE1', 'ADXP_CNT_VALUE1', 'ADXP_CPA_VALUE1', 'ADXP_CTY_VALUE1', 'ADXP_DAL_VALUE1', 'ADXP_DEL_VALUE1', 'ADXP_DINSTA_VALUE1', 'ADXP_DINSTQ_VALUE1', 'ADXP_DIR_VALUE1', 'ADXP_DMOD_VALUE1', 'ADXP_DMODID_VALUE1', 'ADXP_INT_VALUE1', 'ADXP_POB_VALUE1', 'ADXP_PRE_VALUE1', 'ADXP_SAL_VALUE1', 'ADXP_STA_VALUE1', 'ADXP_STB_VALUE1', 'ADXP_STR_VALUE1', 'ADXP_STTYP_VALUE1', 'ADXP_UNID_VALUE1', 'ADXP_UNIT_VALUE1', 'ADXP_ZIP_VALUE1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (37, 'ADXP_ADL_VALUE2', 'ADXP_AL_VALUE2', 'ADXP_BNN_VALUE2', 'ADXP_BNR_VALUE2', 'ADXP_BNS_VALUE2', 'ADXP_CAR_VALUE2', 'ADXP_CEN_VALUE2', 'ADXP_CNT_VALUE2', 'ADXP_CPA_VALUE2', 'ADXP_CTY_VALUE2', 'ADXP_DAL_VALUE2', 'ADXP_DEL_VALUE2', 'ADXP_DINSTA_VALUE2', 'ADXP_DINSTQ_VALUE2', 'ADXP_DIR_VALUE2', 'ADXP_DMOD_VALUE2', 'ADXP_DMODID_VALUE2', 'ADXP_INT_VALUE2', 'ADXP_POB_VALUE2', 'ADXP_PRE_VALUE2', 'ADXP_SAL_VALUE2', 'ADXP_STA_VALUE2', 'ADXP_STB_VALUE2', 'ADXP_STR_VALUE2', 'ADXP_STTYP_VALUE2', 'ADXP_UNID_VALUE2', 'ADXP_UNIT_VALUE2', 'ADXP_ZIP_VALUE2', 'ADXP_ADL_CODE', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (38, 'ADXP_ADL_VALUE3', 'ADXP_AL_VALUE3', 'ADXP_BNN_VALUE3', 'ADXP_BNR_VALUE3', 'ADXP_BNS_VALUE3', 'ADXP_CAR_VALUE3', 'ADXP_CEN_VALUE3', 'ADXP_CNT_VALUE3', 'ADXP_CPA_VALUE3', 'ADXP_CTY_VALUE3', 'ADXP_DAL_VALUE3', 'ADXP_DEL_VALUE3', 'ADXP_DINSTA_VALUE3', 'ADXP_DINSTQ_VALUE3', 'ADXP_DIR_VALUE3', 'ADXP_DMOD_VALUE3', 'ADXP_DMODID_VALUE3', 'ADXP_INT_VALUE3', 'ADXP_POB_VALUE3', 'ADXP_PRE_VALUE3', 'ADXP_SAL_VALUE3', 'ADXP_STA_VALUE3', 'ADXP_STB_VALUE3', 'ADXP_STR_VALUE3', 'ADXP_STTYP_VALUE3', 'ADXP_UNID_VALUE3', 'ADXP_UNIT_VALUE3', 'ADXP_ZIP_VALUE3', 'ADXP_ADL_CODE', 'ADXP_BNS_CODE', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (39, 'ADXP_ADL_VALUE4', 'ADXP_AL_VALUE4', 'ADXP_BNN_VALUE4', 'ADXP_BNR_VALUE4', 'ADXP_BNS_VALUE4', 'ADXP_CAR_VALUE4', 'ADXP_CEN_VALUE4', 'ADXP_CNT_VALUE4', 'ADXP_CPA_VALUE4', 'ADXP_CTY_VALUE4', 'ADXP_DAL_VALUE4', 'ADXP_DEL_VALUE4', 'ADXP_DINSTA_VALUE4', 'ADXP_DINSTQ_VALUE4', 'ADXP_DIR_VALUE4', 'ADXP_DMOD_VALUE4', 'ADXP_DMODID_VALUE4', 'ADXP_INT_VALUE4', 'ADXP_POB_VALUE4', 'ADXP_PRE_VALUE4', 'ADXP_SAL_VALUE4', 'ADXP_STA_VALUE4', 'ADXP_STB_VALUE4', 'ADXP_STR_VALUE4', 'ADXP_STTYP_VALUE4', 'ADXP_UNID_VALUE4', 'ADXP_UNIT_VALUE4', 'ADXP_ZIP_VALUE4', 'ADXP_ADL_CODE', 'ADXP_BNS_CODE', 'ADXP_BNS_CODESYSTEM', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (40, 'ADXP_ADL_VALUE5', 'ADXP_AL_VALUE5', 'ADXP_BNN_VALUE5', 'ADXP_BNR_VALUE5', 'ADXP_BNS_VALUE5', 'ADXP_CAR_VALUE5', 'ADXP_CEN_VALUE5', 'ADXP_CNT_VALUE5', 'ADXP_CPA_VALUE5', 'ADXP_CTY_VALUE5', 'ADXP_DAL_VALUE5', 'ADXP_DEL_VALUE5', 'ADXP_DINSTA_VALUE5', 'ADXP_DINSTQ_VALUE5', 'ADXP_DIR_VALUE5', 'ADXP_DMOD_VALUE5', 'ADXP_DMODID_VALUE5', 'ADXP_INT_VALUE5', 'ADXP_POB_VALUE5', 'ADXP_PRE_VALUE5', 'ADXP_SAL_VALUE5', 'ADXP_STA_VALUE5', 'ADXP_STB_VALUE5', 'ADXP_STR_VALUE5', 'ADXP_STTYP_VALUE5', 'ADXP_UNID_VALUE5', 'ADXP_UNIT_VALUE5', 'ADXP_ZIP_VALUE5', 'ADXP_ADL_CODE', 'ADXP_BNS_CODE', 'ADXP_BNS_CODESYSTEM', 'ADXP_DAL_CODE', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (41, 'ADXP_ADL_VALUE6', 'ADXP_AL_VALUE6', 'ADXP_BNN_VALUE6', 'ADXP_BNR_VALUE6', 'ADXP_BNS_VALUE6', 'ADXP_CAR_VALUE6', 'ADXP_CEN_VALUE6', 'ADXP_CNT_VALUE6', 'ADXP_CPA_VALUE6', 'ADXP_CTY_VALUE6', 'ADXP_DAL_VALUE6', 'ADXP_DEL_VALUE6', 'ADXP_DINSTA_VALUE6', 'ADXP_DINSTQ_VALUE6', 'ADXP_DIR_VALUE6', 'ADXP_DMOD_VALUE6', 'ADXP_DMODID_VALUE6', 'ADXP_INT_VALUE6', 'ADXP_POB_VALUE6', 'ADXP_PRE_VALUE6', 'ADXP_SAL_VALUE6', 'ADXP_STA_VALUE6', 'ADXP_STB_VALUE6', 'ADXP_STR_VALUE6', 'ADXP_STTYP_VALUE6', 'ADXP_UNID_VALUE6', 'ADXP_UNIT_VALUE6', 'ADXP_ZIP_VALUE6', 'ADXP_ADL_CODE', 'ADXP_BNS_CODE', 'ADXP_BNS_CODESYSTEM', 'ADXP_DAL_CODE', 'ADXP_DAL_CODESYSTEM', NULL, NULL, NULL, NULL, NULL, NULL),
  (42, 'ADXP_ADL_VALUE7', 'ADXP_AL_VALUE7', 'ADXP_BNN_VALUE7', 'ADXP_BNR_VALUE7', 'ADXP_BNS_VALUE7', 'ADXP_CAR_VALUE7', 'ADXP_CEN_VALUE7', 'ADXP_CNT_VALUE7', 'ADXP_CPA_VALUE7', 'ADXP_CTY_VALUE7', 'ADXP_DAL_VALUE7', 'ADXP_DEL_VALUE7', 'ADXP_DINSTA_VALUE7', 'ADXP_DINSTQ_VALUE7', 'ADXP_DIR_VALUE7', 'ADXP_DMOD_VALUE7', 'ADXP_DMODID_VALUE7', 'ADXP_INT_VALUE7', 'ADXP_POB_VALUE7', 'ADXP_PRE_VALUE7', 'ADXP_SAL_VALUE7', 'ADXP_STA_VALUE7', 'ADXP_STB_VALUE7', 'ADXP_STR_VALUE7', 'ADXP_STTYP_VALUE7', 'ADXP_UNID_VALUE7', 'ADXP_UNIT_VALUE7', 'ADXP_ZIP_VALUE7', 'ADXP_ADL_CODE', 'ADXP_BNS_CODE', 'ADXP_BNS_CODESYSTEM', 'ADXP_DAL_CODE', 'ADXP_DAL_CODESYSTEM', 'ADXP_INT_CODE', NULL, NULL, NULL, NULL, NULL),
  (43, 'ADXP_ADL_VALUE8', 'ADXP_AL_VALUE8', 'ADXP_BNN_VALUE8', 'ADXP_BNR_VALUE8', 'ADXP_BNS_VALUE8', 'ADXP_CAR_VALUE8', 'ADXP_CEN_VALUE8', 'ADXP_CNT_VALUE8', 'ADXP_CPA_VALUE8', 'ADXP_CTY_VALUE8', 'ADXP_DAL_VALUE8', 'ADXP_DEL_VALUE8', 'ADXP_DINSTA_VALUE8', 'ADXP_DINSTQ_VALUE8', 'ADXP_DIR_VALUE8', 'ADXP_DMOD_VALUE8', 'ADXP_DMODID_VALUE8', 'ADXP_INT_VALUE8', 'ADXP_POB_VALUE8', 'ADXP_PRE_VALUE8', 'ADXP_SAL_VALUE8', 'ADXP_STA_VALUE8', 'ADXP_STB_VALUE8', 'ADXP_STR_VALUE8', 'ADXP_STTYP_VALUE8', 'ADXP_UNID_VALUE8', 'ADXP_UNIT_VALUE8', 'ADXP_ZIP_VALUE8', 'ADXP_ADL_CODE', 'ADXP_BNS_CODE', 'ADXP_BNS_CODESYSTEM', 'ADXP_DAL_CODE', 'ADXP_DAL_CODESYSTEM', 'ADXP_INT_CODE', 'ADXP_INT_CODESYSTEM', NULL, NULL, NULL, NULL),
  (44, 'ADXP_ADL_VALUE9', 'ADXP_AL_VALUE9', 'ADXP_BNN_VALUE9', 'ADXP_BNR_VALUE9', 'ADXP_BNS_VALUE9', 'ADXP_CAR_VALUE9', 'ADXP_CEN_VALUE9', 'ADXP_CNT_VALUE9', 'ADXP_CPA_VALUE9', 'ADXP_CTY_VALUE9', 'ADXP_DAL_VALUE9', 'ADXP_DEL_VALUE9', 'ADXP_DINSTA_VALUE9', 'ADXP_DINSTQ_VALUE9', 'ADXP_DIR_VALUE9', 'ADXP_DMOD_VALUE9', 'ADXP_DMODID_VALUE9', 'ADXP_INT_VALUE9', 'ADXP_POB_VALUE9', 'ADXP_PRE_VALUE9', 'ADXP_SAL_VALUE9', 'ADXP_STA_VALUE9', 'ADXP_STB_VALUE9', 'ADXP_STR_VALUE9', 'ADXP_STTYP_VALUE9', 'ADXP_UNID_VALUE9', 'ADXP_UNIT_VALUE9', 'ADXP_ZIP_VALUE9', 'ADXP_ADL_CODE', 'ADXP_BNS_CODE', 'ADXP_BNS_CODESYSTEM', 'ADXP_DAL_CODE', 'ADXP_DAL_CODESYSTEM', 'ADXP_INT_CODE', 'ADXP_INT_CODESYSTEM', 'ADXP_STB_CODE', NULL, NULL, NULL),
  (45, 'ADXP_ADL_VALUE10', 'ADXP_AL_VALUE10', 'ADXP_BNN_VALUE10', 'ADXP_BNR_VALUE10', 'ADXP_BNS_VALUE10', 'ADXP_CAR_VALUE10', 'ADXP_CEN_VALUE10', 'ADXP_CNT_VALUE10', 'ADXP_CPA_VALUE10', 'ADXP_CTY_VALUE10', 'ADXP_DAL_VALUE10', 'ADXP_DEL_VALUE10', 'ADXP_DINSTA_VALUE10', 'ADXP_DINSTQ_VALUE10', 'ADXP_DIR_VALUE10', 'ADXP_DMOD_VALUE10', 'ADXP_DMODID_VALUE10', 'ADXP_INT_VALUE10', 'ADXP_POB_VALUE10', 'ADXP_PRE_VALUE10', 'ADXP_SAL_VALUE10', 'ADXP_STA_VALUE10', 'ADXP_STB_VALUE10', 'ADXP_STR_VALUE10', 'ADXP_STTYP_VALUE10', 'ADXP_UNID_VALUE10', 'ADXP_UNIT_VALUE10', 'ADXP_ZIP_VALUE10', 'ADXP_ADL_CODE', 'ADXP_BNS_CODE', 'ADXP_BNS_CODESYSTEM', 'ADXP_DAL_CODE', 'ADXP_DAL_CODESYSTEM', 'ADXP_INT_CODE', 'ADXP_INT_CODESYSTEM', 'ADXP_STB_CODE', 'ADXP_STB_CODESYSTEM', NULL, NULL),
  (46, 'ADXP_ADL_VALUE11', 'ADXP_AL_VALUE11', 'ADXP_BNN_VALUE11', 'ADXP_BNR_VALUE11', 'ADXP_BNS_VALUE11', 'ADXP_CAR_VALUE11', 'ADXP_CEN_VALUE11', 'ADXP_CNT_VALUE11', 'ADXP_CPA_VALUE11', 'ADXP_CTY_VALUE11', 'ADXP_DAL_VALUE11', 'ADXP_DEL_VALUE11', 'ADXP_DINSTA_VALUE11', 'ADXP_DINSTQ_VALUE11', 'ADXP_DIR_VALUE11', 'ADXP_DMOD_VALUE11', 'ADXP_DMODID_VALUE11', 'ADXP_INT_VALUE11', 'ADXP_POB_VALUE11', 'ADXP_PRE_VALUE11', 'ADXP_SAL_VALUE11', 'ADXP_STA_VALUE11', 'ADXP_STB_VALUE11', 'ADXP_STR_VALUE11', 'ADXP_STTYP_VALUE11', 'ADXP_UNID_VALUE11', 'ADXP_UNIT_VALUE11', 'ADXP_ZIP_VALUE11', 'ADXP_ADL_CODE', 'ADXP_BNS_CODE', 'ADXP_BNS_CODESYSTEM', 'ADXP_DAL_CODE', 'ADXP_DAL_CODESYSTEM', 'ADXP_INT_CODE', 'ADXP_INT_CODESYSTEM', 'ADXP_STB_CODE', 'ADXP_STB_CODESYSTEM', 'ADXP_ZIP_CODESYSTEM', NULL),
  (47, 'ADXP_ADL_VALUE12', 'ADXP_AL_VALUE12', 'ADXP_BNN_VALUE12', 'ADXP_BNR_VALUE12', 'ADXP_BNS_VALUE12', 'ADXP_CAR_VALUE12', 'ADXP_CEN_VALUE12', 'ADXP_CNT_VALUE12', 'ADXP_CPA_VALUE12', 'ADXP_CTY_VALUE12', 'ADXP_DAL_VALUE12', 'ADXP_DEL_VALUE12', 'ADXP_DINSTA_VALUE12', 'ADXP_DINSTQ_VALUE12', 'ADXP_DIR_VALUE12', 'ADXP_DMOD_VALUE12', 'ADXP_DMODID_VALUE12', 'ADXP_INT_VALUE12', 'ADXP_POB_VALUE12', 'ADXP_PRE_VALUE12', 'ADXP_SAL_VALUE12', 'ADXP_STA_VALUE12', 'ADXP_STB_VALUE12', 'ADXP_STR_VALUE12', 'ADXP_STTYP_VALUE12', 'ADXP_UNID_VALUE12', 'ADXP_UNIT_VALUE12', 'ADXP_ZIP_VALUE12', 'ADXP_ADL_CODE', 'ADXP_BNS_CODE', 'ADXP_BNS_CODESYSTEM', 'ADXP_DAL_CODE', 'ADXP_DAL_CODESYSTEM', 'ADXP_INT_CODE', 'ADXP_INT_CODESYSTEM', 'ADXP_STB_CODE', 'ADXP_STB_CODESYSTEM', 'ADXP_ZIP_CODESYSTEM', 'ADXP_ZIP_CODE');

INSERT INTO dset_ad_value7(id, dset_ad_datatype_id, adxp_al_value, adxp_al_code, adxp_al_codesystem, adxp_dal_code, adxp_dal_codesystem, adxp_dal_value, adxp_cty_value, adxp_cty_code, adxp_cty_codesystem)
VALUES (1, 49, '100 Jefferson Street', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (2, 50, '101 Jefferson Street', 'NCIAL101', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (3, 51, '102 Jefferson Street', 'NCIAL102', 'ADXP_AL_CODESYSTEM1', NULL, NULL, NULL, NULL, NULL, NULL),
  (4, 52, '103 Jefferson Street', 'NCIAL103', 'ADXP_AL_CODESYSTEM2', 'NCIDAL101', NULL, NULL, NULL, NULL, NULL),
  (5, 53, '104 Jefferson Street', 'NCIAL104', 'ADXP_AL_CODESYSTEM3', 'NCIDAL102', 'ADXP_DAL_CODESYSTEM5', NULL, NULL, NULL, NULL),
  (6, 54, '105 Jefferson Street', 'NCIAL105', 'ADXP_AL_CODESYSTEM4', 'NCIDAL103', 'ADXP_DAL_CODESYSTEM5', 'Suite 501', NULL, NULL, NULL),
  (7, 55, '106 Jefferson Street', 'NCIAL106', 'ADXP_AL_CODESYSTEM5', 'NCIDAL104', 'ADXP_DAL_CODESYSTEM5', 'Suite 502', 'Rockville', NULL, NULL),
  (8, 56, '107 Jefferson Street', 'NCIAL107', 'ADXP_AL_CODESYSTEM6', 'NCIDAL105', 'ADXP_DAL_CODESYSTEM5', 'Suite 503', 'Rockville', 'RCK', NULL),
  (9, 57, '108 Jefferson Street', 'NCIAL108', 'ADXP_AL_CODESYSTEM7', 'NCIDAL106', 'ADXP_DAL_CODESYSTEM5', 'Suite 504', 'Rockville', 'RCK', 'RCK_CODE_SYS'),
  (10, 58, '109 Jefferson Street', 'NCIAL108', 'ADXP_AL_CODESYSTEM8', 'NCIDAL107', 'ADXP_DAL_CODESYSTEM5', 'Suite 505', 'Rockville', 'RCK', 'RCK_CODE_SYS');

INSERT INTO dset_ad_value8(id, adxp_al_value, adxp_al_code, adxp_al_codesystem, adxp_dal_code, adxp_dal_codesystem, adxp_dal_value, adxp_cty_value, adxp_cty_code, adxp_cty_codesystem)
VALUES (1, '100 Jefferson Street', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (2, '101 Jefferson Street', 'NCIAL101', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (3, '102 Jefferson Street', 'NCIAL102', 'ADXP_AL_CODESYSTEM1', NULL, NULL, NULL, NULL, NULL, NULL),
  (4, '103 Jefferson Street', 'NCIAL103', 'ADXP_AL_CODESYSTEM2', 'NCIDAL100', NULL, NULL, NULL, NULL, NULL),
  (5, '104 Jefferson Street', 'NCIAL104', 'ADXP_AL_CODESYSTEM3', 'NCIDAL101', 'ADXP_DAL_CODESYSTEM1', NULL, NULL, NULL, NULL),
  (6, '105 Jefferson Street', 'NCIAL105', 'ADXP_AL_CODESYSTEM4', 'NCIDAL102', 'ADXP_DAL_CODESYSTEM2', 'Suite 500', NULL, NULL, NULL),
  (7, '106 Jefferson Street', 'NCIAL106', 'ADXP_AL_CODESYSTEM5', 'NCIDAL103', 'ADXP_DAL_CODESYSTEM3', 'Suite 501', 'Rockville', NULL, NULL),
  (8, '107 Jefferson Street', 'NCIAL107', 'ADXP_AL_CODESYSTEM6', 'NCIDAL104', 'ADXP_DAL_CODESYSTEM4', 'Suite 502', 'Rockville', 'RCK', NULL),
  (9, '108 Jefferson Street', 'NCIAL108', 'ADXP_AL_CODESYSTEM7', 'NCIDAL105', 'ADXP_DAL_CODESYSTEM5', 'Suite 503', 'Rockville', 'RCK', 'RCK_CODE_SYS'),
  (10, '108 Jefferson Street', 'NCIAL108', 'ADXP_AL_CODESYSTEM7', 'NCIDAL105', 'ADXP_DAL_CODESYSTEM5', 'Suite 503', 'Rockville', 'RCK', 'RCK_CODE_SYS');

INSERT INTO dset_cd(id)
VALUES (1),
  (2),
  (3),
  (4),
  (5),
  (6),
  (7),
  (8),
  (9),
  (10),
  (11),
  (12),
  (13),
  (14),
  (15),
  (16),
  (17),
  (18),
  (19),
  (20),
  (21),
  (22),
  (23),
  (24),
  (25),
  (26),
  (27),
  (28),
  (29),
  (30),
  (31),
  (32),
  (33),
  (34),
  (35);

INSERT INTO dset_cd_cd_value7(dset_cd_id, dset_cd_value7_id)
VALUES (31, 1),
  (32, 2),
  (33, 3),
  (34, 4),
  (35, 5);

INSERT INTO dset_cd_value1(dset_cd_id, code)
VALUES (1, 'CODE1'),
  (2, 'CODE2'),
  (3, 'CODE3'),
  (4, 'CODE4'),
  (5, 'CODE5');

INSERT INTO dset_cd_value2(dset_cd_id, code, null_flavor)
VALUES (6, 'CODE1', NULL),
  (7, 'CODE2', NULL),
  (8, 'CODE3', NULL),
  (9, NULL, 'NI'),
  (10, NULL, 'NI');

INSERT INTO dset_cd_value3(dset_cd_id, code, code_system, code_system_name)
VALUES (11, 'CODE1', 'CODE_SYSTEM1', 'CODE_SYSTEM_NAME1'),
  (12, 'CODE2', 'CODE_SYSTEM2', 'CODE_SYSTEM_NAME2'),
  (13, 'CODE3', 'CODE_SYSTEM3', 'CODE_SYSTEM_NAME3'),
  (14, 'CODE4', 'CODE_SYSTEM4', 'CODE_SYSTEM_NAME4'),
  (15, 'CODE5', 'CODE_SYSTEM5', 'CODE_SYSTEM_NAME5');

INSERT INTO dset_cd_value4(dset_cd_id, code, code_system, code_system_name, code_system_version, displayable_value, originaltext_value, originaltext_desc)
VALUES (16, 'CODE1', 'CODE_SYSTEM1', 'CODE_SYSTEM_NAME1', 'CODE_SYSTEM_VERSION1', 'CODE1', 'OrgTextVal1', 'OrgTextDesc1'),
  (17, 'CODE2', 'CODE_SYSTEM2', 'CODE_SYSTEM_NAME2', 'CODE_SYSTEM_VERSION2', 'CODE2', 'OrgTextVal2', 'OrgTextDesc2'),
  (18, 'CODE3', 'CODE_SYSTEM3', 'CODE_SYSTEM_NAME3', 'CODE_SYSTEM_VERSION3', 'CODE3', 'OrgTextVal3', 'OrgTextDesc3'),
  (19, 'CODE4', 'CODE_SYSTEM4', 'CODE_SYSTEM_NAME4', 'CODE_SYSTEM_VERSION4', 'CODE4', 'OrgTextVal4', 'OrgTextDesc4'),
  (20, 'CODE5', 'CODE_SYSTEM5', 'CODE_SYSTEM_NAME5', 'CODE_SYSTEM_VERSION5', 'CODE5', 'OrgTextVal5', 'OrgTextDesc5');

INSERT INTO dset_cd_value5(dset_cd_id, code, code_system, code_system_name, code_system_version, displayable_value, originaltext_value, originaltext_desc)
VALUES (21, 'CODE1', 'CODE_SYSTEM1', 'CODE_SYSTEM_NAME1', 'CODE_SYSTEM_VERSION1', 'CODE1', 'OrgTextVal1', 'OrgTextDesc1'),
  (22, 'CODE2', 'CODE_SYSTEM2', 'CODE_SYSTEM_NAME2', 'CODE_SYSTEM_VERSION2', 'CODE2', 'OrgTextVal2', 'OrgTextDesc2'),
  (23, 'CODE3', 'CODE_SYSTEM3', 'CODE_SYSTEM_NAME3', 'CODE_SYSTEM_VERSION3', 'CODE3', 'OrgTextVal3', 'OrgTextDesc3'),
  (24, 'CODE4', 'CODE_SYSTEM4', 'CODE_SYSTEM_NAME4', 'CODE_SYSTEM_VERSION4', 'CODE4', 'OrgTextVal4', 'OrgTextDesc4'),
  (25, 'CODE5', 'CODE_SYSTEM5', 'CODE_SYSTEM_NAME5', 'CODE_SYSTEM_VERSION5', 'CODE5', 'OrgTextVal5', 'OrgTextDesc5');

INSERT INTO dset_cd_value6(id, dset_cd_id, code)
VALUES (1, 26, 'CODE1'),
  (2, 27, 'CODE2'),
  (3, 28, 'CODE3'),
  (4, 29, 'CODE4'),
  (5, 30, 'CODE5');

INSERT INTO dset_cd_value7(id, code)
VALUES (1, 'CODE1'),
  (2, 'CODE2'),
  (3, 'CODE3'),
  (4, 'CODE4'),
  (5, 'CODE5');

INSERT INTO dset_ii(id)
VALUES (1),
  (2),
  (3),
  (4),
  (5),
  (6),
  (7),
  (8),
  (9),
  (10),
  (11),
  (12),
  (13),
  (14),
  (15),
  (16),
  (17),
  (18),
  (19),
  (20),
  (21),
  (22),
  (23),
  (24),
  (25),
  (26),
  (27),
  (28),
  (29);

INSERT INTO dset_ii_ii_value6(dset_ii_id, dset_ii_value6_id)
VALUES (26, 1),
  (27, 1),
  (28, 1),
  (29, 2),
  (29, 3);

INSERT INTO dset_ii_value1(dset_ii_id, extension)
VALUES (1, 'Extension1'),
  (2, 'Extension2'),
  (3, 'Extension3'),
  (4, 'Extension4'),
  (5, 'Extension5');

INSERT INTO dset_ii_value2(dset_ii_id, extension, root, null_flavor)
VALUES (6, NULL, NULL, 'NI'),
  (7, 'Extension2', 'ROOT2', NULL),
  (8, 'Extension3', 'ROOT3', NULL),
  (9, 'Extension4', 'ROOT4', NULL),
  (10, 'Extension5', 'ROOT5', NULL);

INSERT INTO dset_ii_value3(dset_ii_id, extension, identifier_name, displayable_value, reliability, scope)
VALUES (11, 'Extension1', NULL, NULL, NULL, NULL),
  (12, NULL, 'IDENTIFIER_NAME2', NULL, NULL, NULL),
  (13, 'Extension3', 'IDENTIFIER_NAME3', NULL, NULL, NULL),
  (14, 'Extension4', 'IDENTIFIER_NAME4', NULL, NULL, NULL),
  (15, 'Extension5', 'IDENTIFIER_NAME5', NULL, NULL, NULL);

INSERT INTO dset_ii_value4(dset_ii_id, root, extension, identifier_name, reliability, scope, displayable_value, null_flavor)
VALUES (16, 'Root1', 'Extension1', NULL, NULL, NULL, '1', NULL),
  (17, 'Root2', 'Extension2', 'IDENTIFIER_NAME2', NULL, NULL, '1', NULL),
  (18, 'Root3', NULL, 'IDENTIFIER_NAME3', 'ISS', NULL, '1', NULL),
  (19, 'Root4', 'Extension4', 'IDENTIFIER_NAME4', 'ISS', 'BUSN', '0', NULL),
  (20, 'Root5', 'Extension5', 'IDENTIFIER_NAME5', 'ISS', 'BUSN', '1', NULL);

INSERT INTO dset_ii_value5(id, dset_ii_id, extension)
VALUES (1, 21, 'Extension1'),
  (2, 22, 'Extension2'),
  (3, 23, 'Extension3'),
  (4, 24, 'Extension4'),
  (5, 25, 'Extension5');

INSERT INTO dset_ii_value6(id, extension)
VALUES (1, 'Extension1'),
  (2, 'Extension4'),
  (3, 'Extension5');

INSERT INTO dset_tel(id, null_flavor)
VALUES (0, NULL),
  (1, NULL),
  (2, NULL),
  (3, NULL),
  (4, NULL),
  (5, NULL),
  (6, NULL),
  (7, NULL),
  (8, NULL),
  (9, NULL),
  (10, NULL),
  (11, NULL),
  (12, NULL),
  (13, NULL),
  (14, NULL);

INSERT INTO dset_tel_email(id, null_flavor)
VALUES (0, NULL),
  (1, NULL),
  (2, NULL),
  (3, NULL),
  (4, NULL),
  (5, NULL),
  (6, NULL),
  (7, NULL),
  (8, NULL),
  (9, NULL);

INSERT INTO dset_tel_email_value1(dset_tel_email_id, tel_email_value)
VALUES (0, 'TEL_EMAIL_VALUE1'),
  (1, 'TEL_EMAIL_VALUE2'),
  (2, 'TEL_EMAIL_VALUE3'),
  (3, 'TEL_EMAIL_VALUE4'),
  (4, 'TEL_EMAIL_VALUE5'),
  (5, 'TEL_EMAIL_VALUE1'),
  (6, 'TEL_EMAIL_VALUE2'),
  (7, 'TEL_EMAIL_VALUE3'),
  (8, 'TEL_EMAIL_VALUE4'),
  (9, 'TEL_EMAIL_VALUE5');

INSERT INTO dset_tel_person(id, null_flavor)
VALUES (0, NULL),
  (1, NULL),
  (2, NULL),
  (3, NULL),
  (4, NULL);

INSERT INTO dset_tel_person_value1(dset_tel_person_id, tel_person_value)
VALUES (0, 'TEL_PERSON_VALUE1'),
  (1, 'TEL_PERSON_VALUE2'),
  (2, 'TEL_PERSON_VALUE3'),
  (3, 'TEL_PERSON_VALUE4'),
  (4, 'TEL_PERSON_VALUE5');

INSERT INTO dset_tel_phone(id, null_flavor)
VALUES (0, NULL),
  (1, NULL),
  (2, NULL),
  (3, NULL),
  (4, NULL);

INSERT INTO dset_tel_phone_value1(dset_tel_phone_id, tel_phone_value)
VALUES (0, 'TEL_PHONE_VALUE1'),
  (1, 'TEL_PHONE_VALUE2'),
  (2, 'TEL_PHONE_VALUE3'),
  (3, 'TEL_PHONE_VALUE4'),
  (4, 'TEL_PHONE_VALUE5');

INSERT INTO dset_tel_tel_value_3(dset_tel_id, dset_tel_value3_id)
VALUES (10, 1),
  (11, 1),
  (12, 1),
  (13, 1),
  (14, 1);

INSERT INTO dset_tel_url(id, null_flavor)
VALUES (0, NULL),
  (1, NULL),
  (2, NULL),
  (3, NULL),
  (4, NULL);

INSERT INTO dset_tel_url_value1(dset_tel_url_id, tel_url_value)
VALUES (0, 'TEL_URL_VALUE1'),
  (1, 'TEL_URL_VALUE2'),
  (2, 'TEL_URL_VALUE3'),
  (3, 'TEL_URL_VALUE4'),
  (4, 'TEL_URL_VALUE5');

INSERT INTO dset_tel_value1(dset_tel_id, tel_value)
VALUES (0, 'tel://123-456-7891'),
  (1, 'tel://123-456-7892'),
  (2, 'tel://123-456-7893'),
  (3, 'tel://123-456-7894'),
  (4, 'tel://123-456-7895');

INSERT INTO dset_tel_value2(id, dset_tel_id, tel_value)
VALUES (1, 5, 'tel://123-456-7891'),
  (2, 6, 'tel://123-456-7892'),
  (3, 7, 'tel://123-456-7893'),
  (4, 8, 'tel://123-456-7894'),
  (5, 9, 'tel://123-456-7895');

INSERT INTO dset_tel_value3(id, tel_value)
VALUES (1, 'tel://123-456-7892');

INSERT INTO ed_datatype(id, value1_data, value2_null_flavor, value2_data, value2_compression, value3_null_flavor, value3_data, value3_compression, value3_description, value3_value)
VALUES (1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (2, '0110101010010', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (3, '0100101010011', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (4, '0100001010010', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (5, '0110001010011', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (6, '0110001011110', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (7, NULL, 'NI', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (8, NULL, NULL, '0110001010010', NULL, NULL, NULL, NULL, NULL, NULL),
  (9, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (10, NULL, NULL, NULL, 'GZ', NULL, NULL, NULL, NULL, NULL),
  (11, NULL, NULL, '0110001010010', 'GZ', NULL, NULL, NULL, NULL, NULL),
  (12, NULL, NULL, NULL, NULL, NULL, NULL, 'GZ', NULL, NULL),
  (13, NULL, NULL, NULL, NULL, 'NI', NULL, NULL, NULL, NULL),
  (14, NULL, NULL, NULL, NULL, NULL, NULL, 'GZ', NULL, NULL),
  (15, NULL, NULL, NULL, NULL, NULL, NULL, 'GZ', 'DESCRIPTION', NULL),
  (16, NULL, NULL, NULL, NULL, NULL, '0110001010010', 'GZ', 'DESCRIPTION', 'VALUE3_VALUE_A');

INSERT INTO ed_text_datatype(id, value1_value, value2_null_flavor, value2_value, value3_null_flavor, value3_data, value3_compression, value3_description, value3_value)
VALUES (17, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (18, 'ED_TEXT_VALUE1_VALUE1', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (19, 'ED_TEXT_VALUE1_VALUE2', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (20, 'ED_TEXT_VALUE1_VALUE3', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (21, 'ED_TEXT_VALUE1_VALUE4', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (22, 'ED_TEXT_VALUE1_VALUE5', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (23, NULL, 'NI', NULL, NULL, NULL, NULL, NULL, NULL),
  (24, NULL, NULL, 'ED_TEXT_VALUE2_VALUE1', NULL, NULL, NULL, NULL, NULL),
  (25, NULL, NULL, 'ED_TEXT_VALUE2_VALUE2', NULL, NULL, NULL, NULL, NULL),
  (26, NULL, NULL, 'ED_TEXT_VALUE2_VALUE3', NULL, NULL, NULL, NULL, NULL),
  (27, NULL, NULL, 'ED_TEXT_VALUE2_VALUE4', NULL, NULL, NULL, NULL, NULL),
  (28, NULL, NULL, NULL, NULL, NULL, 'GZ', NULL, NULL),
  (29, NULL, NULL, NULL, 'NI', NULL, NULL, NULL, NULL),
  (30, NULL, NULL, NULL, NULL, '0110001011010', 'GZ', NULL, NULL),
  (31, NULL, NULL, NULL, NULL, '0110111010011', 'GZ', 'DESCRIPTION', NULL),
  (32, NULL, NULL, NULL, NULL, '0110001010010', 'GZ', 'DESCRIPTION', 'VALUE3_VALUE_A');

INSERT INTO en_datatype(id, value1_pn_value, value2_pn_value, value2_pn_code, value3_pn_value, value3_pn_code, value3_pn_code_system, value3_pn_code_system_version, value4_pn_value, value4_pn_enpq, value5_pn_value, value5_pn2_value, value5_pn_code, value5_pn2_code, value5_pn_code_system, value5_pn2_code_system, value5_pn_code_system_version, value5_pn2_code_system_version, value6_pn_value, value6_on_value, value6_pn_code, value6_on_code, value6_pn_code_system, value6_on_code_system, value6_pn_code_system_version, value6_on_code_system_version)
VALUES (1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (2, 'Mr. John Doe Jr.', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (3, 'Mr. John Doe Double Jr.', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (4, 'Mr. John Doe Sr.', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (5, 'Mr. John Doe Super Sr.', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (6, 'Mr. John Doe II', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (7, NULL, 'Mr. John Doe Jr1.', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (8, NULL, 'Mr. John Doe Jr2.', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (9, NULL, 'Mr. John Doe Jr3.', 'JDJ3', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (10, NULL, 'Mr. John Doe Jr4.', 'JDJ4', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (11, NULL, 'Mr. John Doe Jr5.', 'JDJ5', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (12, NULL, NULL, NULL, 'Mrs. Sarah Doe I', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (13, NULL, NULL, NULL, 'Mrs. Sarah Doe II', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (14, NULL, NULL, NULL, 'Mrs. Sarah Doe III', 'MSD1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (15, NULL, NULL, NULL, 'Mrs. Sarah Doe IV', 'MSD2', 'VALUE3_PN_CODE_SYSTEM1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (16, NULL, NULL, NULL, 'Mrs. Sarah Doe V', 'MSD3', 'VALUE3_PN_CODE_SYSTEM2', '1.3', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (17, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'VALUE4_PN_VALUE1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (18, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'BR', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (19, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'VALUE4_PN_VALUE3', 'BR', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (20, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'VALUE4_PN_VALUE4', 'BR', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (21, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'VALUE4_PN_VALUE1', 'SP,BR,NB', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (22, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'Mr. John Doe I', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (23, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'Mr. John Doe II', NULL, 'JDJ1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (24, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'Mr. John Doe III', NULL, 'JDJ1', NULL, 'VALUE5_PN_CODE_SYSTEM1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (25, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'Mr. John Doe IV', NULL, 'JDJ1', NULL, 'VALUE5_PN_CODE_SYSTEM1', NULL, '2.1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (26, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'Mr. John Doe V', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (27, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'Mr. John Doe VI', NULL, 'VALUE5_PN2_CODE1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (28, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'Mr. John Doe VII', NULL, 'VALUE5_PN2_CODE2', NULL, 'VALUE5_PN2_CODE_SYSTEM1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (29, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'Mr. John Doe VIII', NULL, 'VALUE5_PN2_CODE3', NULL, 'VALUE5_PN2_CODE_SYSTEM2', NULL, '2.2', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (30, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'Mr. John Doe I', 'Mr. John Doe V', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (31, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'Mr. John Doe II', 'Mr. John Doe VI', 'VALUE5_PN_CODE1', 'VALUE5_PN2_CODE1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (32, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'Mr. John Doe III', 'Mr. John Doe VII', 'VALUE5_PN_CODE2', 'VALUE5_PN2_CODE2', 'VALUE5_PN_CODE_SYSTEM1', 'VALUE5_PN2_CODE_SYSTEM1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (33, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'Mr. John Doe VI', 'Mr. John Doe VIII', 'VALUE5_PN_CODE3', 'VALUE5_PN2_CODE3', 'VALUE5_PN_CODE_SYSTEM2', 'VALUE5_PN2_CODE_SYSTEM2', '2.1', '2.2', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (34, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'Mr. John Doe I', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (35, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'Mr. John Doe II', NULL, 'VALUE6_PN_CODE1', NULL, NULL, NULL, NULL, NULL),
  (36, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'Mr. John Doe III', NULL, 'VALUE6_PN_CODE2', NULL, 'VALUE6_PN_CODE_SYSTEM1', NULL, NULL, NULL),
  (37, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'Mr. John Doe IV', NULL, 'VALUE6_PN_CODE3', NULL, 'VALUE6_PN_CODE_SYSTEM2', NULL, '3.1', NULL),
  (38, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'Mr. John Doe V', NULL, NULL, NULL, NULL, NULL, NULL),
  (39, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'Mr. John Doe VI', NULL, 'VALUE6_ON_CODE1', NULL, NULL, NULL, NULL),
  (40, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'Mr. John Doe VII', NULL, 'VALUE6_ON_CODE1', NULL, 'VALUE6_ON_CODE_SYSTEM1', NULL, NULL),
  (41, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'Mr. John Doe VIII', NULL, 'VALUE6_ON_CODE2', NULL, 'VALUE6_ON_CODE_SYSTEM2', NULL, '1.1'),
  (42, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'Mr. John Doe I', 'Mr. John Doe V', NULL, NULL, NULL, NULL, NULL, NULL),
  (43, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'Mr. John Doe II', 'Mr. John Doe VI', 'VALUE6_PN_CODE1', 'VALUE6_ON_CODE1', NULL, NULL, NULL, NULL),
  (44, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'Mr. John Doe III', 'Mr. John Doe VII', 'VALUE6_PN_CODE2', 'VALUE6_ON_CODE2', 'VALUE6_PN_CODE_SYSTEM2', 'VALUE6_ON_CODE_SYSTEM2', NULL, NULL),
  (45, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'Mr. John Doe IV', 'Mr. John Doe VIII', 'VALUE6_PN_CODE3', 'VALUE6_ON_CODE3', 'VALUE6_PN_CODE_SYSTEM3', 'VALUE6_ON_CODE_SYSTEM3', '2.1', '1.1');

INSERT INTO en_on_datatype(id, value1_on_value)
VALUES (1, NULL),
  (2, 'NCI1'),
  (3, 'NCI2'),
  (4, 'NCI3'),
  (5, 'NCI4'),
  (6, 'NCI5');

INSERT INTO en_pn_datatype(id, value1_pn_value)
VALUES (1, NULL),
  (2, 'Mr. John Doe'),
  (3, 'Mr. John Doe II'),
  (4, 'Mr. John Doe III'),
  (5, 'Mr. John Doe IV'),
  (6, 'Mr. John Doe V');

INSERT INTO ii_datatype(id, value1_extension, value2_null_flavor, value2_root, value2_extension, value3_null_flavor, value3_extension, value3_identifier_name, value3_reliability, value3_scope, value3_displayable, value4_null_flavor, value4_root, value4_extension, value4_identifier_name, value4_reliability, value4_scope, value4_displayable)
VALUES (1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (2, 'II_Extension', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (4, NULL, 'NI', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (5, NULL, NULL, 'II_VALUE2_ROOT', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (6, NULL, NULL, 'II_VALUE2_ROOT', 'II_VALUE2_EXTENSION', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (7, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (8, NULL, NULL, NULL, NULL, 'UNK', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (9, NULL, NULL, NULL, NULL, NULL, 'II_VALUE3_EXTENSION', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (10, NULL, NULL, NULL, NULL, NULL, 'II_VALUE3_EXTENSION', 'II_VALUE3_IDENTIFIER_NAME', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (11, NULL, NULL, NULL, NULL, NULL, 'II_VALUE3_EXTENSION', 'II_VALUE3_IDENTIFIER_NAME', 'ISS', 'BUSN', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (12, NULL, NULL, NULL, NULL, NULL, 'II_VALUE3_EXTENSION', 'II_VALUE3_IDENTIFIER_NAME', 'VRF', 'BUSN', '0', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (13, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (14, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'INV', NULL, NULL, NULL, NULL, NULL, NULL),
  (15, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'II_VALUE4_ROOT', 'II_VALUE4_EXTENSION', NULL, NULL, NULL, NULL),
  (16, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'II_VALUE4_ROOT', 'II_VALUE4_EXTENSION', 'II_VALUE4_IDENTIFIER_NAME', NULL, NULL, NULL),
  (17, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'II_VALUE4_ROOT', 'II_VALUE4_EXTENSION', 'II_VALUE4_IDENTIFIER_NAME', 'UNV', NULL, NULL),
  (18, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'II_VALUE4_ROOT', 'II_VALUE4_EXTENSION', 'II_VALUE4_IDENTIFIER_NAME', 'ISS', 'BUSN', NULL),
  (19, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'II_VALUE4_ROOT', 'II_VALUE4_EXTENSION', 'II_VALUE4_IDENTIFIER_NAME', 'VRF', 'BUSN', '0');

INSERT INTO int_datatype(id, value1_value, value2_null_flavor, value2_value)
VALUES (1, NULL, NULL, NULL),
  (2, 1, NULL, NULL),
  (3, 2, NULL, NULL),
  (4, 3, NULL, NULL),
  (5, 4, NULL, NULL),
  (6, 5, NULL, NULL),
  (7, NULL, NULL, 6),
  (8, NULL, NULL, 7),
  (9, NULL, NULL, 8),
  (10, NULL, 'NI', NULL),
  (11, NULL, 'NI', NULL),
  (12, NULL, 'NI', NULL);

INSERT INTO ivl_int(id, value1_low_value, value1_high_value, value2_high_value, value2_lowclosed, value3_any_value, value3_low_value, value3_high_closed, value4_low_value, value4_high_value, value4_width_value, value4_null_flavor)
VALUES (1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (2, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (3, NULL, 10, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (4, 1, 10, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (5, 1, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (6, 2, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (7, NULL, NULL, 10, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (8, NULL, NULL, NULL, '1', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (9, NULL, NULL, 10, '1', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (10, NULL, NULL, 1, '0', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (11, NULL, NULL, 1, '1', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (12, NULL, NULL, NULL, NULL, 5, 11, '1', NULL, NULL, NULL, NULL),
  (13, NULL, NULL, NULL, NULL, 6, 12, '1', NULL, NULL, NULL, NULL),
  (14, NULL, NULL, NULL, NULL, 7, 13, '1', NULL, NULL, NULL, NULL),
  (15, NULL, NULL, NULL, NULL, 8, 14, '1', NULL, NULL, NULL, NULL),
  (16, NULL, NULL, NULL, NULL, 9, 15, '1', NULL, NULL, NULL, NULL),
  (17, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 5, 11, 5, 'NI'),
  (18, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 6, 12, 4, 'NI'),
  (19, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 7, 13, 3, 'NI'),
  (20, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 8, 14, NULL, NULL),
  (21, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 9, NULL, NULL, 'NI');

INSERT INTO ivl_pq(id, value1_low_value, value2_low_value, value2_low_precision, value2_low_unit, value3_low_value, value3_low_precision, value3_low_unit, value3_null_flavor, value4_high_value, value4_high_precision, value4_high_unit, value4_high_closed, value4_high_null_flavor, value4_low_value, value4_low_precision, value4_low_unit, value4_low_null_flavor, value4_low_closed, value4_width_value, value4_width_precision, value4_width_unit, value4_width_null_flavor, value4_null_flavor)
VALUES (1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (2, 1.1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (3, 2.1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (4, 3.1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (5, 4.1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (6, 5.1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (7, NULL, 221.1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (8, NULL, 222.1, 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (9, NULL, 221.1, 2, 'Unit', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (10, NULL, 222.1, NULL, 'Unit', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (11, NULL, 223.1, 2, 'Unit', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (12, NULL, 224.1, 2, 'Unit', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (13, NULL, NULL, NULL, NULL, 224.1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (14, NULL, NULL, NULL, NULL, 224.1, 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (15, NULL, NULL, NULL, NULL, NULL, 2, 'Unit', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (16, NULL, NULL, NULL, NULL, 224.1, 2, 'Unit', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (17, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'NI', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (18, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 5.1, 2, 'VALUE4_HIGH_UNIT1', '1', NULL, 1.1, 2, 'VALUE4_LOW_UNIT', NULL, '1', 5.1, 2, 'VALUE4_WIDTH_UNIT1', NULL, NULL),
  (19, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 6.1, 2, 'VALUE4_HIGH_UNIT2', '1', NULL, 1.1, 2, 'VALUE4_LOW_UNIT', NULL, '1', 5.1, 2, 'VALUE4_WIDTH_UNIT2', NULL, NULL),
  (20, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'NI', NULL, NULL, NULL, 'NI', NULL, NULL, NULL, NULL, 'NI', 'NA'),
  (21, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 8.1, 2, 'VALUE4_HIGH_UNIT4', '1', NULL, NULL, NULL, NULL, 'NI', '1', 5, 2, 'VALUE4_WIDTH_UNIT4', NULL, NULL),
  (22, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 9.1, 2, 'VALUE4_HIGH_UNIT5', '1', NULL, 1.1, 2, NULL, NULL, '1', NULL, NULL, NULL, 'NI', NULL);

INSERT INTO ivl_pqv(id, value1_low_value, value1_high_value, value2_low_value, value2_low_precision, value2_high_closed, value3_low_value, value3_low_precision, value3_high_value, value3_high_precision, value3_high_null_flavor, value4_high_value, value4_high_precision, value4_high_null_flavor, value4_low_value, value4_low_precision, value4_low_null_flavor, value4_width_value, value4_width_precision, value4_width_null_flavor)
VALUES (1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (2, 1.1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (3, 2.1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (4, 3.1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (5, 4.1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (6, 5.1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (7, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (8, NULL, NULL, 1.1, 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (9, NULL, NULL, 2.1, 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (10, NULL, NULL, 3.1, 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (11, NULL, NULL, 4.1, 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (12, NULL, NULL, 5.1, 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (13, NULL, NULL, 1.1, 2, '1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (14, NULL, NULL, 2.1, 2, '1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (15, NULL, NULL, 3.1, 2, '1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (16, NULL, NULL, 4.1, 2, '1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (17, NULL, NULL, 5.1, 2, '1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (18, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (19, NULL, NULL, NULL, NULL, NULL, 1.1, 2, 610.1, 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (20, NULL, NULL, NULL, NULL, NULL, 2.1, 2, 620.1, 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (21, NULL, NULL, NULL, NULL, NULL, 3.1, 2, 630.1, 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (22, NULL, NULL, NULL, NULL, NULL, 4.1, 2, 640.1, 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (23, NULL, NULL, NULL, NULL, NULL, 5.1, 2, 650.1, 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (24, NULL, NULL, NULL, NULL, NULL, 1.1, 2, 610.1, 2, 'NI', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (25, NULL, NULL, NULL, NULL, NULL, 2.1, 2, 620.1, 2, 'NI', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (26, NULL, NULL, NULL, NULL, NULL, 3.1, 2, 630.1, 2, 'NI', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (27, NULL, NULL, NULL, NULL, NULL, 4.1, 2, 640.1, 2, 'NI', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (28, NULL, NULL, NULL, NULL, NULL, 5.1, 2, 650.1, 2, 'NI', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (29, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (30, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 710.1, 2, 'NI', NULL, NULL, NULL, NULL, NULL, NULL),
  (31, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 720.1, 2, 'NI', NULL, NULL, NULL, NULL, NULL, NULL),
  (32, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 730.1, 2, 'NI', NULL, NULL, NULL, NULL, NULL, NULL),
  (33, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 740.1, 2, 'NI', NULL, NULL, NULL, NULL, NULL, NULL),
  (34, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 750.1, 2, 'NI', NULL, NULL, NULL, NULL, NULL, NULL),
  (35, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 11.1, 2, 'NI', 100.1, 2, 'NI', NULL, NULL, NULL),
  (36, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 12.1, 2, 'NI', 100.1, 2, 'NI', NULL, NULL, NULL),
  (37, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 13.1, 2, 'NI', 100.1, 2, 'NI', NULL, NULL, NULL),
  (38, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 14.1, 2, 'NI', 100.1, 2, 'NI', NULL, NULL, NULL),
  (39, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 15.1, 2, 'NI', 100.1, 2, 'NI', NULL, NULL, NULL),
  (40, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 210.1, 2, 'NI', 100.1, 2, 'NI', 5, 2, 'NI'),
  (41, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 220.1, 2, 'NI', 100.1, 2, 'NI', 5, 2, 'NI'),
  (42, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 230.1, 2, 'NI', 100.1, 2, 'NI', 5, 2, 'NI'),
  (43, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 240.1, 2, 'NI', 100.1, 2, 'NI', 5, 2, 'NI'),
  (44, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 250.1, 2, 'NI', 100.1, 2, 'NI', 5, 2, 'NI');

INSERT INTO ivl_real(id, value1_low_value, value1_high_value, value2_high_value, value2_low_closed, value3_any_value, value3_high_value, value3_high_closed, value3_low_value, value3_low_closed, value3_width_value, value3_width_null_flavor, value3_null_flavor)
VALUES (1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (2, 10.1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (3, 1, 210.2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (4, NULL, 220.2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (5, 3, 230.2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (6, 230.2, 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (7, NULL, NULL, -310, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (8, NULL, NULL, 40, '1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (9, NULL, NULL, NULL, '1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (10, NULL, NULL, 4222222, '1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (11, NULL, NULL, 43, '0', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (12, NULL, NULL, 44, '0', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (13, NULL, NULL, NULL, NULL, NULL, 510, '1', 2, '1', 44, NULL, NULL),
  (14, NULL, NULL, NULL, NULL, NULL, 520, '1', 2, '1', 44, NULL, NULL),
  (15, NULL, NULL, NULL, NULL, NULL, 530, '1', 2, '1', NULL, 'NI', NULL),
  (16, NULL, NULL, NULL, NULL, NULL, 540, '1', 2, '1', 44, NULL, NULL),
  (17, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'NI'),
  (18, NULL, NULL, NULL, NULL, NULL, 610, '1', 251, '1', 4, NULL, NULL),
  (19, NULL, NULL, NULL, NULL, NULL, 620, '1', 252, '1', 4, NULL, NULL),
  (20, NULL, NULL, NULL, NULL, NULL, 630, '1', 253, '1', 4, NULL, NULL),
  (21, NULL, NULL, NULL, NULL, NULL, 640, '1', 254, '1', 4, NULL, NULL),
  (22, NULL, NULL, NULL, NULL, 101, 650, '1', 255, '1', 4, NULL, NULL);

INSERT INTO ivl_ts(id, value1_low_value, value1_high_value, value2_high_value, value2_low_closed, value3_high_value, value3_low_value, value3_width_null_flavor, value3_width_value, value3_null_flavor)
VALUES (1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (2, '2010-03-11 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (3, '2010-03-12 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (4, NULL, '2010-03-11 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (5, NULL, '2000-03-12 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (6, '2010-03-03 00:00:00', '2010-03-13 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (7, NULL, NULL, '2010-03-11 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL),
  (8, NULL, NULL, '2001-03-11 00:00:00', '1', NULL, NULL, NULL, NULL, NULL),
  (9, NULL, NULL, NULL, '1', NULL, NULL, NULL, NULL, NULL),
  (10, NULL, NULL, '2010-03-13 00:00:00', '0', NULL, NULL, NULL, NULL, NULL),
  (11, NULL, NULL, '2010-03-14 00:00:00', '1', NULL, NULL, NULL, NULL, NULL),
  (12, NULL, NULL, '2010-03-15 00:00:00', '1', NULL, NULL, NULL, NULL, NULL),
  (13, NULL, NULL, NULL, NULL, '2010-03-11 00:00:00', '2010-03-01 00:00:00', NULL, 4, NULL),
  (14, NULL, NULL, NULL, NULL, '2010-03-12 00:00:00', '2010-03-02 00:00:00', NULL, 4, NULL),
  (15, NULL, NULL, NULL, NULL, '2010-03-13 00:00:00', '2010-03-03 00:00:00', NULL, 4, NULL),
  (16, NULL, NULL, NULL, NULL, '2010-03-14 00:00:00', '2010-03-04 00:00:00', 'NI', NULL, NULL),
  (17, NULL, NULL, NULL, NULL, '2010-03-15 00:00:00', '2010-03-05 00:00:00', 'NI', NULL, NULL),
  (18, NULL, NULL, NULL, NULL, NULL, '2010-03-01 00:00:00', NULL, 4, NULL),
  (19, NULL, NULL, NULL, NULL, '2010-03-22 00:00:00', NULL, 'NI', NULL, NULL),
  (20, NULL, NULL, NULL, NULL, NULL, NULL, 'NA', NULL, 'NA'),
  (21, NULL, NULL, NULL, NULL, '2010-03-24 00:00:00', '2010-03-04 00:00:00', NULL, 4, NULL);

INSERT INTO pqv_datatype(id, value1_value, value2_null_flavor, value2_value, value2_precision, value3_value, value3_precision, value4_null_flavor, value4_precision, value4_value)
VALUES (1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (4, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (5, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (6, 1.11, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (7, 2.11, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (8, 3.11, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (9, 4.11, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (10, 5.11, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (11, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (12, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (13, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (14, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (15, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (16, NULL, NULL, 1.22, NULL, NULL, NULL, NULL, NULL, NULL),
  (17, NULL, NULL, 2.22, NULL, NULL, NULL, NULL, NULL, NULL),
  (18, NULL, NULL, 3.22, NULL, NULL, NULL, NULL, NULL, NULL),
  (19, NULL, NULL, 4.22, NULL, NULL, NULL, NULL, NULL, NULL),
  (20, NULL, NULL, 5.22, NULL, NULL, NULL, NULL, NULL, NULL),
  (21, NULL, NULL, 1.23, 2, NULL, NULL, NULL, NULL, NULL),
  (22, NULL, NULL, 2.23, 2, NULL, NULL, NULL, NULL, NULL),
  (23, NULL, NULL, 3.23, 2, NULL, NULL, NULL, NULL, NULL),
  (24, NULL, NULL, 4.23, 2, NULL, NULL, NULL, NULL, NULL),
  (25, NULL, NULL, 5.23, 2, NULL, NULL, NULL, NULL, NULL),
  (26, NULL, 'NI', 1.23, 2, NULL, NULL, NULL, NULL, NULL),
  (27, NULL, 'NI', 2.23, 2, NULL, NULL, NULL, NULL, NULL),
  (28, NULL, 'NI', 3.23, 2, NULL, NULL, NULL, NULL, NULL),
  (29, NULL, 'NI', 4.23, 2, NULL, NULL, NULL, NULL, NULL),
  (30, NULL, 'NI', 5.23, 2, NULL, NULL, NULL, NULL, NULL);

INSERT INTO pq_datatype(id, value1_value, value1_unit, value2_value, value3_null_flavor, value3_unit, value3_value, value3_precision)
VALUES (1, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (2, 1.12, NULL, NULL, NULL, NULL, NULL, NULL),
  (3, 2.12, NULL, NULL, NULL, NULL, NULL, NULL),
  (4, 3.12, NULL, NULL, NULL, NULL, NULL, NULL),
  (5, 4.12, NULL, NULL, NULL, NULL, NULL, NULL),
  (6, 5.12, NULL, NULL, NULL, NULL, NULL, NULL),
  (7, 1.12, 'GALLON', NULL, NULL, NULL, NULL, NULL),
  (8, 2.12, 'GALLON', NULL, NULL, NULL, NULL, NULL),
  (9, 3.12, 'GALLON', NULL, NULL, NULL, NULL, NULL),
  (10, 4.12, 'GALLON', NULL, NULL, NULL, NULL, NULL),
  (11, 5.12, 'GALLON', NULL, NULL, NULL, NULL, NULL),
  (12, NULL, NULL, 1.23, NULL, NULL, NULL, NULL),
  (13, NULL, NULL, 2.23, NULL, NULL, NULL, NULL),
  (14, NULL, NULL, 3.23, NULL, NULL, NULL, NULL),
  (15, NULL, NULL, 4.23, NULL, NULL, NULL, NULL),
  (16, NULL, NULL, 5.23, NULL, NULL, NULL, NULL),
  (17, NULL, NULL, NULL, NULL, NULL, 1.34, NULL),
  (18, NULL, NULL, NULL, NULL, NULL, 2.34, NULL),
  (19, NULL, NULL, NULL, NULL, 'LITER', 1.37, NULL),
  (20, NULL, NULL, NULL, NULL, 'LITER', 2.37, NULL),
  (21, NULL, NULL, NULL, NULL, 'LITER', 3.37, NULL),
  (22, NULL, NULL, NULL, NULL, 'LITER', 1.38, 2),
  (23, NULL, NULL, NULL, NULL, 'LITER', 2.38, 2),
  (24, NULL, NULL, NULL, NULL, 'LITER', 3.38, 2),
  (25, NULL, NULL, NULL, 'NA', NULL, NULL, NULL),
  (26, NULL, NULL, NULL, 'NA', NULL, NULL, NULL),
  (27, NULL, NULL, NULL, 'NA', NULL, NULL, NULL);

INSERT INTO real_datatype(id, value1_value, value2_null_flavor, value2_value)
VALUES (1, NULL, NULL, NULL),
  (2, 1001.15, NULL, NULL),
  (3, 1002.15, NULL, NULL),
  (4, -1003, NULL, NULL),
  (5, 1004, NULL, NULL),
  (6, 1005.15, NULL, NULL),
  (7, NULL, NULL, 1101.15),
  (8, NULL, NULL, 1102),
  (9, NULL, NULL, -1201.15),
  (10, NULL, 'NA', NULL),
  (11, NULL, 'NA', NULL),
  (12, NULL, 'NA', NULL);

INSERT INTO sc_datatype(id, value1_value, value1_code_code, value2_null_flavor, value2_value, value2_code_null_flavor, value2_code_code, value2_code_code_system, value2_code_code_system_name, value2_code_code_system_ver, value3_null_flavor, value3_value, value3_code_null_flavor, value3_code_code, value3_code_code_system, value3_code_code_system_name, value3_code_code_system_ver, value3_code_display_nflavor, value3_code_display_value, value3_code_orig_txt_nflavor, value3_code_orig_txt_desc, value3_code_orig_txt_value)
VALUES (1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (2, 'VALUE1_VALUE1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (3, 'VALUE1_VALUE2', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (4, 'VALUE1_VALUE3', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (5, 'VALUE1_VALUE4', 'CODE1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (6, 'VALUE1_VALUE5', 'CODE2', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (7, NULL, NULL, 'UNK', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (8, NULL, NULL, NULL, 'VALUE2_VALUE1', NULL, 'VALUE2_CODE_CODE1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (9, NULL, NULL, NULL, 'VALUE2_VALUE2', NULL, 'VALUE2_CODE_CODE2', 'VALUE2_CODE_CODE_SYSTEM1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (10, NULL, NULL, NULL, 'VALUE2_VALUE3', NULL, 'VALUE2_CODE_CODE3', 'VALUE2_CODE_CODE_SYSTEM2', 'VALUE2_CODE_CODE_SYSTEM_NAME1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (11, NULL, NULL, NULL, 'VALUE2_VALUE4', NULL, 'VALUE2_CODE_CODE4', 'VALUE2_CODE_CODE_SYSTEM3', 'VALUE2_CODE_CODE_SYSTEM_NAME2', '1.1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (12, NULL, NULL, NULL, 'VALUE2_VALUE5', 'NA', 'VALUE2_CODE_CODE5', 'VALUE2_CODE_CODE_SYSTEM4', 'VALUE2_CODE_CODE_SYSTEM_NAME3', '1.1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (13, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'NA', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (14, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'VALUE3_VALUE1', NULL, 'VALUE3_CODE_CODE1', 'VALUE3_CODE_CODE_SYSTEM1', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
  (15, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'VALUE3_VALUE2', NULL, 'VALUE3_CODE_CODE1', 'VALUE3_CODE_CODE_SYSTEM1', 'VALUE3_CODE_CODE_SYSTEM_NAME1', 'VALUE3_CODE_CODE_SYSTEM_VER1', NULL, NULL, NULL, NULL, NULL),
  (16, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'VALUE3_VALUE1', NULL, 'VALUE3_CODE_CODE1', 'VALUE3_CODE_CODE_SYSTEM1', 'VALUE3_CODE_CODE_SYSTEM_NAME1', 'VALUE3_CODE_CODE_SYSTEM_VER1', 'NI', NULL, 'NI', NULL, NULL),
  (17, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'VALUE3_VALUE3', NULL, 'VALUE3_CODE_CODE1', 'VALUE3_CODE_CODE_SYSTEM1', 'VALUE3_CODE_CODE_SYSTEM_NAME1', 'VALUE3_CODE_CODE_SYSTEM_VER', NULL, 'VALUE3_CODE_DISPLAY_VALUE', 'NI', NULL, NULL),
  (18, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'VALUE3_VALUE4', NULL, 'VALUE3_CODE_CODE31', 'VALUE3_CODE_CODE_SYSTEM31', 'VALUE3_CODE_CODE_SYSTEM_NAME31', 'VALUE3_CODE_CODE_SYSTEM_VER31', NULL, 'VALUE3_CODE_DISPLAY_VALUE31', 'NI', NULL, NULL),
  (19, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'VALUE3_VALUE5', NULL, 'VALUE3_CODE_CODE31', 'VALUE3_CODE_CODE_SYSTEM31', 'VALUE3_CODE_CODE_SYSTEM_NAME31', 'VALUE3_CODE_CODE_SYSTEM_VER31', NULL, 'VALUE3_CODE_DISPLAY_VALUE31', 'NI', NULL, NULL),
  (20, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'VALUE3_VALUE6', NULL, 'VALUE3_CODE_CODE31', 'VALUE3_CODE_CODE_SYSTEM31', 'VALUE3_CODE_CODE_SYSTEM_NAME31', 'VALUE3_CODE_CODE_SYSTEM_VER31', NULL, 'VALUE3_CODE_DISPLAY_VALUE31', 'NI', NULL, NULL),
  (21, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'VALUE3_VALUE7', NULL, 'VALUE3_CODE_CODE31', 'VALUE3_CODE_CODE_SYSTEM31', 'VALUE3_CODE_CODE_SYSTEM_NAME31', 'VALUE3_CODE_CODE_SYSTEM_VER31', NULL, 'VALUE3_CODE_DISPLAY_VALUE31', NULL, 'VALUE3_CODE_ORIG_TXT_DESC1', NULL),
  (22, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'VALUE3_VALUE8', NULL, 'VALUE3_CODE_CODE31', 'VALUE3_CODE_CODE_SYSTEM31', 'VALUE3_CODE_CODE_SYSTEM_NAME31', 'VALUE3_CODE_CODE_SYSTEM_VER31', NULL, 'VALUE3_CODE_DISPLAY_VALUE31', NULL, 'VALUE3_CODE_ORIG_TXT_DESC31', 'VALUE3_CODE_ORIG_TXT_VALUE31'),
  (23, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'VALUE3_VALUE9', 'NI', 'VALUE3_CODE_CODE31', 'VALUE3_CODE_CODE_SYSTEM31', 'VALUE3_CODE_CODE_SYSTEM_NAME31', 'VALUE3_CODE_CODE_SYSTEM_VER31', NULL, 'VALUE3_CODE_DISPLAY_VALUE31', NULL, 'VALUE3_CODE_ORIG_TXT_DESC31', 'VALUE3_CODE_ORIG_TXT_VALUE31');

INSERT INTO st_datatype(id, value1_value, value2_null_flavor, value2_value)
VALUES (1, NULL, NULL, NULL),
  (2, 'VALUE1_VALUE1', NULL, NULL),
  (3, 'VALUE1_VALUE2', NULL, NULL),
  (4, 'VALUE1_VALUE3', NULL, NULL),
  (5, 'VALUE1_VALUE4', NULL, NULL),
  (6, 'VALUE1_VALUE5', NULL, NULL),
  (7, NULL, NULL, 'VALUE2_VALUE1'),
  (8, NULL, NULL, 'VALUE2_VALUE2'),
  (9, NULL, NULL, 'VALUE2_VALUE3'),
  (10, NULL, NULL, 'VALUE2_VALUE4'),
  (11, NULL, NULL, 'VALUE2_VALUE5'),
  (12, NULL, 'UNK', NULL),
  (13, NULL, 'UNK', NULL),
  (14, NULL, 'UNK', NULL),
  (15, NULL, 'UNK', NULL),
  (16, NULL, 'UNK', NULL);

INSERT INTO st_nt_datatype(id, value1_value, value2_null_flavor, value2_value)
VALUES (1, NULL, NULL, NULL),
  (2, 'VALUE1_VALUE1', NULL, NULL),
  (3, 'VALUE1_VALUE2', NULL, NULL),
  (4, 'VALUE1_VALUE3', NULL, NULL),
  (5, 'VALUE1_VALUE4', NULL, NULL),
  (6, 'VALUE1_VALUE5', NULL, NULL),
  (7, NULL, NULL, 'VALUE2_VALUE1'),
  (8, NULL, NULL, 'VALUE2_VALUE2'),
  (9, NULL, NULL, 'VALUE2_VALUE3'),
  (10, NULL, NULL, 'VALUE2_VALUE4'),
  (11, NULL, NULL, 'VALUE2_VALUE5'),
  (12, NULL, 'UNK', NULL),
  (13, NULL, 'UNK', NULL),
  (14, NULL, 'UNK', NULL),
  (15, NULL, 'UNK', NULL),
  (16, NULL, 'UNK', NULL);

INSERT INTO tel_datatype(id, value1_value, value2_null_flavor, value2_value)
VALUES (1, NULL, NULL, NULL),
  (2, 'tel://123-456-7891', NULL, NULL),
  (3, 'tel://123-456-7892', NULL, NULL),
  (4, 'tel://123-456-7893', NULL, NULL),
  (5, 'tel://123-456-7894', NULL, NULL),
  (6, 'tel://123-456-7895', NULL, NULL),
  (7, NULL, NULL, 'tel://123-456-7896'),
  (8, NULL, NULL, 'tel://123-456-7897'),
  (9, NULL, NULL, 'tel://123-456-7898'),
  (10, NULL, NULL, 'tel://123-456-7893'),
  (11, NULL, NULL, 'tel://123-456-7894'),
  (12, NULL, NULL, 'tel://123-456-7895');

INSERT INTO tel_email_datatype(id, value1_value, value2_null_flavor, value2_value)
VALUES (1, NULL, NULL, NULL),
  (2, 'mailto:jdoe1@nci.gov', NULL, NULL),
  (3, 'mailto:jdoe2@nci.gov', NULL, NULL),
  (4, 'mailto:jdoe3@nci.gov', NULL, NULL),
  (5, 'mailto:jdoe4@nci.gov', NULL, NULL),
  (6, 'mailto:jdoe5@nci.gov', NULL, NULL),
  (7, NULL, NULL, 'MailTo:jdoe1@nci.gov'),
  (8, NULL, NULL, 'MailTo:jdoe2@nci.gov'),
  (9, NULL, NULL, 'mailto:jdoe3@nci.gov'),
  (10, NULL, NULL, 'mailto:jdoe4@nci.gov'),
  (11, NULL, NULL, 'mailto:jdoe5@nci.gov');

INSERT INTO tel_person_datatype(id, value1_value, value2_null_flavor, value2_value)
VALUES (1, NULL, NULL, NULL),
  (2, 'tel:8004226231', NULL, NULL),
  (3, 'x-text-fax:8004226232', NULL, NULL),
  (4, 'x-text-tel:8004226233', NULL, NULL),
  (5, 'mailto:8004226235', NULL, NULL),
  (6, 'x-text-tel:8004226235', NULL, NULL),
  (7, NULL, NULL, 'x-text-tel:8004226234'),
  (8, NULL, NULL, 'mailto:8004226235'),
  (9, NULL, NULL, 'x-text-tel:8004226233'),
  (10, NULL, NULL, 'mailto:8004226235'),
  (11, NULL, NULL, 'x-text-tel:8004226235');

INSERT INTO tel_phone_datatype(id, value1_value, value2_null_flavor, value2_value)
VALUES (1, NULL, NULL, NULL),
  (2, 'tel:8004226231', NULL, NULL),
  (3, 'x-text-tel:8004226232', NULL, NULL),
  (4, 'x-text-fax:8004226233', NULL, NULL),
  (5, 'tel:8004226234', NULL, NULL),
  (6, 'tel:8004226235', NULL, NULL),
  (7, NULL, NULL, 'tel:8004226231'),
  (8, NULL, NULL, 'x-text-tel:8004226232'),
  (9, NULL, NULL, 'x-text-fax:8004226233'),
  (10, NULL, NULL, 'tel:8004226234'),
  (11, NULL, NULL, 'tel:8004226235');

INSERT INTO tel_url_datatype(id, value1_value, value2_null_flavor, value2_value)
VALUES (1, NULL, NULL, NULL),
  (2, 'https://www.cancer.gov', NULL, NULL),
  (3, 'file://test.xml', NULL, NULL),
  (4, 'ftp://cancer.gov', NULL, NULL),
  (5, 'cid://www.cancer3.gov', NULL, NULL),
  (6, 'http://www.cancer4.gov', NULL, NULL),
  (7, NULL, NULL, 'https://www.cancer.gov'),
  (8, NULL, NULL, 'nfs://d/test.xml'),
  (9, NULL, NULL, 'cid://www.cancer3.gov'),
  (10, NULL, NULL, 'ftp://cancer.gov'),
  (11, NULL, NULL, 'http://www.cancer4.gov');

INSERT INTO ts_datatype(id, value1_value, value2_null_flavor, value2_value)
VALUES (1, NULL, NULL, NULL),
  (2, '2010-03-11 00:00:00', NULL, NULL),
  (3, '2010-03-12 00:00:00', NULL, NULL),
  (4, '2010-03-13 00:00:00', NULL, NULL),
  (5, '2010-03-14 00:00:00', NULL, NULL),
  (6, '2010-03-15 00:00:00', NULL, NULL),
  (7, NULL, NULL, '2010-03-21 00:00:00'),
  (8, NULL, NULL, '2010-03-22 00:00:00'),
  (9, NULL, NULL, '2010-03-23 00:00:00'),
  (10, NULL, 'NA', NULL),
  (11, NULL, 'NA', NULL),
  (12, NULL, 'NA', NULL);

-- Re-enable foreign key checks
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;

-- End of script
