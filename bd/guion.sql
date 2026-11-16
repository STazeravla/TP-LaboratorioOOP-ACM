-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema bd_fatal_final
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `bd_fatal_final` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `bd_fatal_final` ;

-- -----------------------------------------------------
-- Table `bd_fatal_final`.`comuna`
-- Identidad natural: Numero_Comuna
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bd_fatal_final`.`comuna` (
  `Numero_Comuna` INT NOT NULL,
  `Nombre_Sede` VARCHAR(45) NOT NULL,
  `Numero_Barrios` INT NOT NULL,
  `Tipo_Barrio` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`Numero_Comuna`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `bd_fatal_final`.`barrio`
-- Identidad natural: Nombre del barrio
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bd_fatal_final`.`barrio` (
  `nombreBarrio` VARCHAR(45) NOT NULL,
  `superficie` INT NOT NULL,
  `porAsfaltoEnCalles` INT NULL DEFAULT NULL,
  `cantidadDeEdificiosHorizontales` INT NULL DEFAULT NULL,
  `Conexion_Agua` BIT(1) NULL DEFAULT NULL,
  `Ley_Integracion` VARCHAR(45) NULL DEFAULT NULL,
  `Consorsio_Activo` BIT(1) NULL DEFAULT NULL,
  `Cantidad_Monoblocks` INT NULL DEFAULT NULL,
  `fk_comuna` INT,
  PRIMARY KEY (`nombreBarrio`),
  INDEX `fk_comuna_idx` (`fk_comuna` ASC) VISIBLE,
  CONSTRAINT `fk_barrio_comuna`
    FOREIGN KEY (`fk_comuna`)
    REFERENCES `bd_fatal_final`.`comuna` (`Numero_Comuna`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `bd_fatal_final`.`situacion_laboral`
-- Catálogo precargado de situaciones laborales
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bd_fatal_final`.`situacion_laboral` (
  `id_situacion` INT NOT NULL,
  `descripcion` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id_situacion`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `bd_fatal_final`.`persona`
-- Identidad natural: DNI (y relación correcta con Barrio)
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bd_fatal_final`.`persona` (
  `DNI` VARCHAR(45) NOT NULL,
  `Nombre_completo` VARCHAR(45) NOT NULL,
  `Fecha_Nacimiento` DATE NOT NULL,
  `fk_situacion_laboral` INT NOT NULL,
  `fk_barrio` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`DNI`),
  INDEX `fk_persona_barrio_idx` (`fk_barrio` ASC) VISIBLE,
  INDEX `fk_persona_situacion_idx` (`fk_situacion_laboral` ASC) VISIBLE,
  CONSTRAINT `fk_persona_barrio`
    FOREIGN KEY (`fk_barrio`)
    REFERENCES `bd_fatal_final`.`barrio` (`nombreBarrio`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE,
  CONSTRAINT `fk_persona_situacion`
    FOREIGN KEY (`fk_situacion_laboral`)
    REFERENCES `bd_fatal_final`.`situacion_laboral` (`id_situacion`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

