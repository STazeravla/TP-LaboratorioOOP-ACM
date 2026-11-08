-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema tp_final_26
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `tp_final_26`;
CREATE SCHEMA IF NOT EXISTS `tp_final_26` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci;
USE `tp_final_26`;

-- -----------------------------------------------------
-- Table `tp_final_26`.`comunas`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `tp_final_26`.`comunas`;
CREATE TABLE IF NOT EXISTS `tp_final_26`.`comunas` (
  `id_comuna` INT NOT NULL AUTO_INCREMENT,
  `numero` VARCHAR(45) NOT NULL,
  `sede` VARCHAR(45) NOT NULL,
  `junta` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id_comuna`)
) ENGINE = InnoDB DEFAULT CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci;

-- -----------------------------------------------------
-- Table `tp_final_26`.`barrios`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `tp_final_26`.`barrios`;
CREATE TABLE IF NOT EXISTS `tp_final_26`.`barrios` (
  `id_barrio` INT NOT NULL AUTO_INCREMENT,
  `tipo` VARCHAR(45) NOT NULL,
  `nombre` VARCHAR(45) NOT NULL,
  `hectareas` INT NOT NULL,
  `id_comuna` INT NOT NULL,
  `cantidad_ph` INT NULL DEFAULT NULL,
  `porcetaje_asfalto` FLOAT NULL DEFAULT NULL,
  `numero_ley` VARCHAR(45) NULL DEFAULT NULL,
  `agua_potable` BIT(1) NULL DEFAULT NULL,
  `cantidad_monoblocks` INT NULL DEFAULT NULL,
  `consorcio` BIT(1) NULL DEFAULT NULL,
  PRIMARY KEY (`id_barrio`),
  UNIQUE INDEX `nombre_UNIQUE` (`nombre` ASC) VISIBLE,
  INDEX `fk_comuna_idx` (`id_comuna` ASC) VISIBLE,
  CONSTRAINT `fk_comuna`
    FOREIGN KEY (`id_comuna`)
    REFERENCES `tp_final_26`.`comunas` (`id_comuna`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE
) ENGINE = InnoDB DEFAULT CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci;

-- -----------------------------------------------------
-- Table `tp_final_26`.`situaciones_laborales`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `tp_final_26`.`situaciones_laborales`;
CREATE TABLE IF NOT EXISTS `tp_final_26`.`situaciones_laborales` (
  `id_situacion_laboral` INT NOT NULL,
  `nombre` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id_situacion_laboral`),
  UNIQUE INDEX `id_situacion_laboral_UNIQUE` (`id_situacion_laboral` ASC) VISIBLE
) ENGINE = InnoDB DEFAULT CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci;

-- -----------------------------------------------------
-- Table `tp_final_26`.`personas`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `tp_final_26`.`personas`;
CREATE TABLE IF NOT EXISTS `tp_final_26`.`personas` (
  `id_persona` INT NOT NULL AUTO_INCREMENT,
  `dni` VARCHAR(45) NOT NULL,
  `nombre` VARCHAR(45) NOT NULL,
  `nacimiento` DATE NOT NULL,
  `id_situacion_laboral` INT NOT NULL,
  `id_barrio` INT NOT NULL,
  PRIMARY KEY (`id_persona`),
  UNIQUE INDEX `dni_UNIQUE` (`dni` ASC) VISIBLE,
  INDEX `fk_situacion_laboral_idx` (`id_situacion_laboral` ASC) VISIBLE,
  INDEX `fk_barrio_idx` (`id_barrio` ASC) VISIBLE,
  CONSTRAINT `fk_barrio`
    FOREIGN KEY (`id_barrio`)
    REFERENCES `tp_final_26`.`barrios` (`id_barrio`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE,
  CONSTRAINT `fk_situacion_laboral`
    FOREIGN KEY (`id_situacion_laboral`)
    REFERENCES `tp_final_26`.`situaciones_laborales` (`id_situacion_laboral`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE
) ENGINE = InnoDB DEFAULT CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci;

-- -----------------------------------------------------
-- Precarga de datos obligatorios (Situaciones Laborales)
-- -----------------------------------------------------
INSERT INTO `tp_final_26`.`situaciones_laborales` (`id_situacion_laboral`, `nombre`) VALUES 
(1, 'Ocupado'),
(2, 'Desocupado'),
(3, 'Subocupado');

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;