from sqlalchemy import create_engine
from sqlalchemy.orm import declarative_base
from sqlalchemy.orm import sessionmaker
Base = declarative_base()
engine = create_engine("mysql+pymysql://root:password@localhost:3307/pos")

Session = sessionmaker(bind=engine)