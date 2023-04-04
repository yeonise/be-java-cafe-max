package kr.codesqaud.cafe.service;

import kr.codesqaud.cafe.domain.Article;
import kr.codesqaud.cafe.dto.article.ArticleResponse;
import kr.codesqaud.cafe.dto.article.ArticleSaveRequest;
import kr.codesqaud.cafe.dto.article.ArticleUpdateRequest;
import kr.codesqaud.cafe.exception.article.ArticleNotFoundException;
import kr.codesqaud.cafe.repository.ArticleRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArticleService {
    private final ArticleRepository articleRepository;

    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public void saveArticle(ArticleSaveRequest articleSaveRequest) { // 새로운 게시글 저장하기
        articleRepository.save(Article.from(articleSaveRequest));
    }

    public List<ArticleResponse> getAllArticles() { // 모든 게시글 가져오기
        return articleRepository.findAll().stream().map(ArticleResponse::from).collect(Collectors.toUnmodifiableList());
    }

    public ArticleResponse findById(Long id) {
        if (!articleRepository.isExists(id)) { // 게시글 존재 여부 검사
            throw new ArticleNotFoundException();
        }

        return ArticleResponse.from(articleRepository.findById(id));
    }

    public void updateArticle(ArticleUpdateRequest articleUpdateRequest) { // 기존 게시글을 수정하기
        LocalDateTime createdAt = this.findById(articleUpdateRequest.getId()).getCreatedAt(); // 게시글 첫 생성 시간 가져오기

        articleRepository.save(Article.from(articleUpdateRequest, createdAt));
    }
}