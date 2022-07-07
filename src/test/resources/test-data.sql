-- roles
insert into `roles`(id, name) values (1, 'ROLE_ADMIN');
insert into `roles`(id, name) values (2, 'ROLE_USER');
-- users
INSERT INTO `users` (`id`,`account_non_expired`, `account_non_locked`, `create_date_time`, `credentials_non_expired`, `deleted`, `email`, `enabled`, `first_name`, `last_name`, `password`, `photo`, `update_date_time`) VALUES (1 ,true, true, CURRENT_DATE(), true, false, 'test@admin.com', true, 'carlos', 'perez', '$2a$10$Bz9kmnLhJb.xWc78LNymcugT0//TZRzNedtXgnfMWyYPVwuBcNlIG', 'imgcarlos.jpg', CURRENT_DATE());
INSERT INTO `users` (`id`,`account_non_expired`, `account_non_locked`, `create_date_time`, `credentials_non_expired`, `deleted`, `email`, `enabled`, `first_name`, `last_name`, `password`, `photo`, `update_date_time`) VALUES (2 ,true, true, CURRENT_DATE(), true, false, 'test@user.com', true, 'maria', 'vazquez', '$2a$10$npmFpCbBFdtXjGGhgc1ste1quwGaF4MvkgYk9N2rBDFkOrV5Lj6M6', 'imgmaria.jpg', CURRENT_DATE());
--Role User--
INSERT INTO `usuario_roles` (`usuario_id`, `roles_id`) VALUES (1, 1);
INSERT INTO `usuario_roles` (`usuario_id`, `roles_id`) VALUES (2, 2);

INSERT INTO `members` (`id`, `create_date_time`, `deleted`, `description`, `facebook_url`, `image`, `instagram_url`, `linkedin_url`, `name`, `update_date_time`) VALUES ('1', CURRENT_DATE(), false, 'Some content', 'facebook', 'img', 'ista', 'link', 'ale', CURRENT_DATE());
INSERT INTO `categories` (`id`, `create_date_time`, `deleted`, `description`, `image`, `name`, `update_date_time`) VALUES ('1', CURRENT_DATE(), false, 'Some content', 'test.jpg', 'news', CURRENT_DATE());
INSERT INTO `news` (`id`, `content`, `create_date_time`, `image`, `name`, `update_date_time`, `category_id`, `deleted`) VALUES ('1', 'Some content', CURRENT_DATE(), 'img.jpg', 'Test news', CURRENT_DATE(), '1', false);

