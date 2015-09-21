package com.dspl.malkey.dao;

import javax.persistence.Persistence;
import javax.persistence.PersistenceException;

import org.springframework.transaction.TransactionSystemException;

public interface MessageDAO {
String getMessage(TransactionSystemException e);
String getMessage(PersistenceException e);
}
