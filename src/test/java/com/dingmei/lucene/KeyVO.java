package com.dingmei.lucene;
class KeyVO{
		public String id;
		public String name;
		public String url;
		public String[] keywords;
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getUrl() {
			return url;
		}
		public void setUrl(String url) {
			this.url = url;
		}
		public String[] getKeywords() {
			return keywords;
		}
		public void setKeywords(String[] keywords) {
			this.keywords = keywords;
		}
	}