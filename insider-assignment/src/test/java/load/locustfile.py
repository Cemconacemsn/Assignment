"""
Locust load tests for the sample API (JSONPlaceholder).

Run from insider-assignment:
  pip install locust
  locust -f src/test/java/load/locustfile.py

Override host:
  LOCUST_HOST=https://jsonplaceholder.typicode.com locust -f src/test/java/load/locustfile.py
"""

import os

from locust import HttpUser, between, task


class JsonPlaceholderUser(HttpUser):
  host = os.getenv("LOCUST_HOST", "https://jsonplaceholder.typicode.com")
  wait_time = between(1, 3)

  @task(3)
  def list_posts(self):
    self.client.get("/posts", name="GET /posts")

  @task(2)
  def get_post_by_id(self):
    self.client.get("/posts/1", name="GET /posts/{id}")

  @task(1)
  def list_users(self):
    self.client.get("/users", name="GET /users")
