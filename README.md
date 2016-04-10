# OrderManagerPico
a simple order manager for a specific usage

Server interface:

%%登录%%

发送：
data={
	user_name="zhangsan"(账号)
	password="asd66661sdwda13"(md5加密后结果，32位)
}

收到：
data={
	result=0 (0成功，1失败)
	error=""(错误提示字符串，若成功则该字段为空)
	token="asdf165dfa65sd"(登录token，用于保持登录状态)
	use_id=1321
}


%%注册%%

发送：
data={
	user_name=""(登录名)
	name=""(姓名)
	password=""(密码，md5加密后结果)
	authority=0(0管理员，1业务员，2收款员)
	telephone=13856486524
	information=""(相关信息)
}

收到：
result={
	result=0 (0成功，1失败)
	error=""(错误提示字符串，若成功则该字段为空)
}


%%订单提交%%


发送：
data={
	user_id=1321
	token="asdf165dfa65sd"
	product_name=""(产品名)
	custom_name=""(客户名)
	carriage_num=12(车数)
	note=""(相关描述)
}


收到：
result={

	result=0 (0成功，1失败)
	error=""(错误提示字符串，若成功则该字段为空)

	data={
		product_id=35(产品id)
		product_name=""(产品名)
		custom_name=""(客户名)
		manager_name=""(业务员名)
		carriage_num=12(车数)
		date="2016-4-3"(时间)
		state=0(状态：0已创建未发货，1已发货未确认，
			2已确认收货未付款，3已付款未审核，4已审核订单结束)
		note=""(备注信息)
	}


}
#总价格需要在最后计算，创建时不给出


%%订单删除%%

发送：
data={
	user_id=1321
	token="asdf165dfa65sd"
	product_id=35(产品id)
}

收到：
result={

	result=0 (0成功，1失败)
	error=""(错误提示字符串，若成功则该字段为空)
}
#只能在刚创建后删除，若发货后无法删除


%%订单查询%%

发送：
data={
	user_id=1321
	token="asdf165dfa65sd"
}


收到：
result={

	result=0 (0成功，1失败)
	error=""(错误提示字符串，若成功则该字段为空)
	data=[
	{
		product_id=35(产品id)
		product_name=""(产品名)
		custom_name=""(客户名)
		manager_name=""(业务员名)
		carriage_num=12(车数)
		date="2016-4-3"(时间)
		state=0(状态：0已创建未发货，1已发货未确认，
			2已确认收货未付款，3未全部付款，4已付款未审核，5已审核订单结束)
		note=""(备注信息)
		price_sum=56165(总价格)
	},
	{

	}
	...](列表)
}

%%创建发货表%%
#每个产品按每车创建发货单
发送：
data={
	user_id=1321
	token="asdf165dfa65sd"
	data={
		product_id=1(产品id)
		product_name=""(产品名)
		manager_id=""(业务员id)
		manager_name=""(业务员名)
		price=20(单价/吨)
		custom_name=""(客户名)
		tonnage=30(吨位)
		price_sum=50(单车总价)
		plate number=""(车牌号)
		note=""(备注)
	}

}


收到：
result={
	result=0 (0成功，1失败)
	error=""(错误提示字符串，若成功则该字段为空)
	data={
		delivery_id=56(发货id)
	}
}






%%查询发货表%%

发送：
data={
	user_id=1321
	token="asdf165dfa65sd"
	product_id=1(产品id)
}


收到：
result={
	result=0 (0成功，1失败)
	error=""(错误提示字符串，若成功则该字段为空)
	data=[
	{
		delivery_id=2(发货id)
		product_id=1(产品id)
		product_name=""(产品名)
		manager_id=""(业务员id)
		manager_name=""(业务员名)
		price=20(单价/吨)
		custom_name=""(客户名)
		tonnage=30(吨位)
		price_sum=50(单车总价)
		plate number=""(车牌号)
		state=0(0未发货，1已发货)
		delivery_date=""
		receive_date=""
		note=""(备注)
	},{},{}....]  (类型：列表)
}



%%删除发货表%%
#新创建的发货表可删除，已发货不可删除
发送：
data={
	user_id=1321
	token="asdf165dfa65sd"
	delivery_id=1(发货id)
}


收到：
result={
	result=0 (0成功，1失败)
	error=""(错误提示字符串，若成功则该字段为空)
}





%%发货%%

发送：
data={
	user_id=1321
	token="asdf165dfa65sd"
	product_id=1(产品id表)
}


收到：
result={
	result=0 (0成功，1失败)
	error=""(错误提示字符串，若成功则该字段为空)
}



%%确认收货%%

发送：
data={
	user_id=1321
	token="asdf165dfa65sd"
	product_id=1(产品id表)
}


收到：
result={
	result=0 (0成功，1失败)
	error=""(错误提示字符串，若成功则该字段为空)
}



%%创建付款项%%

发送：
data={
	user_id=1321
	token="asdf165dfa65sd"
	data={
		product_id=1(产品id)
		product_name=""(产品名)
		manager_name=""(业务员名)
		manager_id=""(业务员id)
		money=20(付款金额)
		custom_name=""(客户名)
		tonnage=30(付款吨位)
		bank=""(付款银行)
		pay_person=""(付款人，业务员自由填)
		note=""(备注)
	}

}


收到：
result={
	result=0 (0成功，1失败)
	error=""(错误提示字符串，若成功则该字段为空)
	data={
		pay_id=56(付款id)
		state=0(0未付款，1已付款，2已审核)
	}
}


%%删除付款项%%

发送：
data={
	user_id=1321
	token="asdf165dfa65sd"
	pay_id=1(付款id)
}


收到：
result={
	result=0 (0成功，1失败)
	error=""(错误提示字符串，若成功则该字段为空)

}


%%确认付款%%

发送：
data={
	user_id=1321
	token="asdf165dfa65sd"
	pay_id=[1,2,3](付款id)
}


收到：
result={
	result=0 (0成功，1失败)
	error=""(错误提示字符串，若成功则该字段为空)
}



%%查询付款项%%

发送：
data={
	user_id=1321
	token="asdf165dfa65sd"
	product_id=1(产品id)
}


收到：
result={
	result=0 (0成功，1失败)
	error=""(错误提示字符串，若成功则该字段为空)
	data=[
	{
		pay_id=2(付款id)
		product_id=1(产品id)
		product_name=""(产品名)
		manager_name=""(业务员名)
		manager_id=""(业务员id)
		money=20(付款金额)
		date=""(付款日期)
		tonnage=30(付款吨位)
		pay_person=""(付款人)
		state=0(0未付款，1已付款，2已审核)
		bank=""(付款银行)
		note=""(备注)
	},{},{}....]  (类型：列表)
}


%%审核付款%%
#只有全部付款完成才参与审核
发送：
data={
	user_id=1321
	token="asdf165dfa65sd"
	product_id=1(产品id)
}


收到：
result={
	result=0 (0成功，1失败)
	error=""(错误提示字符串，若成功则该字段为空)
}


%%查询银行表%%
发送：
data={
	user_id=1321
	token="asdf165dfa65sd"
}


收到：
result={
	result=0 (0成功，1失败)
	error=""(错误提示字符串，若成功则该字段为空)
	data=["银行1","银行2","银行3","银行4"](列表)
}



%%查询客户表%%
发送：
data={
	user_id=1321
	token="asdf165dfa65sd"
}


收到：
result={
	result=0 (0成功，1失败)
	error=""(错误提示字符串，若成功则该字段为空)
	data=[
	{
		custom_name=""(客户名)
		custom_id=0(客户id)
		information=""(客户信息)

	}
}

%%查询业务员表%%
发送：
data={
	user_id=1321
	token="asdf165dfa65sd"
}


收到：
result={
	result=0 (0成功，1失败)
	error=""(错误提示字符串，若成功则该字段为空)
	data=[
	{
		name=""(业务员名)
		id=""(业务员id)
		information=""(业务员信息)
		authority=""(权限)
		telephone=""(电话)
	},{},{}....]
}
