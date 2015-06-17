#!/bin/bash

cd target

cust_karaf_tar=$(ls | grep tar)

tar -zxvf ${cust_karaf_tar}

cust_karaf_dir=${cust_karaf_tar%.tar*}

cd $cust_karaf_dir
cd bin
ls
