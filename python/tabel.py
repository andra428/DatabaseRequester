from sqlalchemy import Column, String, Integer
from base import Base

class User(Base):
    __tablename__ = 'users'

    uid = Column(Integer, primary_key=True)
    username = Column(String)
    password = Column(String)
    role = Column(String)

    def __init__(self, username, password,role):
        self.username = username
        self.password = password
        self.role = role
