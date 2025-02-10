create database if not exists chuyen_doi_erd;
use chuyen_doi_erd;

create table phieu_xuat (
    ma_phieu int primary key,
    ngay_xuat date
);

create table vat_tu (
    ma_vat_tu int primary key,
    ten_vat_tu varchar(50)
);

create table phieu_nhap (
    ma_phieu int primary key,
    ngay_nhap date
);

create table don_dat_hang (
    ma_don_hang int primary key,
    ngay_dat_hang date
);

create table nha_cung_cap (
    ma_nha_cung_cap int primary key,
    ten_nha_cung_cap varchar(50),
    dia_chi varchar(50)
);

create table so_dien_thoai_nha_cung_cap (
    id int auto_increment primary key,
    ma_nha_cung_cap int,
    so_dien_thoai varchar(20),
    foreign key (ma_nha_cung_cap) references nha_cung_cap(ma_nha_cung_cap)
);

create table chi_tiet_phieu_xuat (
    id int auto_increment primary key,
    ma_phieu int,
    ma_vat_tu int,
    don_gia_xuat decimal(10, 2),
    so_luong_xuat int,
    foreign key (ma_phieu) references phieu_xuat(ma_phieu),
    foreign key (ma_vat_tu) references vat_tu(ma_vat_tu)
);

create table chi_tiet_phieu_nhap (
    id int auto_increment primary key,
    ma_vat_tu int,
    ma_phieu int,
    don_gia_nhap decimal(10, 2),
    so_luong_nhap int,
    foreign key (ma_phieu) references phieu_nhap(ma_phieu),
    foreign key (ma_vat_tu) references vat_tu(ma_vat_tu)
);

create table chi_tiet_don_dat_hang (
    id int auto_increment primary key,
    ma_vat_tu int,
    ma_don_hang int,
    foreign key (ma_don_hang) references don_dat_hang(ma_don_hang),
    foreign key (ma_vat_tu) references vat_tu(ma_vat_tu)
);

create table cung_cap (
    id int auto_increment primary key,
    ma_don_hang int,
    ma_nha_cung_cap int,
    foreign key (ma_don_hang) references don_dat_hang(ma_don_hang),
    foreign key (ma_nha_cung_cap) references nha_cung_cap(ma_nha_cung_cap)
);

