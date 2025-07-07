package org.news.news_aggregation_client.model;

import java.time.LocalDateTime;

    public class Notification {
        private Long id;
        private String title;
        private String content;
        private String source;
        private String url;
        private String categories;  // Comma-separated string from server
        private LocalDateTime notifiedAt;


        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getCategories() {
            return categories;
        }

        public void setCategories(String categories) {
            this.categories = categories;
        }

        public LocalDateTime getNotifiedAt() {
            return notifiedAt;
        }

        public void setNotifiedAt(LocalDateTime notifiedAt) {
            this.notifiedAt = notifiedAt;
        }

}
