INSERT INTO notification_type_config (type, rate_limiting_window, max_requests_per_window)
VALUES ('news', 5, 1),
       ('status', 5, 2),
       ('marketing', 5, 3)
ON CONFLICT DO NOTHING;