package com.backend.test.entities;

import java.util.List;

public class ErrorMappingJSON {
	private String name;
	private String description;
	private List<ErrorDetail> errorList;
	
	public static class ErrorDetail{
		private String code;
		private String codeMapping;
		private String dspMessage;
		private String messageDetail;
		public String getCode() {
			return code;
		}
		public void setCode(String code) {
			this.code = code;
		}
		public String getCodeMapping() {
			return codeMapping;
		}
		public void setCodeMapping(String codeMapping) {
			this.codeMapping = codeMapping;
		}
		public String getDspMessage() {
			return dspMessage;
		}
		public void setDspMessage(String dspMessage) {
			this.dspMessage = dspMessage;
		}
		public String getMessageDetail() {
			return messageDetail;
		}
		public void setMessageDetail(String messageDetail) {
			this.messageDetail = messageDetail;
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<ErrorDetail> getErrorList() {
		return errorList;
	}

	public void setErrorList(List<ErrorDetail> errorList) {
		this.errorList = errorList;
	}
	
}
