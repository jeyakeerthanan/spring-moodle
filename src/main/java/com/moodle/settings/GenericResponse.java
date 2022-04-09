/*
Copyright [2020] [Apptimus Tech (Pvt) Ltd.]

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
*/

/**
 * 
 */
package com.moodle.settings;

import java.util.StringJoiner;

/**
 * @author Derushan Sep 16, 2020
 */
public class GenericResponse {
	private boolean isSuccess;
	private String msg;
	private String status;
	private Integer statusCode;
	private Object error;
	private Object data;

	/**
	 * @return the isSuccess
	 */
	public boolean isSuccess() {
		return isSuccess;
	}

	/**
	 * @param isSuccess the isSuccess to set
	 */
	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}

	/**
	 * @return the msg
	 */
	public String getMsg() {
		return msg;
	}

	/**
	 * @param msg the msg to set
	 */
	public void setMsg(String msg) {
		this.msg = msg;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the statusCode
	 */
	public Integer getStatusCode() {
		return statusCode;
	}

	/**
	 * @param statusCode the statusCode to set
	 */
	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}

	/**
	 * @return the error
	 */
	public Object getError() {
		return error;
	}

	/**
	 * @param error the error to set
	 */
	public void setError(Object error) {
		this.error = error;
	}

	/**
	 * @return the data
	 */
	public Object getData() {
		return data;
	}

	/**
	 * @param data the data to set
	 */
	public void setData(Object data) {
		this.data = data;
	}

	public static GenericResponseBuilder builder() {
		return new GenericResponseBuilder();
	}

	public String toJson() {
		return new StringJoiner(", ", "{", "}")
				.add("\"msg\": \"" + msg + "\"")
				.add("\"statusCode\": " + statusCode)
				.add("\"isSuccess\": " + isSuccess)
				.add("\"status\": \"" + status + "\"")
				.add("\"error\": " + error)
				.add("\"data\": " + data)
				.toString();
	}

}
