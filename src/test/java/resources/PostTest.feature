Feature: Post Testing
  Background:
    Given The Post Service Endpoint "http://localhost:8080/api/v1/posts" is available

  @get-all-posts
  Scenario: Get All Posts
    When A request is made to get all posts
    Then A list of posts with status 201 is received

  @get-post-by-id
  Scenario: Get Post By Id
    When A request is made to get a post with id 1
    Then A post with status 201 is received

  @get-posts-by-employer-id
  Scenario: Get All Posts By Employer Id
    When A request is made to get all posts by employer id 2
    Then A list of posts with status 201 is received

  @create-post
  Scenario: Create a New Post
    When A post creation request is sent for employer id 2 with title "New Post", description "Post Description", subtitle "Post Subtitle", and imgUrl "http://example.com/image.jpg"
    Then A new post with status 201 is received

  @delete-post
  Scenario: Delete Post By Id
    When A delete request is made for a post with id 1
    Then A confirmation with status 201 is received

  @update-post
  Scenario: Update Post By Id
    When An update request is made for a post with id 1 with title "Updated Post", description "Updated Description", subtitle "Updated Subtitle", and imgUrl "http://example.com/updated_image.jpg"
    Then An updated post with status 201 is received
