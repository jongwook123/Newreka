package com.d103.newreka.hottopic.repo;

import java.util.List;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.d103.newreka.hottopic.domain.NewsDocument;

public interface UserSearchRepository extends ElasticsearchRepository<NewsDocument, String> {
}